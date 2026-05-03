package Sorting;

import java.util.Arrays;

public class CyclicSort {
    public static void main(String[] args) {
        int[] nums = {3, 5, 2, 1, 4};
        cycliSortDemo(nums);
        System.out.println("hello:"+ Arrays.toString(nums));
    }

    public static int[] cycliSortDemo(int[] nums) {
        int n = nums.length,i=0;
        while(i<n) {
            int idx = nums[i] -1;
            if(nums[i]!=0 && nums[i]!= nums[idx]) {
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp - 1] = temp;
            } else {
                i++;
            }
        }
        return nums;
    }

}
