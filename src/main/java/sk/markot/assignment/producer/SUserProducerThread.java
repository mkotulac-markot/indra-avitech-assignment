package sk.markot.assignment.producer;

import sk.markot.assignment.entity.SUser;
import sk.markot.assignment.service.SUserService;

import java.util.List;

public class SUserProducerThread extends Thread {

    public SUserProducerThread(SUserService userService, List<SUser> users) {
        super(new SUserProducer(userService, users), "Thread-Producer");
    }

    private static final class SUserProducer implements Runnable {

        private final SUserService userService;
        private final List<SUser> users;

        public SUserProducer(SUserService userService, List<SUser> users) {
            this.userService = userService;
            this.users = users;
        }

        @Override
        public void run() {
            try {
                synchronized (userService) {
                    while (userService.count() == users.size()) {
                        userService.wait();
                    }

                    for (SUser user : users) {
                        userService.add(user);
                    }

                    userService.printAll();
                    userService.notify();
                }
            } catch (InterruptedException e) {
                System.err.println("Thread execution exception: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}