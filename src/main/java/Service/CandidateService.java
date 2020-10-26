package Service;

import exception.CandidateNotFoundException;
import exception.DbProcessingException;
import model.DAO.CandidateDAO;
import model.DAO.DAOFactory;
import model.entity.Candidate;
import model.entity.CandidateProfile;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CandidateService {
    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);


    public List<Candidate> findAll() {

        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            return dao.findAll();
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void delete(Long candidateId) {
        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            dao.delete(candidateId);
        } catch (SQLException e) {
            throw new DbProcessingException("Can not delete candidate with id:"+candidateId);
        }
    }

    public void update(String role, String candidateStatus, Long candidateId) {
        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            dao.updateCandidate(role, candidateStatus, candidateId);
        } catch (SQLException e) {
            throw new DbProcessingException("Can not update  status of candidate with id: " + candidateId);
        }
    }

    public Candidate findById(Long candidateId) {
        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            return dao.findById(candidateId)
                    .orElseThrow(()-> new CandidateNotFoundException("Can not find candidate with id: "+candidateId));
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public Optional<CandidateProfile> getCandidateProfile(Candidate candidate) {
        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            return dao.getCandidateProfile(candidate);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void updateCandidateProfile(CandidateProfile candidateProfile) {
        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            dao.updateCandidateProfile(candidateProfile);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public Optional<Candidate> findCandidateByUsername(String username) {
        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            return dao.findCandidateByUsername(username);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }
}
