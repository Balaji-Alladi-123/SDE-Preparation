package Heaps;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class HeapDemo {

    public static void main(String[] args) throws Exception {
        Heap<Integer> heap = new Heap<>();
        heap.insert(45);
        heap.insert(4);
        heap.insert(15);
        heap.insert(100);
        heap.insert(1);
        heap.insert(10);
        System.out.println(heap.list);
    }
}
