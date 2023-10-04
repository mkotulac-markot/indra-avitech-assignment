package sk.markot.assignment.consumer;

import sk.markot.assignment.service.SUserService;

public class SUserConsumerThread extends Thread {

    public SUserConsumerThread(SUserService userService) {
        super(new SUserConsumer(userService), "Thread-Consumer");
    }

    private static final class SUserConsumer implements Runnable {

        private final SUserService userService;

        public SUserConsumer(SUserService userService) {
            this.userService = userService;
        }

        @Override
        public void run() {
            try {
                synchronized (userService) {
                    while (userService.count() == 0) {
                        userService.wait();
                    }

                    userService.deleteAll();
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
