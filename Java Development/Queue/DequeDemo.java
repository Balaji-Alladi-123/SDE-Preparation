package Queue;

import java.net.StandardSocketOptions;
import java.sql.SQLOutput;
import java.util.*;

public class DequeDemo {
    public static void main(String[] args) {
        System.out.println(shortestSubarray(new int[]{2,-1,1,3},4));
    }
    public static int shortestSubarray(int[] nums, int k){
        int n = nums.length;
        int l = 0, r = 0, sum = 0, min = Integer.MAX_VALUE;
        Deque<Pair> queue = new ArrayDeque<>();
        while (l < n) {
            sum += nums[l];
            while(!queue.isEmpty() && sum- queue.peekFirst().prefixSum>=k) {
                min = Math.min(min,(l-queue.peekFirst().index));
                queue.pollFirst();
            }
            while(!queue.isEmpty() && queue.peekLast().prefixSum > nums[l]) {
                queue.pollLast();
            }
            queue.offer(new Pair(sum, l));
            l++;
        }
        return min == Integer.MAX_VALUE ? -1:min;
    }
}

class Pair {
    int prefixSum;
    int index;
    Pair(int prefixSum, int index) {
        this.prefixSum = prefixSum;
        this.index = index;
    }
}
