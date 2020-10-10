package DAO;

import entity.AdmissionRequest;
import entity.Faculty;

import javax.sql.RowSet;
import java.util.Collection;

public interface AdmissionRequestDAO {


    int createAdmissionRequest();

    boolean deleteAdmissionRequest();

    AdmissionRequest findAdmissionRequest();

    boolean updateAdmissionRequest();


    Collection<AdmissionRequest> selectAdmissionRequestsTO();

}
