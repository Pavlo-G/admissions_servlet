package model.DAO;


import model.dto.FacultyDTO;
import model.entity.Faculty;

import java.sql.SQLException;
import java.util.List;


public interface FacultyDAO {

    public int createFaculty(Faculty faculty) throws SQLException;

    public boolean deleteFaculty(Long id) throws SQLException;

    public Faculty findFaculty(Long id) throws SQLException;

    public boolean updateFaculty(Faculty faculty) throws SQLException;


    public List<Faculty> getAllFaculties() throws SQLException;

    boolean changeAdmissionOpenStatus(String action, Long facultyId) throws SQLException;

    FacultyDTO getAllFaculties2(String name, String direction, int page, int itemsPerPage) throws SQLException;
}
