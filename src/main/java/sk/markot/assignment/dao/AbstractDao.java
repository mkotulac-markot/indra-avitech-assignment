package sk.markot.assignment.dao;

import sk.markot.assignment.db.DbConnection;
import sk.markot.assignment.entity.Entity;

public abstract class AbstractDao<T extends Entity> implements Dao<T> {

    private final DbConnection connection;

    public AbstractDao(DbConnection connection) {
        this.connection = connection;
    }

    protected DbConnection getConnection() {
        return connection;
    }
}
