package DAO;

import dto.AdmissionRequestDTO;
import entity.AdmissionRequest;
import entity.Faculty;

import javax.sql.RowSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface AdmissionRequestDAO {


    int saveAdmissionRequest(AdmissionRequest admissionRequest);

    boolean deleteAdmissionRequest(Long id) throws SQLException;

    AdmissionRequest findAdmissionRequest();

    boolean updateAdmissionRequest();


   List<AdmissionRequestDTO> selectAdmissionRequests() throws SQLException;
   List<AdmissionRequestDTO> selectAdmissionRequestsForCandidateWithId(Long id);

}
