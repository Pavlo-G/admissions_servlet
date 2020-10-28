package model.DAO.mapper;

import model.entity.CandidateProfile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CandidateProfileMapper implements ObjectMapper<CandidateProfile> {


    @Override
    public CandidateProfile extractFromResultSet(ResultSet rs) throws SQLException {
        CandidateProfile candidateProfile = new CandidateProfile();
        candidateProfile.setId(rs.getLong("cp.id"));
        candidateProfile.setFirstName(rs.getString("first_name"));
        candidateProfile.setLastName(rs.getString("last_name"));
        candidateProfile.setEmail(rs.getString("email"));
        candidateProfile.setAddress(rs.getString("address"));
        candidateProfile.setCity(rs.getString("city"));
        candidateProfile.setRegion(rs.getString("region"));
        candidateProfile.setSchool(rs.getString("school"));
        candidateProfile.setPhoneNumber(rs.getString("phone_number"));
        candidateProfile.setFileName(rs.getString("certificate_file"));
        return candidateProfile;
    }

    @Override
    public CandidateProfile makeUnique(Map<Long, CandidateProfile> cache, CandidateProfile entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
