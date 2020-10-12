package DAO.mapper;

import entity.AdmissionRequest;
import entity.AdmissionRequestStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AdmissionRequestMapper implements ObjectMapper<AdmissionRequest> {

    @Override
    public AdmissionRequest makeUnique(Map<Long, AdmissionRequest> cache, AdmissionRequest entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }

    public AdmissionRequest extractFromResultSet(ResultSet rs) throws SQLException {
        AdmissionRequest admissionRequest = new AdmissionRequest();
        admissionRequest.setId(rs.getLong("admission_request.id"));
        admissionRequest.setAdmissionRequestStatus(AdmissionRequestStatus.getAdmissionRequestStatus(rs.getInt("status")));
        admissionRequest.setRequiredSubject1Grade(rs.getInt("req_subject1_grade"));
        admissionRequest.setRequiredSubject2Grade(rs.getInt("req_subject2_grade"));
        admissionRequest.setRequiredSubject3Grade(rs.getInt("req_subject3_grade"));
        admissionRequest.setCreationDateTime(rs.getTimestamp("creation_date_time").toLocalDateTime());
        return admissionRequest;
    }

}
