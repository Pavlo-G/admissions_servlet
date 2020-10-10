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
import java.sql.SQLException;


public class MySqlDAOFactory extends DAOFactory {

    private static MySqlDAOFactory instance;

    public static Connection createConnection() {
        Connection con = null;
        try {
            Context context = (Context) new InitialContext().lookup("java:/comp/env");
            DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
            System.out.println(dataSource.getClass().getName());
            con = dataSource.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
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
        return MySqlCandidateDAO.getInstance();
    }

    @Override
    public FacultyDAO getFacultyDAO() {
        return MySqlFacultyDAO.getInstance();
    }

    @Override
    public AdmissionRequestDAO getAdmissionRequestDAO() {
        return MySqlAdmissionRequestDAO.getInstance();
    }
}

