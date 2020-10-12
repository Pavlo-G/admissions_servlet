package DAO.mapper;

import entity.AdmissionRequest;
import entity.AdmissionRequestStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

public class AdmissionRequestMapper implements ObjectMapper<AdmissionRequest> {

    @Override
    public AdmissionRequest makeUnique(Map<Long, AdmissionRequest> cache, AdmissionRequest entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }

    @Override
    public AdmissionRequest extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    public Optional<AdmissionRequest> extractFromResultSet2(ResultSet rs) throws SQLException {
        if (rs.getLong("admission_request.id") != 0) {
            AdmissionRequest admissionRequest = new AdmissionRequest();
            admissionRequest.setId(rs.getLong("admission_request.id"));
            admissionRequest.setAdmissionRequestStatus(AdmissionRequestStatus.getAdmissionRequestStatus(rs.getInt("status")));
            admissionRequest.setRequiredSubject1Grade(rs.getInt("req_subject1_grade"));
            admissionRequest.setRequiredSubject2Grade(rs.getInt("req_subject2_grade"));
            admissionRequest.setRequiredSubject3Grade(rs.getInt("req_subject3_grade"));
            Timestamp timestamp = rs.getTimestamp("creation_date_time");
            if (timestamp != null) {
                admissionRequest.setCreationDateTime(timestamp.toLocalDateTime());
            }
            return Optional.of(admissionRequest);
        }
        return Optional.empty();
    }







}
