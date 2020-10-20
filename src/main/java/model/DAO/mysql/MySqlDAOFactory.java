package model.DAO.mysql;

import model.DAO.AdmissionRequestDAO;
import model.DAO.CandidateDAO;
import model.DAO.DAOFactory;
import model.DAO.FacultyDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class MySqlDAOFactory extends DAOFactory {

    private static MySqlDAOFactory instance;
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static synchronized MySqlDAOFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDAOFactory();
        }

        return instance;
    }


    private MySqlDAOFactory() {
        super();
    }

    @Override
    public CandidateDAO getCandidateDAO() {
        return new MySqlCandidateDAO(getConnection());
    }

    @Override
    public FacultyDAO getFacultyDAO() {
        return new MySqlFacultyDAO(getConnection());
    }

    @Override
    public AdmissionRequestDAO getAdmissionRequestDAO() {
        return new MySqlAdmissionRequestDAO(getConnection());
    }


}

