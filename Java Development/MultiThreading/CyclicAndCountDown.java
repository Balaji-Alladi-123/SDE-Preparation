package MultiThreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicAndCountDown {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("All thread reaches same point barrier");
        });

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " waiting");
            try {
                cyclicBarrier.await();  // Wait until 3 threads reach here
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " passed the barrier");
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();

        CountDownLatch latch = new CountDownLatch(3);

        Runnable worker = () -> {
            System.out.println("Worker done");
            latch.countDown();
        };

        new Thread(worker).start();
        new Thread(worker).start();
        new Thread(worker).start();
    }
}
