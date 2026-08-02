package leetcodePractiseQuestions;

import java.util.*;

public class Solutions {
    public static void main(String[] args) {
        ///fibonnaci(5);
        int[] n = {-100000,-100000,-100000};
        //longestBalanced(n);
//        threeSum(n);'
        //lexSmallest("abba");
        //maxProduct(n);
        int[] nums= {1,100,1};
        countValidSubarrays(nums,1);
//        isPalindrome("A man, a plan, a canal: Panama");
    }

    public static int fibonnaci(int n) {
        if (n <= 1) {
            return n;
        }

        return fibonnaci(n - 1) + fibonnaci(n - 2);
    }

    static public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        int i = 0, j = 1, k = 2;
        List<Integer> list = new ArrayList<>();
        while (i < n && j < n && k < n) {
            if (i != j && i != k && j != k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    if (list.contains(nums[i]) && list.contains(nums[j]) && list.contains(nums[k])) {
                        j++;
                        k++;
                        continue;
                    } else {
                        list.clear();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                    }
                    if (!res.contains(list)) {
                        res.add(list);
                    }
                }
            }
            j++;
            k++;
        }
        return res;
    }

    static String lexSmallest(String s) {
        int  n  = s.length();
        int k = n-1;

        StringBuilder sb = new StringBuilder();

        StringBuilder r =  sb.append(s.substring(0,k)).reverse();
        r.append(s.substring(k,n));

        if(sb.toString().compareTo(s)<0) {
            return sb.toString();
        } else {
            int l = n-k;
            sb = new StringBuilder();
            r = sb.append(s.substring(0,l));
            sb = new StringBuilder();
            r.append(sb.append(s.substring(l,n)).reverse());
            if(r.toString().compareTo(s)>0) {
                return sb.toString();
            }
        }
        return "";
    }

    public static boolean isPalindrome(String s) {
        s=s.toLowerCase();
        return palindrome(s,0,s.length()-1);
    }

    public static boolean palindrome(String s,int l, int r) {
        if(r>l) {
            return true;
        }
        char c1 = s.charAt(l),c2 = s.charAt(r);
        if (!((c1 >= 97 && c1 <= 122) || (c1 >= 48 && c1 <= 57))) {
            return palindrome(s, l + 1, r);
        }
        if (!((c2 >= 97 && c2 <= 122) || (c2 >= 48 && c2 <= 57))) {
            return palindrome(s, l, r - 1);
        }
        if(s.charAt(r) != s.charAt(l)) {
                return false;
            }

        return palindrome(s,l+1,r-1);
    }

    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l != r) {
            int lSquare = nums[l] * nums[l];
            int rSquare = nums[r] * nums[r];
            if (lSquare > rSquare) {
                int temp = nums[r];
                nums[r] = nums[l];
                nums[l] = temp;
            } else {
                nums[r] = rSquare;
                nums[l] = lSquare;
            }
            r--;
        }
        return nums;
    }

    public static int countValidSubarrays(int[] nums, int x) {
        int n = nums.length;
        long sum =0;
        int count = 0;
        for(int i=0;i<n;i++) {
            sum+=nums[i];
            count+= getmatch(sum,x);
            for(int j=i+1;j<n;j++) {
                sum+=nums[j];
                count+= getmatch(sum,x);
            }
        }
        return count;
    }

    public static int getmatch(long num, int x) {
        String s = String.valueOf(num);
        System.out.println( s.charAt(0));
        if(s.charAt(0) == x && s.charAt(s.length()-1) == x) {
            return 1;
        } 
        return 0;
    }
}