package DAO.mapper;

import entity.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class FacultyMapper implements ObjectMapper<Faculty> {
    @Override
    public Faculty extractFromResultSet(ResultSet rs) throws SQLException {

        Faculty faculty = new Faculty();
        faculty.setId(rs.getLong("f.id"));
        faculty.setName(rs.getString("name"));
        faculty.setDescription(rs.getString("description"));
        faculty.setBudgetCapacity(rs.getInt("budget_capacity"));
        faculty.setTotalCapacity(rs.getInt("total_capacity"));
        faculty.setRequiredSubject1(rs.getString("req_subject1"));
        faculty.setRequiredSubject2(rs.getString("req_subject2"));
        faculty.setRequiredSubject3(rs.getString("req_subject3"));
        faculty.setAdmissionOpen(rs.getBoolean("admission_open"));

        return faculty;
    }

    @Override
    public Faculty makeUnique(Map<Long, Faculty> cache, Faculty entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
