package sk.markot.assignment.dao;

import sk.markot.assignment.db.DbConnection;
import sk.markot.assignment.entity.SUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SUsersDao extends AbstractDao<SUser> {

    public SUsersDao(DbConnection connection) {
        super(connection);
    }

    @Override
    public void add(SUser entity) {
        String sql = "INSERT INTO SUSERS (USER_ID, USER_GUID, USER_NAME) VALUES (?,?,?)";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, entity.id());
            preparedStatement.setString(2, entity.guid());
            preparedStatement.setString(3, entity.name());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Cannot insert entity to DB: " + entity);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SUser> findAll() {
        String sql = "SELECT * FROM SUSERS";

        List<SUser> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SUser sUser = new SUser(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3));

                result.add(sUser);
            }
        } catch (SQLException e) {
            System.err.println("Cannot read from DB");
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM SUSERS";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Cannot delete records in DB");
            throw new RuntimeException(e);
        }
    }
}
