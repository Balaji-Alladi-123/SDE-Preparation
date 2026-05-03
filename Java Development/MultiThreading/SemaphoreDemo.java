package MultiThreading;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        Runnable task = () -> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " acquired permit");
                Thread.sleep(1000); // Simulate work
                System.out.println(Thread.currentThread().getName() + " releasing permit");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        for(int i=0;i<10;i++) {
            new Thread(task).start();
        }
    }
}
