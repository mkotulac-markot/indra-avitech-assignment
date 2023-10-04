package sk.markot.assignment.db;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public final class DbConnection implements AutoCloseable {

    private static DbConnection instance;

    private final Connection connection;

    private DbConnection(Connection connection) {
        this.connection = connection;
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            try {
                String dbUrl = DbPropertiesLoader.getDbUrl();
                Connection connection = DriverManager.getConnection(dbUrl);
                instance = new DbConnection(connection);

                System.out.println("DB connection created");
            } catch (SQLException e) {
                System.err.println("Cannot create DB connection");
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
        System.out.println("DB connection closed");
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public void initDb() {
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        String scriptFileName = DbPropertiesLoader.getDbInitScriptFileName();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(scriptFileName)) {
            InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(inputStream));
            scriptRunner.runScript(inputStreamReader);
        } catch (IOException e) {
            System.err.println("Cannot read init DB script file");
            throw new RuntimeException(e);
        }

    }
}
