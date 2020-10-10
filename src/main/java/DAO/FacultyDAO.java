package DAO;


import entity.Faculty;

import javax.sql.RowSet;
import java.sql.SQLException;
import java.util.Collection;


public interface FacultyDAO {

    public int createFaculty();

    public boolean deleteFaculty();

    public Faculty findFaculty(int id);

    public boolean updateFaculty();

    public RowSet selectFacultyRS();

    public Collection<Faculty> getAllFacultiesTO() throws SQLException;

}
