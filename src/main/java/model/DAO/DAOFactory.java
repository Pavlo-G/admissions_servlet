package model.DAO;

import model.DAO.mysql.MySqlDAOFactory;


public abstract class DAOFactory {

    // List of model.DAO types supported by the factory
    public static final int MYSQL = 1;
//    public static final int ORACLE = 2;
//    public static final int SYBASE = 3;


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