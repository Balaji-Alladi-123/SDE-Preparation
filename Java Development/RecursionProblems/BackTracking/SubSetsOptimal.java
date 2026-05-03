package RecursionProblems.BackTracking;

import java.util.*;

public class SubSetsOptimal {
    public static void main(String[] args) {
        int[] nums = {3,1,6,8,4};
        System.out.println(checkEqualPartitions(nums,24));
    }

    static int countSubsequenceWithTargetSum(int[] nums, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int count = 0;
        recursion(nums, 0, new ArrayList<>(), res, k, count);
        System.out.println(res);
        return res.size();
    }

    static void recursion(int[] nums, int s, ArrayList<Integer> list, List<List<Integer>> res, int k, int count) {
        if(checkList(list, k, count)) {
            res.add(list);
        }
        for (int i = s; i < nums.length; i++) {
            list.add(nums[i]);
            recursion(nums, i + 1, list, res, k, count);
            list.remove(list.size() - 1);
        }
    }

    static boolean checkList(ArrayList<Integer> internal, int k, int count) {
        int sum = 0;
        boolean flag = false;
        for (int n : internal) {
            sum += n;
        }
        if (sum == k) {
            flag = true;
        }
       return flag;
    }

    public static boolean checkEqualPartitions(int[] nums, long target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int flag = 0;
        subSets(nums, new ArrayList<Integer>(), target, 0, flag, map);
        System.out.println(flag);
        if (flag >= 2) {
            return true;
        }

        return false;
    }

    public static void subSets(int[] nums, ArrayList<Integer> list, long target, int start, int flag,
                               HashMap<Integer, Integer> map) {
        if (checkProduct(list, target, map)) {
            flag+=1;
        }
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            subSets(nums, list, target, i + 1, flag, map);
            list.remove(list.size() - 1);
        }
    }

    public static boolean checkProduct(ArrayList<Integer> list, long target, HashMap<Integer, Integer> map) {
        long prod = 1;
        for (int i : list) {
            prod *= i;
        }
        if (prod == target) {
            return true;
        }
        return false;
    }
}
