package MultiThreading;

import java.util.concurrent.*;

public class ExecutorServiceDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i=0;i<10;i++) {
            Runnable task = () -> {
                System.out.println("Current Thread"+ Thread.currentThread().getName());
            };
            executorService.execute(task);
        }

        Callable<String> demo = () -> {
            Thread.sleep(1000);
            return "Result from Callable";
        };
        Future<String> future = executorService.submit(demo);
        System.out.println("Waiting for result...");
        String result = future.get();  // Blocking call
        System.out.println("Result: " + result);
        executorService.shutdown();
    }
}
