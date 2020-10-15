package DAO.mysql;

import DAO.AdmissionRequestDAO;
import DAO.mapper.AdmissionRequestMapper;
import DAO.mapper.CandidateMapper;
import DAO.mapper.CandidateProfileMapper;
import DAO.mapper.FacultyMapper;
import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlAdmissionRequestDAO implements AdmissionRequestDAO {


    private final Connection connection;


    public MySqlAdmissionRequestDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean changeAdmissionRequestStatus(Long id, AdmissionRequestStatus status) throws SQLException {
        boolean res = false;
        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement("UPDATE  admission_request SET status=? WHERE  id= ?")) {
            pstmt.setInt(1, status.ordinal());
            pstmt.setLong(2, id);
            res = pstmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            throw new SQLException("Cannot delete a admission request with id:" + id, ex);
        }
        return res;
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
    public List<AdmissionRequest> selectAdmissionRequestsForCandidateWithId(Long id) {

        List<AdmissionRequest> admissionRequestList = new ArrayList<>();

        String sql = "SELECT " +
                "cp.id, cp.address, cp.city, cp.email, cp.first_name, cp.last_name, cp.phone_number, cp.region, cp.school, cp.candidate_id, " +
                "f.id, budget_capacity, description_en, name_en,description_uk,name_uk, req_subject1_en,req_subject1_uk, req_subject2_en,req_subject2_uk, req_subject3_en,req_subject3_uk, total_capacity, admission_open," +
                "admission_request.id, status, creation_date_time, req_subject1_grade, req_subject2_grade, req_subject3_grade,admission_request.candidate_id,faculty_id," +
                " c.id,c.username,c.password,c.role,c.candidate_status " +
                "FROM admission_request " +
                "Left JOIN candidate c on admission_request.candidate_id=c.id " +
                "Left JOIN  candidate_profile cp on admission_request.candidate_id = cp.candidate_id " +
                "Left JOIN faculty f on admission_request.faculty_id = f.id WHERE admission_request.candidate_id=?;";
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
                    Faculty faculty = facultyMapper.extractFromResultSet(rs);
                    Candidate candidate = candidateMapper.extractFromResultSet(rs);
                    CandidateProfile candidateProfile = candidateProfileMapper.extractFromResultSet(rs);
                    candidate.setCandidateProfile(candidateProfile);
                    admissionRequest.ifPresent(x -> {
                        x.setCandidate(candidate);
                        x.setFaculty(faculty);
                        admissionRequestList.add(x);
                    });

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admissionRequestList;
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
    public Optional<AdmissionRequest> findAdmissionRequest(Long id) {

        String sql = "SELECT cp.id, cp.address, cp.city, cp.email, cp.first_name, cp.last_name, cp.phone_number, cp.region, cp.school, cp.candidate_id, " +
                "                f.id, budget_capacity, description_en, name_en, name_uk,description_uk, req_subject1_en,req_subject1_uk, req_subject2_en,req_subject2_uk, req_subject3_en,req_subject3_uk, total_capacity, admission_open, " +
                "                admission_request.id, status, creation_date_time, req_subject1_grade, req_subject2_grade, req_subject3_grade,admission_request.candidate_id,faculty_id, " +
                "                 c.id,c.username,c.password,c.role,c.candidate_status " +
                "                FROM admission_request  " +
                "                Left JOIN candidate c on admission_request.candidate_id=c.id " +
                "                Left JOIN  candidate_profile cp on admission_request.candidate_id = cp.candidate_id " +
                "                Left JOIN faculty f on admission_request.faculty_id = f.id WHERE admission_request.id=?";
        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                AdmissionRequestMapper admissionRequestMapper = new AdmissionRequestMapper();
                FacultyMapper facultyMapper = new FacultyMapper();
                CandidateMapper candidateMapper = new CandidateMapper();
                CandidateProfileMapper candidateProfileMapper = new CandidateProfileMapper();
                while (rs.next()) {
                    Optional<AdmissionRequest> admissionRequest = admissionRequestMapper.extractFromResultSet2(rs);
                    Faculty faculty = facultyMapper.extractFromResultSet(rs);
                    Candidate candidate = candidateMapper.extractFromResultSet(rs);
                    CandidateProfile candidateProfile = candidateProfileMapper.extractFromResultSet(rs);
                    candidate.setCandidateProfile(candidateProfile);
                    admissionRequest.ifPresent(x -> {
                        x.setCandidate(candidate);
                        x.setFaculty(faculty);

                    });
                    return admissionRequest;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }



    @Override
    public List<AdmissionRequest> selectAdmissionRequests() throws SQLException {
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


}
