package model.DAO;


import utils.util.FacultyPage;
import model.entity.Faculty;

import java.sql.SQLException;


public interface FacultyDAO extends GenericDao<Faculty>{


    boolean changeAdmissionOpenStatus(String action, Long facultyId) throws SQLException;

    FacultyPage findAllSorted(String name, String direction, int page, int itemsPerPage) throws SQLException;
}
