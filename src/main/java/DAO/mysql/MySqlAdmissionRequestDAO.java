package DAO.mysql;

import DAO.AdmissionRequestDAO;
import entity.AdmissionRequest;
import entity.AdmissionRequestStatus;
import entity.Faculty;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MySqlAdmissionRequestDAO implements AdmissionRequestDAO {



    private static Connection connection;


    public MySqlAdmissionRequestDAO(Connection connection) {
        this.connection = connection;
    }



    @Override
    public int createAdmissionRequest() {
        return 0;
    }

    @Override
    public boolean deleteAdmissionRequest() {
        return false;
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
    public Collection<AdmissionRequest> selectAdmissionRequestsTO() {
        List<AdmissionRequest> admissionRequests = new ArrayList<>();

        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(Constants.SQL_FIND_ALL_ADMISSION_REQUESTS)) {

            while (rs.next()) {
                admissionRequests.add(mapAdmissionRequest(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            //LOGGER.severe("Cannot get all users!");
            //  throw new SQLException("Cannot get all candidates!", ex);
        }

        return admissionRequests;
    }

    private AdmissionRequest mapAdmissionRequest(ResultSet rs) throws SQLException {
        AdmissionRequest admissionRequest = AdmissionRequest.createAdmissionRequest();
        admissionRequest.setId(rs.getLong("id"));
        admissionRequest.setAdmissionRequestStatus(AdmissionRequestStatus.getAdmissionRequestStatus(rs.getInt("status")));
        admissionRequest.setCandidateId(rs.getLong("candidate_id"));
        admissionRequest.setFacultyId(rs.getLong("faculty_id"));
        admissionRequest.setRequiredSubject1Grade(rs.getInt("req_subject1_grade"));
        admissionRequest.setRequiredSubject2Grade(rs.getInt("req_subject2_grade"));
        admissionRequest.setRequiredSubject3Grade(rs.getInt("req_subject3_grade"));
        admissionRequest.setCreationDateTime(rs.getTimestamp("creation_date_time").toLocalDateTime());


        return admissionRequest;
    }

}
