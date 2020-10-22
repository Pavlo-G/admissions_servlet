package model.DAO;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    void create (T entity) throws SQLException;
    T findById(Long id)throws SQLException;
    List<T> findAll()throws SQLException;
    void update(T entity) throws SQLException;
    void delete(Long id) throws SQLException;
    void close()throws SQLException;
}
