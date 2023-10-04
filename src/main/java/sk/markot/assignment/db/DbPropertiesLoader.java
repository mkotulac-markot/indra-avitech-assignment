package sk.markot.assignment.db;

import java.io.IOException;
import java.util.Properties;

public final class DbPropertiesLoader {

    private static final Properties properties;

    private static final String DB_CONFIG_FILE = "db-config.properties";

    static {
        try {
            properties = new Properties();
            properties.load(DbPropertiesLoader.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE));
        } catch (IOException e) {
            System.err.println("Cannot read properties file. Exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static String getDbUrl() {
        return properties.getProperty("dbUrl");
    }

    public static String getDbInitScriptFileName() {
        return properties.getProperty("dbInitScriptFileName");
    }
}
