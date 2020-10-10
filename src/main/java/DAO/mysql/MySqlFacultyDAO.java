package DAO.mysql;


import DAO.FacultyDAO;
import DAO.mapper.FacultyMapper;
import entity.Faculty;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MySqlFacultyDAO implements FacultyDAO {


    private final Connection connection;


    public MySqlFacultyDAO(Connection connection) {
        this.connection = connection;
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
    public List<Faculty> getAllFacultiesTO() throws SQLException {
        List<Faculty> listFaculties = new ArrayList<>();

        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(Constants.SQL_FIND_ALL_FACULTIES)) {
            FacultyMapper facultyMapper= new FacultyMapper();
            while (rs.next()) {
                listFaculties.add(facultyMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
              throw new SQLException("Cannot get all faculties!", ex);
        }
        return listFaculties;


    }



}
