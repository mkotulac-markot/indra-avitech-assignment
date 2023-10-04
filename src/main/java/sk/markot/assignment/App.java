package sk.markot.assignment;

import sk.markot.assignment.consumer.SUserConsumerThread;
import sk.markot.assignment.dao.SUsersDao;
import sk.markot.assignment.db.DbConnection;
import sk.markot.assignment.entity.SUser;
import sk.markot.assignment.producer.SUserProducerThread;
import sk.markot.assignment.service.SUserService;

import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) {
        try (DbConnection connection = DbConnection.getInstance()) {
            connection.initDb();

            SUsersDao dao = new SUsersDao(connection);
            SUserService userService = new SUserService(dao);
            List<SUser> users = List.of(
                    new SUser(1L, "a1", "Robert"),
                    new SUser(2L, "a2", "Martin"));

            SUserProducerThread producerThread = new SUserProducerThread(userService, users);
            SUserConsumerThread consumerThread = new SUserConsumerThread(userService);

            producerThread.start();
            consumerThread.start();

            producerThread.join();
            consumerThread.join();

        } catch (SQLException | InterruptedException e) {
            System.err.println("Exception caught in runtime: " + e.getMessage());
            System.exit(1);
        }
    }
}
