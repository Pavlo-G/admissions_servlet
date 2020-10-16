package DAO.mysql;


import DAO.FacultyDAO;
import DAO.mapper.AdmissionRequestMapper;
import DAO.mapper.CandidateMapper;
import DAO.mapper.CandidateProfileMapper;
import DAO.mapper.FacultyMapper;
import dto.FacultyDTO;
import entity.AdmissionRequest;
import entity.Candidate;
import entity.CandidateProfile;
import entity.Faculty;

import java.sql.*;
import java.util.*;


public class MySqlFacultyDAO implements FacultyDAO {


    private final Connection connection;


    public MySqlFacultyDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public int createFaculty(Faculty faculty) {
        String sql = " INSERT INTO faculty  " +
                " (name_en,name_uk,description_en,description_uk,budget_capacity, total_capacity,req_subject1_en,req_subject1_uk,req_subject2_en,req_subject2_uk,req_subject3_en,req_subject3_uk,admission_open)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(sql)) {

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
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }


    @Override
    public boolean deleteFaculty(Long id) throws SQLException {
        boolean res = false;
        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement("DELETE  FROM faculty WHERE  id= ?")) {
            pstmt.setLong(1, id);
            res = pstmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            throw new SQLException("Cannot delete a faculty with id:" + id, ex);
        }
        return res;
    }

    @Override
    public Faculty findFaculty(Long id) {
        Faculty faculty = null;
        Faculty facultyUnique = null;
        Map<Long, Faculty> facultyMap = new HashMap<>();

        String sql = "SELECT  " +
                "f.id, f.budget_capacity, f.description_en, f.name_en,f.description_uk,f.name_uk, f.req_subject1_en, f.req_subject1_uk, f.req_subject2_en ,f.req_subject2_uk, f.req_subject3_en ,f.req_subject3_uk, f.total_capacity, f.admission_open," +
                "admission_request.id, admission_request.status, admission_request.creation_date_time, admission_request.req_subject1_grade, admission_request.req_subject2_grade, admission_request.req_subject3_grade, admission_request.candidate_id, admission_request.faculty_id," +
                "c.id, c.candidate_status, c.password, c.role, c.username," +
                "cp.id, cp.address, cp.city, cp.email, cp.first_name, cp.last_name, cp.phone_number, cp.region, cp.school, cp.candidate_id " +
                "FROM faculty f " +
                "Left Join admission_request  on f.id = admission_request.faculty_id " +
                "Left Join candidate c on admission_request.candidate_id = c.id  " +
                "Left Join candidate_profile cp on c.id = cp.candidate_id" +
                " WHERE f.id=?";
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
                FacultyMapper facultyMapper = new FacultyMapper();
                CandidateMapper candidateMapper = new CandidateMapper();
                CandidateProfileMapper candidateProfileMapper = new CandidateProfileMapper();
                while (rs.next()) {
                    Optional<AdmissionRequest> admissionRequest = admissionRequestMapper.extractFromResultSet2(rs);
                    faculty = facultyMapper.extractFromResultSet(rs);
                    facultyUnique = facultyMapper.makeUnique(facultyMap, faculty);
                    if (admissionRequest.isPresent()) {
                        Candidate candidate = candidateMapper.extractFromResultSet(rs);
                        CandidateProfile candidateProfile = candidateProfileMapper.extractFromResultSet(rs);
                        candidate.setCandidateProfile(candidateProfile);
                        admissionRequest.get().setCandidate(candidate);
                        admissionRequest.get().setFaculty(facultyUnique);
                        facultyUnique.getAdmissionRequestList().add(admissionRequest.get());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultyUnique;
    }

    @Override
    public boolean updateFaculty(Faculty faculty) {


        String sql = " UPDATE  faculty " +
                "SET name_en=?, name_uk=?,description_en=?,description_uk=?,budget_capacity=?, total_capacity=?,req_subject1_en=?,req_subject1_uk=?,req_subject2_en=?, req_subject2_uk=?,req_subject3_en=?,req_subject3_uk=? " +
                "WHERE id=?;";


        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(sql)) {

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
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }


    @Override
    public boolean changeAdmissionOpenStatus(String action, Long facultyId) {
        String sql = " UPDATE  faculty " +
                "SET admission_open=?" +
                " WHERE id=?;";


        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setBoolean(1, !action.equals("block"));
            pstmt.setLong(2, facultyId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public List<Faculty> getAllFaculties() throws SQLException {
        Map<Long, Faculty> faculties = new HashMap<>();

        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * From faculty f LEFT JOIN admission_request on f.id = admission_request.faculty_id")) {
            FacultyMapper facultyMapper = new FacultyMapper();
            AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
            while (rs.next()) {

                Optional<AdmissionRequest> admissionRequest = admissionRequestMapper.extractFromResultSet2(rs);
                Faculty faculty = facultyMapper.extractFromResultSet(rs);
                Faculty uniqueFaculty = facultyMapper.makeUnique(faculties, faculty);
                if (admissionRequest.isPresent()) {
                    uniqueFaculty.getAdmissionRequestList().add(admissionRequest.get());
                }


            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Cannot get all faculties!", ex);
        }
        return new ArrayList<>(faculties.values());


    }


    public FacultyDTO getAllFaculties2(String name_en, String direction, int page, int itemsPerPage) throws SQLException {

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
        return new FacultyDTO(count, faculties);
    }
}