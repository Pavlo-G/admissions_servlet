package model.DAO;

import model.DAO.mysql.MySqlDAOFactory;


public abstract class DAOFactory {

    public static final int MYSQL = 1;


    public abstract CandidateDAO getCandidateDAO();

    public abstract FacultyDAO getFacultyDAO();

    public abstract AdmissionRequestDAO getAdmissionRequestDAO();


    public static DAOFactory getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case MYSQL:
                return MySqlDAOFactory.getInstance();
            default:
                throw new RuntimeException("Unknown factory");
        }
    }


}