package DAO.mapper;

import entity.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class FacultyMapper implements ObjectMapper<Faculty> {
    @Override
    public Faculty extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Faculty makeUnique(Map<Integer, Faculty> cache, Faculty entity) {
        return null;
    }
}
