package Heaps.leetcodeProblems;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {
    static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        int n = stones.length;
        if(n == 1) {
            return stones[0];
        }
        for (int i = 0; i < n; i++) {
            queue.offer(stones[i]);
        }

        while(queue.size()>1) {
            int x = queue.poll();
            int y = queue.poll();
            if(x==y) {
                continue;
            }
            if(x!=y) {
                queue.offer(Math.abs(x-y));
            }
        }
        return queue.peek();
    }
    static String reorganizeString(String s) {
        StringBuilder sb = new StringBuilder(s);
        int n = s.length();
        for(int i=0;i<n;i++) {
            int j= i+1;
            while(j<n && sb.charAt(i) == sb.charAt(j)) {
                j++;
            }
            if(j>=n) {
                return "";
            }
            char t = sb.charAt(j);
            sb.setCharAt(j,sb.charAt(i+1));
            sb.setCharAt(i+1,t);
            i++;
        }
        return sb.toString();
    }

    static int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        TaskSchedule[] ts = new TaskSchedule[n];

        for (int i = 0; i < n; i++) {
            ts[i] = new TaskSchedule(tasks[i][0], tasks[i][1], i);
        }

        // Sort by enqueue time
        Arrays.sort(ts, (a, b) -> a.eTime - b.eTime);

        PriorityQueue<TaskSchedule> queue = new PriorityQueue<>(
                (a, b) -> a.pTime == b.pTime ? a.idx - b.idx : a.pTime - b.pTime
        );

        int[] res = new int[n];
        int k = 0, i = 0;
        long time = 0;

        while (!queue.isEmpty() || i < n) {
            // If queue is empty, jump time to the next available task
            if (queue.isEmpty()) {
                time = Math.max(time, ts[i].eTime);
            }

            // Add all tasks that have arrived by current time
            while (i < n && ts[i].eTime <= time) {
                queue.offer(ts[i]);
                i++;
            }

            if (!queue.isEmpty()) {
                TaskSchedule t = queue.poll();
                time += t.pTime;
                res[k++] = t.idx;
            }
        }
        return res;
    }
    public static void main(String[] args) {
//        int[] nums = {2, 2};
//        lastStoneWeight(nums);
       // reorganizeString("aab");
        int[][] tasks = {{1,2},{2,4},{3,2},{4,1}};
        getOrder(tasks);
    }
}
class TaskSchedule {
    int eTime;  // Enqueue time
    int pTime;  // Processing time
    int idx;    // Original index

    TaskSchedule(int eTime, int pTime, int idx) {
        this.eTime = eTime;
        this.pTime = pTime;
        this.idx = idx;
    }
}