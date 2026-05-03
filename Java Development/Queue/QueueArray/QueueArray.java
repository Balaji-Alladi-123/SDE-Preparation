package Queue.QueueArray;

public class QueueArray {
    int start, end, currSize, maxSize;
    int[] arr;

    QueueArray() {
        arr = new int[10];
        start = -1;
        end = -1;
    }

    QueueArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        start = -1;
        end = -1;
    }

    void push(int ele) {
        if (currSize == maxSize) {
            System.out.println("stack is full");
        }
        if (end == -1) {
            end = 0;
            start = 0;
        } else {
            end = (end + 1) % maxSize;
        }
        arr[end] = ele;
        System.out.println("element pushed into the stack");
        currSize++;
    }

    int pop() {
        if (start == -1) {
            System.out.println("queue is full/underflow");
        }
        int popedEle = arr[start];
        if (currSize == 1) {
            start = -1;
            end = -1;
        } else {
            start = (start + 1) % maxSize;
            currSize--;
            System.out.println("element poped");
        }
        return popedEle;
    }

    public int top() {
        if (start == -1) {
            System.out.println("Queue is Empty");
            System.exit(1);
        }
        return arr[start];
    }

    static class test {
        public static void main(String[] args) {
            QueueArray queue = new QueueArray(23);
            queue.push(89);
            queue.push(90);
            queue.push(100);
            System.out.println(queue.pop());
        }
    }
}
