package DAO;

import entity.AdmissionRequest;
import entity.Faculty;

import javax.sql.RowSet;
import java.util.Collection;

public interface AdmissionRequestDAO  {


    public int createAdmissionRequest();
    public boolean deleteAdmissionRequest();
    public Faculty findAdmissionRequest();
    public boolean updateAdmissionRequest();
    public RowSet selectAdmissionRequestsRS();
    public Collection<AdmissionRequest> selectAdmissionRequestsTO();

}
