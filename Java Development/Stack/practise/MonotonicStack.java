package Stack.practise;

import java.util.Stack;

public class MonotonicStack {
    static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        int n = nums2.length;
        int[] arr = new int[1000];
        for (int i = 0; i < n; i++) {
            if(stack.isEmpty()) {
                stack.push(nums2[i]);
                continue;
            }
            if (stack.peek() < nums2[i]) {
                arr[stack.peek()] = nums2[i];
                stack.push(nums2[i]);
            }
            else {
                while(stack.peek()>nums2[i]) {
                    arr[stack.pop()] = -1;
                }
                stack.push(nums2[i]);
            }
        }
       arr[stack.peek()] = -1;
        for(int i=0;i<nums1.length;i++) {
            nums1[i] = arr[nums1[i]];
        }
        return nums1;
    }
    public static void main(String[] args) {
       int[] nums1 = {2,1,3}, nums2 = {2,3,1};
       nextGreaterElement(nums1,nums2);
    }
}
