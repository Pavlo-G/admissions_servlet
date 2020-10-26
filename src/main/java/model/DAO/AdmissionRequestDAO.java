package model.DAO;

import model.dto.AdmissionRequestDTO;
import model.entity.AdmissionRequest;
import model.entity.AdmissionRequestStatus;
import model.entity.Faculty;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AdmissionRequestDAO extends GenericDao<AdmissionRequest> {

    boolean changeAdmissionRequestStatus(Long id, AdmissionRequestStatus status) throws SQLException;

    List<AdmissionRequest> selectAdmissionRequestsForCandidateWithId(Long id) throws SQLException;


}
