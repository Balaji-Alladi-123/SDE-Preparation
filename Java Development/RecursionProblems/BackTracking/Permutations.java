package RecursionProblems.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        permutationSets("", "abc");
    }

    public static ArrayList<String> permutationSets(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> res = new ArrayList<>();
            res.add(p);
            return res;
        }
        ArrayList<String> res = new ArrayList<>();

        char c = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0, i);
            String s = p.substring(i, p.length());
            res.addAll(permutationSets(f + c + s, up.substring(1)));
        }
        System.out.println(res);
        return res;
    }

    static public List<List<Integer>> permute(int[] nums) {
       List<List<Integer>> res = new ArrayList<>();
       permutations(0,nums,res);
       return res;
    }

    private static void permutations(int index, int[] nums, List<List<Integer>> res) {
        if(index  == nums.length) {
            ArrayList<Integer> internal = new ArrayList<>();
            for(int i=0;i<nums.length;i++) {
                internal.add(nums[i]);
            }
            res.add(internal);
            return;
        }
        for(int i = index;i< nums.length;i++) {
            swapIndex(i,index, nums);
            permutations(index+1,nums, res);
            swapIndex(i,index,nums);
        }
    }

    private static void swapIndex(int i,int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
