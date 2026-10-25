package dynamicProgramming;

public class Problems {
    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        findTargetSumWays(nums, 3);
    }
    public static int findTargetSumWays(int[] nums, int target) {
         return helper(nums, target, 0, 0);
    }

    public static int helper(int[] nums, int target, int idx, int sum) {
        if(idx>=nums.length) {
            return sum ==target ? 1: 0;
        }
        int add = helper(nums, target, idx+1, sum+nums[idx]);
        int sub = helper(nums, target, idx+1, sum-nums[idx]);
        return add+sub;
    }
}
