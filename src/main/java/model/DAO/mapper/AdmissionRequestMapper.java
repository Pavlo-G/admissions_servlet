package model.DAO.mapper;

import model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

public class AdmissionRequestMapper implements ObjectMapper<AdmissionRequest> {

    public  Optional<AdmissionRequest> getAdmissionRequest(ResultSet rs) throws SQLException {

        FacultyMapper facultyMapper = new FacultyMapper();
        CandidateMapper candidateMapper = new CandidateMapper();
        CandidateProfileMapper candidateProfileMapper = new CandidateProfileMapper();

        Optional<AdmissionRequest> admissionRequest = Optional.of(extractFromResultSet(rs));
        Optional<Faculty> faculty = Optional.of(facultyMapper.extractFromResultSet(rs));
        Optional<Candidate> candidate = Optional.of(candidateMapper.extractFromResultSet(rs));
        Optional<CandidateProfile> candidateProfile = Optional.of(candidateProfileMapper.extractFromResultSet(rs));
        candidate.ifPresent(c -> c.setCandidateProfile(candidateProfile.get()));
        admissionRequest.ifPresent(ar -> {
            ar.setCandidate(candidate.get());
            ar.setFaculty(faculty.get());

        });
        return admissionRequest;
    }

    @Override
    public AdmissionRequest makeUnique(Map<Long, AdmissionRequest> cache, AdmissionRequest entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }


    public AdmissionRequest extractFromResultSet(ResultSet rs) throws SQLException {

        AdmissionRequest admissionRequest = new AdmissionRequest();
        admissionRequest.setId(rs.getLong("admission_request.id"));
        admissionRequest.setAdmissionRequestStatus(AdmissionRequestStatus.getAdmissionRequestStatus(rs.getInt("admission_request.status")));
        admissionRequest.setRequiredSubject1Grade(rs.getInt("req_subject1_grade"));
        admissionRequest.setRequiredSubject2Grade(rs.getInt("req_subject2_grade"));
        admissionRequest.setRequiredSubject3Grade(rs.getInt("req_subject3_grade"));
        Timestamp timestamp = rs.getTimestamp("creation_date_time");
        if (timestamp != null) {
            admissionRequest.setCreationDateTime(timestamp.toLocalDateTime());
        }
        return admissionRequest;

    }



}
