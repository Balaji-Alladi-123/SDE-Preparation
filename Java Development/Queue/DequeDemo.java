package Queue;

import java.util.*;

public class DequeDemo {
    public static void main(String[] args) {
//        Queue<List<Integer>> deque = new ArrayDeque<>();
//        deque.offer(Arrays.asList(2,3,4));
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(7);
        queue.offer(6);
        queue.offer(1);
        queue.offer(0);
        queue.peek();
        System.out.println(queue.peek());
    }
}
