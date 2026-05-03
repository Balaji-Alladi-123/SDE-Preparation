package leetcodePractiseQuestions;

public class Leetcode28 {
    public static void main(String[] args) {
        int[] nums = {73,32,54};
        maximumScore(nums);
    }

    public static long maximumScore(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int max = Integer.MIN_VALUE, idx = 0;
        if (n == 2) {
            return nums[0] - nums[1];
        }
        prefix[0] = nums[0];
        // max = Math.max(max,prefix[0]);
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                min = Math.min(min, nums[k]);
            }
            max = Math.max(max, prefix[j] - min);
        }
        return max;
    }
}
