package sk.markot.assignment.db;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DbConnectionTest {

    @Test
    void testGetInstance() {
        DbConnection dbConnection = DbConnection.getInstance();

        assertThat(dbConnection).isNotNull();
    }
}