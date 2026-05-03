package Sorting;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
     int[] nums = {45,1,27,20,7,80,50};
     quickSorting(nums,0, nums.length-1);
        System.out.println(Arrays.toString(nums));
    }

   static public void quickSorting(int[] nums, int start, int end) {
        if(start>=end) return;
       int l = start;
       int h = end;
       int mid = l + (h - l) / 2;
       int pivot = nums[mid];

       while (l <= h) {
           while (nums[l] < pivot) {
               l++;
           }

           while (nums[h] > pivot) {
               h--;
           }

           if (l <= h) {
               int temp = nums[l];
               nums[l] = nums[h];
               nums[h] = temp;
               l++;
               h--;
           }
       }
           quickSorting(nums, start, h);
           quickSorting(nums, l, end);
   }
}
