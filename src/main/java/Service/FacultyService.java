package Service;

import model.DAO.DAOFactory;
import model.DAO.FacultyDAO;
import model.entity.Faculty;

import java.sql.SQLException;
import java.util.List;

public class FacultyService {
    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);

    public List<Faculty> getAllFaculties() throws Exception {
        try (FacultyDAO dao = daoFactory.getFacultyDAO()) {
            return dao.getAllFaculties();
        } catch (SQLException e) {
            throw new Exception();
        }
    }


}
