package RecursionProblems.BackTracking;

import java.util.ArrayList;
import java.util.Collections;

public class SubSetSum {
    static ArrayList<Integer> subsetSums(int[] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        backtrackSum(arr, 0, 0, result); // start with sum = 0
        Collections.sort(result); // sort if required
        return result;
    }

    static void backtrackSum(int[] nums, int index, int currSum, ArrayList<Integer> result) {
        if (index == nums.length) {
            result.add(currSum); // add final sum when all elements processed
            return;
        }

        // Choice 1: Exclude current element
        backtrackSum(nums, index + 1, currSum, result);

        // Choice 2: Include current element
        backtrackSum(nums, index + 1, currSum + nums[index], result);
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        subsetSums(nums);
     }
}
