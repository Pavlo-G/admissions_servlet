package Service;

import exception.CanNotFindRequestById;
import exception.DbProcessingException;
import model.DAO.DAOFactory;
import model.entity.AdmissionRequest;
import model.entity.AdmissionRequestStatus;

import java.sql.SQLException;


public class AdmissionRequestService {
    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);


    public void changeAdmissionRequestStatus(Long admissionRequestId, AdmissionRequestStatus newAdmissionRequestStatus) {
    }

    public AdmissionRequest findById(Long admissionRequestId) {
        try {
          return daoFactory.getAdmissionRequestDAO().findAdmissionRequest(admissionRequestId)
                    .orElseThrow(() -> new CanNotFindRequestById("Can not find request by id: " + admissionRequestId));
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }
}
