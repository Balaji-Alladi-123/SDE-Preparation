package MultiThreading;

public class createThread{
    public static void main(String[] args) {
        System.out.println("Thread name:" + Thread.currentThread().getName());
    }
}

class OwnThread extends Thread {
    static Object lock = new Object();
   OwnThread(String name) {
       super(name);
   }
    public void run() {
        synchronized (lock) {
            System.out.println(getName() + "is Running waiting");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                System.out.println("error");
            }
            System.out.println("Resume Thread"+ getName());
        }
   }

    public static void main(String[] args) throws InterruptedException {
        OwnThread ownThread1 = new OwnThread("first");
        OwnThread ownThread2 = new OwnThread("second");
        ownThread1.start();
        ownThread2.start();
        Thread.sleep(10);
        Thread notifier = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Notifier is notifying one thread..."+ Thread.currentThread().getName());
                lock.notifyAll(); // Only one of the waiting threads will be resumed
            }
        });
        notifier.start(); // Start notifier thread
    }
}

class myRunning implements Runnable {

    @Override
    public void run() {

    }
}

class child extends Thread {

}