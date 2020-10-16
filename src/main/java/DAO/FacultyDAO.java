package DAO;


import dto.FacultyDTO;
import entity.Faculty;

import java.sql.SQLException;
import java.util.List;


public interface FacultyDAO {

    public int createFaculty(Faculty faculty);

    public boolean deleteFaculty(Long id) throws SQLException;

    public Faculty findFaculty(Long id);

    public boolean updateFaculty(Faculty faculty);


    public List<Faculty> getAllFaculties() throws SQLException;

    boolean changeAdmissionOpenStatus(String action, Long facultyId);

    FacultyDTO getAllFaculties2(String name, String direction, int page, int itemsPerPage) throws SQLException;
}
