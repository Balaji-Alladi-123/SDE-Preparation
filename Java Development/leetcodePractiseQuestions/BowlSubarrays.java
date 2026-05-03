package leetcodePractiseQuestions;

import java.util.HashMap;

public class BowlSubarrays {
    public static void main(String[] args) {
        int[] nums = {1,2,1,3,1};
        //bowlSubarrays(nums);
        minArrivalsToDiscard(nums,4,2);
    }
    static long bowlSubarrays(int[] nums) {
        int n = nums.length, l = 0;
        int r = n - 1;
        long count = 0;

        while (l < r) {
            if (r - l + 1 <= 3) {
                break;
            }
            long min = (long) Math.min(nums[l], nums[r]);
            long max = checkForMax(l + 1, r - 1, nums);
            if (min > max) {
                count++;
            }
            if (max>min) {
                l++;
            } else {
                r--;
            }
        }
        return count;
    }

    static long checkForMax(int start, int end, int[] nums) {
        long max = 0;
        for (int i = start; i <= end; i++) {
            max = (long) Math.max(nums[start], max);
        }
        return max;
    }

    static int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        HashMap<Integer,Integer> map = new HashMap<>();

        int n = arrivals.length,count=0,i=0,l=0;

        while(i<n && l<n) {
            if(!map.containsKey(arrivals[i])) {
                map.put(arrivals[i],1);
            } else {
                map.put(arrivals[i],map.get(arrivals[i])+1);
                if(map.get(arrivals[i])>m) {
                    count++;
                }
            }
            if(i-l+1 == w) {
                if(map.containsKey(arrivals[l])) {
                    map.put(arrivals[l],map.get(arrivals[l])-1);
                }
                l++;
            }
            i++;
        }
        return count;
    }
}
