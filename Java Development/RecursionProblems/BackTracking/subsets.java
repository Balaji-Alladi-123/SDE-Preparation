package RecursionProblems.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class subsets {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        rob(nums);
    }

    public static List<List<Integer>> subSetsArray(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int num : nums) {
            int n = res.size();
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> internal = new ArrayList<>(res.get(i));
                internal.add(num);
                res.add(internal);
            }
        }
        return res;
    }

    public static ArrayList<String> subSetsString(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);
        ArrayList<String> left = subSetsString(p + ch, up.substring(1));
        ArrayList<String> right = subSetsString(p, up.substring(1));
        left.addAll(right);
        return left;
    }
/// bit masking for String
    public static ArrayList<String> subSetBitMasking(String str) {
        int n = str.length();
        int total = 1 << n; // 2^n
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < total; i++) {
            StringBuilder subset = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.append(str.charAt(j));
                }
            }
            result.add(subset.toString());
        }
        return result;
    }

/// bit masking for integer array
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        for (int mask = 0; mask < (1 << n); mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }
            res.add(subset);
        }
        return res;
    }

    static public List<List<Integer>> RecursionSubSets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>(), result);
        System.out.println(result);
        return result;
    }

    static void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(nums, i + 1, path, result);
            
            path.remove(path.size() - 1);
        }
    }

    static public boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (total % 2 != 0) return false;
        return canFindSubset(nums, 0, 0, total / 2);
    }

    static boolean canFindSubset(int[] nums, int index, int currentSum, int target) {
        if (currentSum == target) return true;
        if (currentSum > target || index >= nums.length) return false;

        // choose the current number
        if (canFindSubset(nums, index + 1, currentSum + nums[index], target)) return true;

        // skip the current number
        return canFindSubset(nums, index + 1, currentSum, target);
    }


    static public List<Integer> RecursionSubSetsSum(int[] nums) {
        List<Integer> result = new ArrayList<>();
        backtrackSum(nums, 0, new ArrayList<>(), result);
        Collections.sort(result);
        return result;
    }

    static void backtrackSum(int[] nums, int start, List<Integer> path, List<Integer> result) {
        result.add(calculateSum(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrackSum(nums, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }

    static int calculateSum(List<Integer> nums) {
        int sum = 0;
        for(int n: nums) {
             sum+=n;
        }
        return sum;
    }
    static int rob(int[] nums) {
        int n = nums.length,sum=0;
        return recursion(nums,n-1);
    }

    static int recursion(int[] nums, int index) {
        if(index ==0) {
            return nums[index];
        }
        if(index<0) {
            return 0;
        }

        int pick = nums[index] + recursion(nums,index-2);
        int nonPick = recursion(nums,index-1);
        return Math.max(pick, nonPick);
    }
}
