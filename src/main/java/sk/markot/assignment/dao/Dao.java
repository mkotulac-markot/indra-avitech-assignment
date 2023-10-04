package sk.markot.assignment.dao;

import sk.markot.assignment.entity.Entity;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T extends Entity> {
    void add(T entity) throws SQLException;
    List<T> findAll();
    void deleteAll();
}
