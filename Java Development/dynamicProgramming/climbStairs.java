package dynamicProgramming;

public class climbStairs {
    public static void main(String[] args) {
        climbStairs(3);
    }
    static int climbStairs(int n) {
        if (n == 1)
            return n;
        int[] dp = new int[n + 1];
        return recursion(n, dp);
    }

    public static int recursion(int n, int[] dp) {
        if (n <=1) {
            return 1;
        }
         if(dp[n]!=0) {
             return dp[n];
         }
        int res = recursion(n-1,dp) + recursion(n-2,dp);
        return dp[n] = res;
    }
}
