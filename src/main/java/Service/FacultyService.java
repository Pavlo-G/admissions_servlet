package Service;

import exception.DbProcessingException;
import model.DAO.DAOFactory;
import model.DAO.FacultyDAO;
import model.dto.FacultyListDTO;
import model.entity.Faculty;


import java.sql.SQLException;
import java.util.List;

public class FacultyService {
    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);

    public List<Faculty> findAll() {
        try (FacultyDAO dao = daoFactory.getFacultyDAO()) {
            return dao.findAll();
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void changeAdmissionOpenStatus(String action, Long facultyId) {

        try {
            daoFactory.getFacultyDAO().changeAdmissionOpenStatus(action, facultyId);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }


    public void create(Faculty faculty) {
        try {
            daoFactory.getFacultyDAO().create(faculty);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public Faculty findById(Long id) {
        try {
            return daoFactory.getFacultyDAO().findById(id);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void update(Faculty faculty) {

        try {
            daoFactory.getFacultyDAO().update(faculty);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            daoFactory.getFacultyDAO().delete(id);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }


    FacultyListDTO findAllSorted(String name, String direction, int page, int itemsPerPage) {
        return null;
    }

}
