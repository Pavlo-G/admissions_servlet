package model.DAO.mysql;


import model.DAO.FacultyDAO;
import model.DAO.mapper.AdmissionRequestMapper;
import model.DAO.mapper.FacultyMapper;
import utils.util.FacultyPage;
import model.entity.AdmissionRequest;
import model.entity.Faculty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;


public class MySqlFacultyDAO implements FacultyDAO {
    static final Logger LOG = LoggerFactory.getLogger(MySqlFacultyDAO.class);

    private final Connection connection;


    public MySqlFacultyDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean changeAdmissionOpenStatus(String action, Long facultyId) throws SQLException {

        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(Constants.SQL_UPDATE_FACULTY_ADMISSION_STATUS)) {
            pstmt.setBoolean(1, !action.equals("block"));
            pstmt.setLong(2, facultyId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Cannot change status for faculty with id: " + facultyId, e);

        }

    }

    @Override
    public void create(Faculty faculty) throws SQLException {
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(Constants.SQL_CREATE_FACULTY)) {
            pstmt.setString(1, faculty.getNameEn());
            pstmt.setString(2, faculty.getNameUk());
            pstmt.setString(3, faculty.getDescriptionEn());
            pstmt.setString(4, faculty.getDescriptionUk());
            pstmt.setInt(5, faculty.getBudgetCapacity());
            pstmt.setInt(6, faculty.getTotalCapacity());
            pstmt.setString(7, faculty.getRequiredSubject1En());
            pstmt.setString(8, faculty.getRequiredSubject1Uk());
            pstmt.setString(9, faculty.getRequiredSubject2En());
            pstmt.setString(10, faculty.getRequiredSubject2Uk());
            pstmt.setString(11, faculty.getRequiredSubject3En());
            pstmt.setString(12, faculty.getRequiredSubject3Uk());
            pstmt.setBoolean(13, faculty.isAdmissionOpen());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Cannot create faculty", e);

        }

    }

    @Override
    public Optional<Faculty> findById(Long id) throws SQLException {
        Faculty faculty;
        Optional<Faculty> facultyUnique = Optional.empty();
        Map<Long, Faculty> facultyMap = new HashMap<>();

        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(Constants.SQL_FIND_FACULTY_BY_ID)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                FacultyMapper facultyMapper = new FacultyMapper();
                while (rs.next()) {
                    AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
                    Optional<AdmissionRequest> admissionRequest = admissionRequestMapper.getAdmissionRequest(rs);
                    if (admissionRequest.isPresent()) {
                        faculty = admissionRequest.get().getFaculty();
                        facultyUnique = Optional.of(facultyMapper.makeUnique(facultyMap, faculty));
                        facultyUnique.get().getAdmissionRequestList().add(admissionRequest.get());
                    }
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Cannot find faculty with id: " + id, e);
        }
        return facultyUnique;
    }


    @Override
    public List<Faculty> findAll() throws SQLException {
        Map<Long, Faculty> faculties = new HashMap<>();

        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(Constants.SQL_FIND_ALL_FACULTIES)) {
            FacultyMapper facultyMapper = new FacultyMapper();
            AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
            while (rs.next()) {
                Optional<AdmissionRequest> admissionRequest = Optional.of(admissionRequestMapper.extractFromResultSet(rs));
                Faculty faculty = facultyMapper.extractFromResultSet(rs);
                Faculty uniqueFaculty = facultyMapper.makeUnique(faculties, faculty);
                admissionRequest.ifPresent(request -> uniqueFaculty.getAdmissionRequestList().add(request));
            }
        } catch (SQLException ex) {
            throw new SQLException("Cannot get all faculties!", ex);
        }
        return new ArrayList<>(faculties.values());


    }

    @Override
    public void update(Faculty faculty) throws SQLException {

        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(Constants.SQL_UPDATE_FACULTY)) {

            pstmt.setString(1, faculty.getNameEn());
            pstmt.setString(2, faculty.getNameUk());
            pstmt.setString(3, faculty.getDescriptionEn());
            pstmt.setString(4, faculty.getDescriptionUk());
            pstmt.setInt(5, faculty.getBudgetCapacity());
            pstmt.setInt(6, faculty.getTotalCapacity());
            pstmt.setString(7, faculty.getRequiredSubject1En());
            pstmt.setString(8, faculty.getRequiredSubject1Uk());
            pstmt.setString(9, faculty.getRequiredSubject2En());
            pstmt.setString(10, faculty.getRequiredSubject2Uk());
            pstmt.setString(11, faculty.getRequiredSubject3En());
            pstmt.setString(12, faculty.getRequiredSubject3Uk());
            pstmt.setLong(13, faculty.getId());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new SQLException("Cannot find update faculty ", e);

        }

    }

    @Override
    public void delete(Long id) throws SQLException {

        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement(Constants.SQL_DELETE_FACULTY)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException("Cannot delete a faculty with id:" + id, ex);
        }


    }


    public FacultyPage findAllSorted(String name_en, String direction, int page, int itemsPerPage) throws SQLException {

        List<Faculty> faculties = new ArrayList<>();
        int count = 0;
        int fromItem = (page - 1) * itemsPerPage;
        int toItem = ((page - 1) * itemsPerPage) + itemsPerPage;
        String sql = "select f.id, budget_capacity, description_en, description_uk, name_en,name_uk, req_subject1_en,req_subject1_uk, req_subject2_en,req_subject2_uk, req_subject3_en,req_subject3_uk, total_capacity, admission_open," +
                "(select count(*) from faculty) as count from faculty f ORDER BY " + name_en + " " + direction + " LIMIT " + fromItem + "," + toItem + ";";
        try (Connection con = connection;
             PreparedStatement pstmt =
                     con.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                FacultyMapper facultyMapper = new FacultyMapper();

                while (rs.next()) {
                    Faculty faculty = facultyMapper.extractFromResultSet(rs);
                    count = rs.getInt("count");
                    faculties.add(faculty);
                }
            } catch (
                    SQLException ex) {
                ex.printStackTrace();
                throw new SQLException("Cannot get all faculties!", ex);
            }
        }
        return new FacultyPage(count, faculties);
    }


    @Override
    public void close() throws SQLException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}