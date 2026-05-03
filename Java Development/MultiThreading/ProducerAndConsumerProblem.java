package MultiThreading;

import java.util.ArrayList;
import java.util.LinkedList;


class ProducerAndConsumer {
    LinkedList<Integer> list = new LinkedList<>();

    public void produce() throws InterruptedException {
        int counter = 0;
        while (true) {
            synchronized (this) {
                if (list.size() == 3) {
                    System.out.println("list is full, I am going to sleep");
                    notify();
                    wait();
                }
                list.add(counter);
                System.out.println("Producer produced-" + counter);
                counter++;
                notify();
                Thread.sleep(1000);
            }
        }
    }

    public void consumer() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // consumer thread waits while list is empty
                if (list.size() == 0) {
                    System.out.println("List is empty, consumer is waiting...");
                    // Signal any waiting producer before waiting
                    notify();
                    wait();
                }

                // to retrieve the first job in the list
                int val = list.removeFirst();
                System.out.println("Consumer consumed-" + val);

                // Wake up producer thread
                notify();

                // and sleep
                Thread.sleep(1000);
            }
        }
    }
}

public class ProducerAndConsumerProblem {
    public static void main(String[] args) throws InterruptedException {
        ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerAndConsumer.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerAndConsumer.consumer();
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
