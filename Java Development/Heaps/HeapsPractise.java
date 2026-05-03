package Heaps;

import java.util.*;

public class HeapsPractise {
    public  static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int[] res = new int[k];
        int j=0;
        for (int val : map.keySet()) {
            if(j>=k) break;
            else {
                res[j] = map.get(val);
            }
            j++;
        }
        return res;

    }
    public static int kthSmallest(int[] nums, int k) {
        // Your code here
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            queue.offer(nums[i]);
            if(queue.size()>k) {
                queue.poll();
            }
        }

        return queue.peek();
    }
    public static int[] topKFrequentBrute(int[] nums, int k) {
        // Step 1: Count frequencies
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(countMap.entrySet());
        System.out.println(entryList);
        entryList.sort((a, b) -> b.getValue() - a.getValue());

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = entryList.get(i).getKey();
        }

        return result;
    }
    public static void main(String[] args) {
//        PriorityQueue<Integer> queue = new PriorityQueue<>();
//        queue.add(20);
//        queue.add(15);
//        queue.add(40);
//        queue.add(7);
//        queue.add(10);
//        queue.add(30);
//        queue.add(17);
//        queue.add(21);
//        System.out.println(queue);
        int[] nums = {3,1,1,1,2,2};
        topKFrequentBrute(nums,3);
    }
}
