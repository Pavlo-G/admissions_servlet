package DAO.mapper;

import dto.AdmissionRequestDTO;
import entity.AdmissionRequest;
import entity.AdmissionRequestStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AdmissionRequestDTOMapper implements ObjectMapper<AdmissionRequestDTO> {


    @Override
    public AdmissionRequestDTO extractFromResultSet(ResultSet rs) throws SQLException {
      AdmissionRequestDTO admissionRequestDTO = new AdmissionRequestDTO();
        admissionRequestDTO.setId(rs.getLong("id"));
        admissionRequestDTO.setAdmissionRequestStatus(AdmissionRequestStatus.getAdmissionRequestStatus(rs.getInt("status")));
        admissionRequestDTO.setCandidateId(rs.getLong("candidate_id"));
        admissionRequestDTO.setFacultyId(rs.getLong("faculty_id"));
        admissionRequestDTO.setRequiredSubject1Grade(rs.getInt("req_subject1_grade"));
        admissionRequestDTO.setRequiredSubject2Grade(rs.getInt("req_subject2_grade"));
        admissionRequestDTO.setRequiredSubject3Grade(rs.getInt("req_subject3_grade"));
        admissionRequestDTO.setCreationDateTime(rs.getTimestamp("creation_date_time").toLocalDateTime());
        admissionRequestDTO.setFirstName(rs.getString("first_name"));
        admissionRequestDTO.setLastName(rs.getString("last_name"));
        admissionRequestDTO.setFacultyName(rs.getString("name"));
        return admissionRequestDTO;
    }

    @Override
    public AdmissionRequestDTO makeUnique(Map<Integer, AdmissionRequestDTO> cache, AdmissionRequestDTO entity) {
        return null;
    }
}
