package DAO.mapper;

import entity.Candidate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CandidateMapper implements ObjectMapper<Candidate> {
    @Override
    public Candidate extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Candidate makeUnique(Map<Integer, Candidate> cache, Candidate entity) {
        return null;
    }
}
