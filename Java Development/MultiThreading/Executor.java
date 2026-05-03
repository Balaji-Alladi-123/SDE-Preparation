package MultiThreading;

import java.util.concurrent.*;

public class Executor {

    public static void main(String[] args) {
       // forkJoinExample();
        //executorExample();
        compatibleFutureExample();
    }

    public static void executorExample() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Runnable task = () -> {
            try {
                System.out.println("Running: " + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 15; i++) {
            executorService.execute(task);
        }
        // Proper shutdown
        executorService.shutdown();
    }
/**
 * main() calls forkJoinExample()
 *     Tasks are submitted
 *     main() finishes immediately
 *     JVM exits
 *     Daemon threads are killed ❌
 *     No output appears
 */

    public static void forkJoinExample() {
        ForkJoinPool pool = new ForkJoinPool(3);
        Runnable task = () -> {
            try {
                System.out.println("Running Forkjoin pool: " + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        for(int i=0;i<5;i++) {pool.execute(task);}
    }

    public static void compatibleFutureExample() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Runnable task = () -> {
            try {
                System.out.println("Running completable future: " + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        for(int i=0;i<5;i++) {
            CompletableFuture.runAsync(task,executor);
        }
    }
}
