package Sorting;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] nums= {45,1,27,20,7,80,50};
        nums = mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static int[] mergeSort(int[] nums) {
        if(nums.length == 1) {
            return nums;
        }
        int mid = nums.length/2;
        int[] left = mergeSort(Arrays.copyOfRange(nums,0,mid));
        int[] right = mergeSort(Arrays.copyOfRange(nums,mid,nums.length));
        int[] resultArray =  mergeArray(left, right);
        return resultArray;
    }

    private static int[] mergeArray(int[] left, int[] right) {
        int[] result = new int[left.length+ right.length];
        int i= 0,j=0,k=0;
        while(i<left.length && j< right.length) {
            if(left[i]<right[j]) {
               result[k] = left[i];
               i++;
            } else {
                result[k] = right[j];
                j++;
            }
            k++;
        }

        while(i< left.length) {
            result[k] = left[i];
            i++;
            k++;
        }
        while(j< right.length) {
            result[k] = right[j];
            j++;
            k++;
        }
      return result;
    }
}
