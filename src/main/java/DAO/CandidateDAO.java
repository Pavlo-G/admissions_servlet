package DAO;

import entity.Candidate;
import entity.CandidateProfile;

import javax.sql.RowSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;


public interface CandidateDAO {
    void insertCandidate(Candidate candidate, CandidateProfile candidateProfile) throws SQLException;


    boolean deleteCandidate();

    Candidate findCandidateById(int id);

    Candidate findCandidateByUsername(String username);

    boolean updateCandidate();

    void updateCandidateProfile(CandidateProfile candidateProfile) throws SQLException;

    RowSet selectCandidatesRS();

    Collection<Candidate> getAllCandidatesTO();

    Optional<CandidateProfile> getCandidateProfile(Candidate candidate);
}
