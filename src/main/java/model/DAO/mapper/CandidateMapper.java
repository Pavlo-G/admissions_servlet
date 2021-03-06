package model.DAO.mapper;

import model.entity.Candidate;
import model.entity.CandidateStatus;
import model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


public class CandidateMapper implements ObjectMapper<Candidate> {


    public Candidate extractFromResultSet(ResultSet rs) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setId(rs.getLong("c.id"));
        candidate.setUsername(rs.getString("username"));
        candidate.setPassword(rs.getString("password"));
        candidate.setRole(Role.valueOf(rs.getString("role")));
        candidate.setCandidateStatus(CandidateStatus.valueOf(rs.getString("candidate_status")));
        return candidate;
    }

    @Override
    public Candidate makeUnique(Map<Long, Candidate> cache, Candidate entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
