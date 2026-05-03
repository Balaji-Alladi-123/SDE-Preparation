package MultiThreading;

public class ThreadLifeCycle {

    public static void main(String[] args) throws InterruptedException {

        // 1. NEW state: Thread created but not started
        Thread newThread = new Thread(() -> System.out.println("Thread running..."));
        System.out.println("State after creation (NEW): " + newThread.getState());

        // 2. RUNNABLE state: Thread started, might be running or ready
        newThread.start();
        Thread.sleep(100); // Give it time to run
        System.out.println("State after start (RUNNABLE or TERMINATED): " + newThread.getState());

        // 3. BLOCKED state: Thread trying to enter synchronized block, but lock is held
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(2000); // Hold lock for 2 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread2 acquired the lock.");
            }
        });

        thread1.start();
        Thread.sleep(50); // Make sure thread1 acquires the lock
        thread2.start();
        Thread.sleep(50); // Let thread2 attempt to acquire the lock

        System.out.println("State of thread2 (BLOCKED): " + thread2.getState());

        // 4. WAITING state: Thread waiting indefinitely (e.g., using wait())
        Thread waitingThread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait(); // Wait indefinitely
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        waitingThread.start();
        Thread.sleep(50); // Let it enter wait()
        System.out.println("State of waitingThread (WAITING): " + waitingThread.getState());

        // 5. TIMED_WAITING: Thread sleeping or using join with timeout
        Thread timedWaitingThread = new Thread(() -> {
            try {
                Thread.sleep(3000); // Sleep for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        timedWaitingThread.start();
        Thread.sleep(50); // Let it start sleeping
        System.out.println("State of timedWaitingThread (TIMED_WAITING): " + timedWaitingThread.getState());

        // 6. TERMINATED: Thread finished execution
        Thread terminatedThread = new Thread(() -> System.out.println("I will terminate quickly"));
        terminatedThread.start();
        terminatedThread.join(); // Wait for it to finish
        System.out.println("State of terminatedThread (TERMINATED): " + terminatedThread.getState());
    }
}

