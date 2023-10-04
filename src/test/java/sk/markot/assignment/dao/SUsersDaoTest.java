package sk.markot.assignment.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.markot.assignment.db.DbConnection;
import sk.markot.assignment.entity.SUser;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SUsersDaoTest {

    private static DbConnection connection;

    private SUsersDao dao;

    @BeforeAll
    static void beforeAll() {
        connection = DbConnection.getInstance();
        connection.initDb();
    }

    @AfterAll
    static void afterAll() throws SQLException {
        connection.close();
    }

    @BeforeEach
    void setUp() {
        dao = new SUsersDao(connection);
        dao.deleteAll();
    }

    @Test
    void testAdd() {
        SUser user = new SUser(1L, "1", "User 1");
        dao.add(user);

        List<SUser> users = dao.findAll();

        assertThat(users).isNotEmpty().hasSize(1);
        assertThat(users.get(0).id()).isEqualTo(1L);
        assertThat(users.get(0).guid()).isEqualTo("1");
        assertThat(users.get(0).name()).isEqualTo("User 1");
    }

    @Test
    void testFindAll() {
        SUser user1 = new SUser(1L, "1", "User 1");
        SUser user2 = new SUser(2L, "2", "User 2");
        dao.add(user1);
        dao.add(user2);

        List<SUser> users = dao.findAll();

        assertThat(users).isNotEmpty().hasSize(2);
    }

    @Test
    void testDeleteAll() {
        SUser user = new SUser(1L, "1", "User 1");
        dao.add(user);
        dao.deleteAll();

        List<SUser> users = dao.findAll();

        assertThat(users).isEmpty();
    }
}