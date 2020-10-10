package DAO.mysql;

import DAO.AdmissionRequestDAO;
import DAO.mapper.AdmissionRequestDTOMapper;
import dto.AdmissionRequestDTO;
import entity.AdmissionRequest;
import entity.AdmissionRequestStatus;
import entity.Faculty;

import javax.sql.RowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class MySqlAdmissionRequestDAO implements AdmissionRequestDAO {


    private final Connection connection;


    public MySqlAdmissionRequestDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public int saveAdmissionRequest(AdmissionRequest admissionRequest) {

        String sql = "INSERT INTO admission_request " +
                "(faculty_id,candidate_id,req_subject1_grade,req_subject2_grade,req_subject3_grade,status)" +
                "Values(?,?,?,?,?,?);";
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setLong(1, admissionRequest.getFacultyId());
            pstmt.setLong(2, admissionRequest.getCandidateId());
            pstmt.setInt(3, admissionRequest.getRequiredSubject1Grade());
            pstmt.setInt(4, admissionRequest.getRequiredSubject2Grade());
            pstmt.setInt(5, admissionRequest.getRequiredSubject3Grade());
            pstmt.setInt(6, admissionRequest.getAdmissionRequestStatus().ordinal());
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<AdmissionRequestDTO> selectAdmissionRequestsForCandidateWithId(Long id) {

        List<AdmissionRequestDTO> admissionRequestDTOList = new ArrayList<>();

        String sql = "SELECT c.first_name,c.last_name,f.name,ar.id, status, creation_date_time," +
                "req_subject1_grade, req_subject2_grade, req_subject3_grade,ar.candidate_id,ar.faculty_id" +
                " FROM admission_request ar " +
                " JOIN  candidate_profile c on ar.candidate_id = c.candidate_id " +
                "JOIN faculty f on ar.faculty_id = f.id WHERE ar.candidate_id=?";
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                AdmissionRequestDTOMapper admissionRequestDTOMapper = new AdmissionRequestDTOMapper();
                while (rs.next()) {
                    admissionRequestDTOList.add(admissionRequestDTOMapper.extractFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admissionRequestDTOList;
    }


    @Override
    public boolean deleteAdmissionRequest(Long id) throws SQLException {
        boolean res = false;
        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement("DELETE  FROM admission_request WHERE  id= ?")) {
            pstmt.setLong(1, id);
            res = pstmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            throw new SQLException("Cannot delete a admission request with id:" + id, ex);
        }
        return res;

    }

    @Override
    public AdmissionRequest findAdmissionRequest() {
        return null;
    }

    @Override
    public boolean updateAdmissionRequest() {
        return false;
    }




    @Override
    public List<AdmissionRequestDTO> selectAdmissionRequests() throws SQLException {
        List<AdmissionRequestDTO> admissionRequests = new ArrayList<>();

        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(Constants.SQL_FIND_ALL_ADMISSION_REQUESTS)) {
            AdmissionRequestDTOMapper admissionRequestDTOMapper = new AdmissionRequestDTOMapper();
            while (rs.next()) {
                admissionRequests.add(admissionRequestDTOMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {

            throw new SQLException("Cannot get all admission requests!", ex);
        }

        return admissionRequests;
    }

}
