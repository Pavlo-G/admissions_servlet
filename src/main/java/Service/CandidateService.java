package Service;

import controller.command.Command;
import exception.DbProcessingException;
import model.DAO.DAOFactory;
import model.entity.Candidate;

import java.sql.SQLException;
import java.util.List;

public class CandidateService {
    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);


    public List<Candidate> findAll() {

        try {
            return daoFactory.getCandidateDAO().findAll();
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }
}
