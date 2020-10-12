package DAO;

import entity.AdmissionRequest;

import java.sql.SQLException;
import java.util.List;

public interface AdmissionRequestDAO {


    int saveAdmissionRequest(AdmissionRequest admissionRequest);

    boolean deleteAdmissionRequest(Long id) throws SQLException;

    AdmissionRequest findAdmissionRequest(Long id);

    boolean updateAdmissionRequest();


    List<AdmissionRequest> selectAdmissionRequests() throws SQLException;

    List<AdmissionRequest> selectAdmissionRequestsForCandidateWithId(Long id);

    List<AdmissionRequest> selectAdmissionRequestsForFacultyWithId(Long id);
}
