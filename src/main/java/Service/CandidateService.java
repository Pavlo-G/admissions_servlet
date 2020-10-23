package Service;

import controller.command.Command;
import exception.CandidateNotFoundException;
import exception.DbProcessingException;
import model.DAO.DAOFactory;
import model.entity.Candidate;
import model.entity.Role;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CandidateService {
    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);


    public List<Candidate> findAll() {

        try {
            return daoFactory.getCandidateDAO().findAll();
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void delete(Long candidateId) {
        try {
            daoFactory.getCandidateDAO().delete(candidateId);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void update(String role, String candidateStatus, Long candidateId)  {
        try {
            daoFactory.getCandidateDAO().updateCandidate(role, candidateStatus, candidateId);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public Candidate findById(Long candidateId) {
        try {
         return  daoFactory.getCandidateDAO().findCandidateById(candidateId).orElseThrow(CandidateNotFoundException::new);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }
}
