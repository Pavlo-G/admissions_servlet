package model.DAO;

import model.entity.AdmissionRequest;
import model.entity.AdmissionRequestStatus;
import model.entity.Faculty;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AdmissionRequestDAO extends GenericDao<AdmissionRequest> {


    int saveAdmissionRequest(AdmissionRequest admissionRequest) throws SQLException;

    boolean deleteAdmissionRequest(Long id) throws SQLException;

    Optional<AdmissionRequest> findAdmissionRequest(Long id) throws SQLException;

    boolean changeAdmissionRequestStatus(Long id, AdmissionRequestStatus status) throws SQLException;

    List<AdmissionRequest> selectAdmissionRequests() throws SQLException;

    List<AdmissionRequest> selectAdmissionRequestsForCandidateWithId(Long id) throws SQLException;


}
