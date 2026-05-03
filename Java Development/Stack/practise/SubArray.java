package Stack.practise;

import java.util.Arrays;
import java.util.Stack;

public class SubArray {
    //    static int sumSubarrayMins(int[] arr) {
//        int mod = 1000000007;
//        int n = arr.length;
//        Stack<Integer> s = new Stack<>();
//        int[] left = new int[n];
//        int[] right = new int[n];
//        for (int i = 0; i < n; i++) {
//            while (!s.empty() && arr[s.peek()] >= arr[i]) {
//                s.pop();
//            }
//            if (s.empty()) {
//                left[i] = -1;
//            } else {
//                left[i] = s.peek();
//            }
//            s.add(i);
//        }
//
//        s.clear();
//
//        for (int i = n - 1; i >= 0; i--) {
//            while (!s.empty() && arr[s.peek()] > arr[i]) {
//                s.pop();
//            }
//            if (s.empty()) {
//                right[i] = n;
//            } else {
//                right[i] = s.peek();
//            }
//            s.add(i);
//        }
//
//        long result = 0;
//        for (int i = 0; i < n; i++) {
//            long contribution = ((long) arr[i] * (i - left[i]) * (right[i] - i)) % mod;
//            result = (result + contribution + mod) % mod;
//        }
//        return (int) result;
//    }
    static long subArrayRanges(int[] nums) {
        int n = nums.length;
        int[] minLeft = new int[n];
        int[] minRight = new int[n];
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];

        findNextSmallerLeft(minLeft, nums);
        findNextSmallerRight(minRight, nums);
        findNextGreaterLeft(maxLeft, nums);
        findNextGreaterRight(maxRight, nums);

        long minSum = 0, maxSum = 0;

        for (int i = 0; i < n; i++) {
            long minCount = (long) nums[i] * (i - minLeft[i]) * (minRight[i] - i);
            long maxCount = (long) nums[i] * (i - maxLeft[i]) * (maxRight[i] - i);
            minSum += minCount;
            maxSum += maxCount;
        }
        return maxSum - minSum;
    }

    private static void findNextGreaterLeft(int[] arr, int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            arr[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
    }

    private static void findNextGreaterRight(int[] arr, int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            arr[i] = stack.isEmpty() ? nums.length : stack.peek();
            stack.push(i);
        }
    }

    private static void findNextSmallerLeft(int[] arr, int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            arr[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
    }

    private static void findNextSmallerRight(int[] arr, int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            arr[i] = stack.isEmpty() ? nums.length : stack.peek();
            stack.push(i);
        }
    }

    public static void main(String[] args) {
        int a[] = {1, 2, 3};
        subArrayRanges(a);
    }
}
