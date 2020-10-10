package DAO;

import entity.Candidate;
import entity.CandidateProfile;

import javax.sql.RowSet;
import java.util.Collection;


public interface CandidateDAO {
    int insertCandidate(Candidate candidate);

    int insertCandidateProfile(CandidateProfile candidateProfile);

    boolean deleteCandidate();

    Candidate findCandidateById(int id);

    Candidate findCandidateByLogin(String login);

    boolean updateCandidate();

    RowSet selectCandidatesRS();

    Collection<Candidate> getAllCandidatesTO();

}