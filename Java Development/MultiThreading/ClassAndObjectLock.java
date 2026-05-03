package MultiThreading;

public class ClassAndObjectLock {
    public synchronized static void doSomething() {
        System.out.println("hello");
    }
    public static void main(String[] args) {
        ClassAndObjectLock classLock = new ClassAndObjectLock();
     Thread thread1 = new Thread(ClassAndObjectLock::doSomething);
     Thread thread2 = new Thread(ClassAndObjectLock::doSomething);
     thread1.start();
     thread2.start();
    }
}

