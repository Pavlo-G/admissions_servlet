package DAO;

import entity.AdmissionRequest;
import entity.AdmissionRequestStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AdmissionRequestDAO {


    int saveAdmissionRequest(AdmissionRequest admissionRequest);

    boolean deleteAdmissionRequest(Long id) throws SQLException;

    Optional<AdmissionRequest> findAdmissionRequest(Long id);

    boolean updateAdmissionRequest();

    boolean changeAdmissionRequestStatus(Long id, AdmissionRequestStatus status) throws SQLException;

    List<AdmissionRequest> selectAdmissionRequests() throws SQLException;

    List<AdmissionRequest> selectAdmissionRequestsForCandidateWithId(Long id);

    List<AdmissionRequest> selectAdmissionRequestsForFacultyWithId(Long id);
}
