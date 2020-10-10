package DAO.mysql;

import DAO.AdmissionRequestDAO;
import DAO.CandidateDAO;
import DAO.DAOFactory;
import DAO.FacultyDAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MySqlDAOFactory extends DAOFactory {

    private static MySqlDAOFactory instance;
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection(){
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

