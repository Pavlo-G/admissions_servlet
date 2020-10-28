package model.DAO.mysql;

import controller.command.candidate.GetCandidateRequestsListCommand;
import model.DAO.AdmissionRequestDAO;
import model.DAO.mapper.AdmissionRequestMapper;
import model.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MySqlAdmissionRequestDAO implements AdmissionRequestDAO {
    static final Logger LOG = LoggerFactory.getLogger(MySqlAdmissionRequestDAO.class);

    private final Connection connection;


    public MySqlAdmissionRequestDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(AdmissionRequest admissionRequest) throws SQLException {

        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(Constants.SQL_INSERT_ADMISSION_REQUEST)) {
            pstmt.setLong(1, admissionRequest.getFacultyId());
            pstmt.setLong(2, admissionRequest.getCandidateId());
            pstmt.setInt(3, admissionRequest.getRequiredSubject1Grade());
            pstmt.setInt(4, admissionRequest.getRequiredSubject2Grade());
            pstmt.setInt(5, admissionRequest.getRequiredSubject3Grade());
            pstmt.setInt(6, admissionRequest.getAdmissionRequestStatus().ordinal());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Cannot save admission request !");
        }

    }

    @Override
    public Optional<AdmissionRequest> findById(Long id) throws SQLException {
        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement(Constants.SQL_FIND_ADMISSION_REQUEST_BY_ID)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
                    return admissionRequestMapper.getAdmissionRequest(rs);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Cannot find a admission request with id: " + id, e);
        }

        return Optional.empty();
    }


    @Override
    public List<AdmissionRequest> findAll() throws SQLException {
        List<AdmissionRequest> admissionRequests = new ArrayList<>();

        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(Constants.SQL_FIND_ALL_ADMISSION_REQUESTS)) {
            AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
            while (rs.next()) {
                admissionRequests.add(admissionRequestMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {

            throw new SQLException("Cannot get all admission requests!", ex);
        }

        return admissionRequests;
    }


    @Override
    public void delete(Long id) throws SQLException {
        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement(Constants.SQL_DELETE_ADMISSION_REQUEST)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLException("Cannot delete a admission request with id:" + id, ex);
        }

    }


    @Override
    public boolean changeAdmissionRequestStatus(Long id, AdmissionRequestStatus status) throws SQLException {
        boolean res = false;
        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement(Constants.SQL_CHANGE_ADMISSION_REQUEST_STATUS)) {
            pstmt.setInt(1, status.ordinal());
            pstmt.setLong(2, id);
            res = pstmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            throw new SQLException("Cannot delete a admission request with id:" + id, ex);
        }
        return res;
    }


    @Override
    public List<AdmissionRequest> selectAdmissionRequestsForCandidateWithId(Long id) throws SQLException {

        List<AdmissionRequest> admissionRequestList = new ArrayList<>();

        String sql = "SELECT " +
                "cp.id, cp.address, cp.city, cp.email, cp.first_name, cp.last_name, cp.phone_number, cp.region, cp.school,cp.certificate_file, cp.candidate_id, " +
                "f.id, budget_capacity, description_en, name_en,description_uk,name_uk, req_subject1_en,req_subject1_uk, req_subject2_en,req_subject2_uk, req_subject3_en,req_subject3_uk, total_capacity, admission_open," +
                "admission_request.id, admission_request.status, creation_date_time, req_subject1_grade, req_subject2_grade, req_subject3_grade,admission_request.candidate_id,faculty_id," +
                " c.id,c.username,c.password,c.role,c.candidate_status " +
                "FROM admission_request " +
                "Left JOIN candidate c on admission_request.candidate_id=c.id " +
                "Left JOIN  candidate_profile cp on admission_request.candidate_id = cp.candidate_id " +
                "Left JOIN faculty f on admission_request.faculty_id = f.id WHERE admission_request.candidate_id=?;";
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
                    Optional<AdmissionRequest> admissionRequest = admissionRequestMapper.getAdmissionRequest(rs);
                    admissionRequest.ifPresent(admissionRequestList::add);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Cannot find a admission requests for candidate with id: " + id, e);
        }
        return admissionRequestList;
    }

    @Override
    public void update(AdmissionRequest entity) throws SQLException {

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
