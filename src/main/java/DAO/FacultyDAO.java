package DAO;


import entity.Faculty;

import java.sql.SQLException;
import java.util.List;


public interface FacultyDAO {

    public int createFaculty(Faculty fcaulty);

    public boolean deleteFaculty();

    public Faculty findFaculty(Long id);

    public boolean updateFaculty(Faculty faculty);


    public List<Faculty> getAllFacultiesTO() throws SQLException;

}
