package DAO;

import entity.Candidate;
import entity.CandidateProfile;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface CandidateDAO {
    void insertCandidate(Candidate candidate, CandidateProfile candidateProfile) throws SQLException;


    boolean deleteCandidate(Long id);

    Optional<Candidate> findCandidateById(Long id) throws SQLException;

    Candidate findCandidateByUsername(String username);

    boolean updateCandidate(String role,String candidateStatus,Long id);

    void updateCandidateProfile(CandidateProfile candidateProfile) throws SQLException;


    List<Candidate> getAllCandidates() throws SQLException;

    Optional<CandidateProfile> getCandidateProfile(Candidate candidate) throws SQLException;
}
