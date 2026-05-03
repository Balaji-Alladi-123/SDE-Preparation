package RecursionProblems.BackTracking;

import java.util.Arrays;
import java.util.HashMap;

public class BeautifulSubSets {
    static int count = 0;

    static int beautifulSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        backTrack(0, k, nums, new HashMap<>());
        return count - 1;
    }

    static void backTrack(int start, int k, int[] nums, HashMap<Integer, Integer> freq) {
        if (start == nums.length) {
            count++;
            return;
        }

        backTrack(start + 1, k, nums, freq);

        int val = nums[start];
        if (!freq.containsKey(val - k)) {
            freq.put(val, freq.getOrDefault(val, 0) + 1);
            backTrack(start + 1, k, nums, freq);
            freq.put(val, freq.get(val) - 1);
            if (freq.get(val) == 0) {
                freq.remove(val);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,4,6};
        beautifulSubsets(nums,2);
    }
}
