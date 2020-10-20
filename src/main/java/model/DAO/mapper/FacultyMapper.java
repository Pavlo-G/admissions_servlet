package model.DAO.mapper;

import model.entity.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class FacultyMapper implements ObjectMapper<Faculty> {
    @Override
    public Faculty extractFromResultSet(ResultSet rs) throws SQLException {

        Faculty faculty = new Faculty();
        faculty.setId(rs.getLong("f.id"));
        faculty.setNameEn(rs.getString("name_en"));
        faculty.setNameUk(rs.getString("name_uk"));
        faculty.setDescriptionEn(rs.getString("description_en"));
        faculty.setDescriptionUk(rs.getString("description_uk"));
        faculty.setBudgetCapacity(rs.getInt("budget_capacity"));
        faculty.setTotalCapacity(rs.getInt("total_capacity"));
        faculty.setRequiredSubject1En(rs.getString("req_subject1_en"));
        faculty.setRequiredSubject1Uk(rs.getString("req_subject1_uk"));
        faculty.setRequiredSubject2En(rs.getString("req_subject2_en"));
        faculty.setRequiredSubject2Uk(rs.getString("req_subject2_uk"));
        faculty.setRequiredSubject3En(rs.getString("req_subject3_en"));
        faculty.setRequiredSubject3Uk(rs.getString("req_subject3_uk"));
        faculty.setAdmissionOpen(rs.getBoolean("admission_open"));

        return faculty;
    }

    @Override
    public Faculty makeUnique(Map<Long, Faculty> cache, Faculty entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
