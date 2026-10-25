package leetcodePractiseQuestions;

public class Leetcode28 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6};
        //maximumScore(nums);
        countGoodRotations(nums);
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

    public static int countGoodRotations(int[] nums) {
        int n = nums.length, count = 0;
        long first = 0, second = 0;
        for (int i = 0; i < n / 2; i++) {
            first += nums[i];
        }
        for (int j = n / 2; j < n; j++) {
            second += nums[j];
        }

        if (first > second) {
            count++;
        }

        int l = 0, h = 1;
        while (l < n && h < n) {
            first -= nums[l];
            first += nums[h];
            second -= nums[h];
            second += nums[l];
            System.out.print(first);
            if (first > second) {
                count++;
            }
            l++;
            h++;
        }
        return count;
    }
}
