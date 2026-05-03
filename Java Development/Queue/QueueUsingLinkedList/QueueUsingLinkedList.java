package Queue.QueueUsingLinkedList;

public class QueueUsingLinkedList {
    Node rear, front;

    QueueUsingLinkedList() {
        rear = null;
        front = null;
    }

    void enQueue(int x) {
        Node newNode = new Node(x);
        if (rear == null) {
           rear = front = newNode;
           return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    void deQueue() {
        if(isEmpty()) {
            System.out.println("queue is underflow");
            return;
        }
        Node temp = front;
        front = front.next;
        if( front == null) {
            rear = null;
        }
    }

    int getRear() {
      if(isEmpty()) {
          System.out.println("queue is empty");
      }
      return rear.data;
    }

    int getFront() {
        if(isEmpty()) {
            System.out.println("queue is empty");
        }
        return front.data;
    }

    boolean isEmpty() {
      return rear == null &&  front == null;
    }

    public static void main(String[] args) {
        QueueUsingLinkedList queue = new QueueUsingLinkedList();
        queue.enQueue(10);
        queue.enQueue(20);
        queue.enQueue(30);
        queue.deQueue();
        System.out.println(queue.front.data);
    }
}

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
