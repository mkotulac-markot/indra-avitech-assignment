package sk.markot.assignment.db;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DbPropertiesLoaderTest {

    @Test
    void testGetDbUrl() {
        String dbUrl = DbPropertiesLoader.getDbUrl();

        assertThat(dbUrl).isEqualTo("jdbc:h2:mem:myDb");
    }

    @Test
    void testGetDbInitScriptFileName() {
        String dbInitScriptFileName = DbPropertiesLoader.getDbInitScriptFileName();

        assertThat(dbInitScriptFileName).isEqualTo("test-db-init.sql");
    }
}