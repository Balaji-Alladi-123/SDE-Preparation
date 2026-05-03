package leetcodePractiseQuestions;

import java.util.*;

public class SubArray {
    public static void main(String[] args) {
        int[] nums = {3,2,2,5,4};
        //countStableSubarrays(nums);
       // maximumSum(nums);
//      String[]  words = {"fusion","layout"};
//        countPairs(words);
        longestBalanced(nums);
    }

    public static long countStableSubarrays(int[] capacity) {
        int n = capacity.length, l = 0;
        long sub = 0;
        int r = n - 1;
        while (l < n && r < n && (r - l) + 1 >= 3) {
            long sum = 0;
            if (capacity[l] == capacity[r]) {
                int i = l + 1;
                while (i <= r - 1) {
                    sum += capacity[i];
                    i++;
                }
                if (capacity[l] == sum) {
                    sub++;
                }
            }
            l++;
            r++;
        }
        return sub;
    }

    public  static String reverseWords(String s) {
        HashMap<Integer, String> map = new HashMap<>();
        String[] str = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            int v = vowelCount(str[i]);
            if (!map.containsKey(v)) {
                map.put(v, str[i]);
                sb.append(str[i]);
            } else {
                StringBuilder rev = new StringBuilder(str[i]).reverse();
                map.put(v, rev.toString());
                sb.append(rev);
            }

            if (i != str.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static int vowelCount(String s) {
        int i = 0, count = 0;
        while (i < s.length()) {
            int c = "aeiou".indexOf(s.charAt(i));
            if (c > 0)
                count++;
            i++;
        }
        return count;
    }

    static int maximumSum(int[] nums) {
        int n = nums.length,  max = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum % 3 == 0) {
                    max = Math.max(max, sum);
                }
                k--;
            }
            k=n-1;
            j=j+1;
            while ((j+1) < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum % 3 == 0) {
                    max = Math.max(max, sum);
                }
                j++;
            }
        }

        return max;
    }

    static long countPairs(String[] words) {
        int n = words.length;
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            StringBuilder sb = new StringBuilder();
            int first = words[i].charAt(0) - 'a';
            for(Character c: words[i].toCharArray()) {
                int diff = (c-'a'-first+26) %26;
                sb.append(diff).append(',');
            }

            map.put(sb.toString(), map.getOrDefault(sb.toString(),0)+1);
        }
        long pairs = 0;
        for(int v: map.values()) {
            pairs+= (long) v * (v-1)/2;
        }
        return pairs;
     }

    static int longestBalanced(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int n = nums.length,r=0,l=0,even=0,odd=0,max = Integer.MIN_VALUE;
        while(r<n && l<n) {
            if(nums[l]%2 ==0) {
                if(!set.contains(nums[l])) {
                    set.add(nums[l]);
                    even++;
                }
            } else {
                if(!set.contains(nums[l])) {
                    set.add(nums[l]);
                    odd++;
                }
            }

            if(odd == even) {
                max = Math.max(max,r-l+1);
            } else if(odd!=0 && even!=0 && ((odd<even) || (odd>even))) {
                if(nums[r]%2==0) even--;
                else odd--;
                set.remove(nums[r]);
                r++;
            }
            l++;
        }
        return max;
    }
}
