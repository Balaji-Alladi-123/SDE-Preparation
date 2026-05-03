package RecursionProblems.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        chechCombinations(candidates, target, result, new ArrayList<>(), 0, 0);
        return result;
    }

    static void chechCombinations(int[] nums, int target, List<List<Integer>> res, ArrayList<Integer> internal,
                                  int index, int total) {
        if (total == target) {
            res.add(new ArrayList<>(internal));
            return;
        }

        if (total > target || index >= nums.length) {
            return;
        }

        internal.add(nums[index]);
        chechCombinations(nums, target, res, internal, index, total + nums[index]);
        internal.remove(internal.size() - 1);
        System.out.print(internal);
        chechCombinations(nums, target, res, internal, index + 1, total);
    }

    static public List<List<Integer>> CombinationSumII(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>(), result, target);
        System.out.println(result);
        return result;
    }

    static void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> result, int target) {
        if(target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if(i> start && nums[i]== nums[i-1]) continue;
            if(nums[i] > target) break;
            path.add(nums[i]);
            backtrack(nums, i + 1, path, result, target-nums[i]);
            path.remove(path.size() - 1);
        }
    }

    static List<List<Integer>> combinationSumIII(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backTrack(new ArrayList<Integer>(),result,n,k,1);
        return result;
    }

    public static void backTrack(List<Integer> internal, List<List<Integer>> result,int n, int k, int start) {
        if(internal.size() == k) {
            if(n==0) {
                result.add(new ArrayList<>(internal));
            }
            return;
        }

        for(int i= start;i<=9;i++) {
            if (i > n) break;
            internal.add(i);
            backTrack(internal,result,n-i,k,i+1);
            internal.remove(internal.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {10,1,2,7,6,1,5};
        combinationSumIII(3,7);
    }
}
