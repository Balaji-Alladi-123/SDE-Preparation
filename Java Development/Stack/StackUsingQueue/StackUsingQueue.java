package Stack.StackUsingQueue;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {
    Queue<Integer> queue = new LinkedList<>();

    void push(int val) {
        int l = queue.size();
        queue.add(val);
        for (int i = 0; i < l; i++) {
            queue.add(queue.remove());
        }
    }

    void pop() {
        if (queue.isEmpty()) {
            return;
        }
        queue.remove();
    }

    int top() {
        if (queue.isEmpty()) {
            return -1;
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        StackUsingQueue stack = new StackUsingQueue();
        stack.push(9);
        stack.push(10);
        stack.push(23);
        stack.push(90);
        stack.pop();
        System.out.println(stack.top());

    }
}

//
//    static Queue<Integer> q1
//            = new LinkedList<Integer>();
//    static Queue<Integer> q2
//            = new LinkedList<Integer>();
//
//    // To maintain current number of
//    // elements
//    static int curr_size;
//
//    static void push(int x)
//    {
//        // Push x first in empty q2
//        q2.add(x);
//
//        // Push all the remaining
//        // elements in q1 to q2.
//        while (!q1.isEmpty()) {
//            q2.add(q1.peek());
//            q1.remove();
//        }
//
//        // swap the names of two queues
//        Queue<Integer> q = q1;
//        q1 = q2;
//        q2 = q;
//    }