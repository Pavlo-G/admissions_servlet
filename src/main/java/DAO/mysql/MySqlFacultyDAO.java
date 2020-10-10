package DAO.mysql;


import DAO.FacultyDAO;
import entity.Faculty;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MySqlFacultyDAO implements FacultyDAO {

    private static MySqlFacultyDAO instance;




    public static synchronized MySqlFacultyDAO getInstance() {
        if (instance == null) {
            instance = new MySqlFacultyDAO();
        }
        return instance;
    }

    private MySqlFacultyDAO() {

    }


    @Override
    public int createFaculty() {
        return 0;
    }

    @Override
    public boolean deleteFaculty() {
        return false;
    }

    @Override
    public Faculty findFaculty(int id) {
        return null;
    }

    @Override
    public boolean updateFaculty() {
        return false;
    }

    @Override
    public RowSet selectFacultyRS() {
        return null;
    }

    @Override
    public List<Faculty> getAllFacultiesTO() {
        List<Faculty> listFaculties = new ArrayList<>();

        try (Connection con = MySqlDAOFactory.createConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(Constants.SQL_FIND_ALL_FACULTIES)) {

            while (rs.next()) {
                listFaculties.add(mapFaculty(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            //LOGGER.severe("Cannot get all users!");
            //  throw new SQLException("Cannot get all candidates!", ex);
        }
        return listFaculties;


    }


    private Faculty mapFaculty(ResultSet rs) throws SQLException {
        String name = rs.getString("name");

        Faculty faculty = Faculty.createFaculty(name);
        faculty.setId(rs.getLong("id"));
        faculty.setName(rs.getString("name"));
        faculty.setDescription(rs.getString("description"));
        faculty.setBudgetCapacity(rs.getInt("budget_capacity"));
        faculty.setTotalCapacity(rs.getInt("total_capacity"));
        faculty.setRequiredSubject1(rs.getString("req_subject1"));
        faculty.setRequiredSubject2(rs.getString("req_subject2"));
        faculty.setRequiredSubject3(rs.getString("req_subject3"));
        faculty.setAdmissionOpen(rs.getBoolean("admission_open"));

        return faculty;
    }
}
