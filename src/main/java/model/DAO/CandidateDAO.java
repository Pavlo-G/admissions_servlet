package model.DAO;

import model.entity.Candidate;
import model.entity.CandidateProfile;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface CandidateDAO extends GenericDao<Candidate> {
    void insertCandidate(Candidate candidate, CandidateProfile candidateProfile) throws SQLException;





    Optional<Candidate> findCandidateByUsername(String username) throws SQLException;

    boolean updateCandidate(String role,String candidateStatus,Long id) throws SQLException;

    void updateCandidateProfile(CandidateProfile candidateProfile) throws SQLException;


    List<Candidate> getAllCandidates() throws SQLException;

    Optional<CandidateProfile> getCandidateProfile(Candidate candidate) throws SQLException;
}
