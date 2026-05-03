package dynamicProgramming;

public class MinClimbing {
    public static void main(String[] args) {
        int[] nums = {10,15,20};
        // minCostClimbingStairs(nums);
        sumAndMultiply(10203004);
    }
    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int a = f(cost, 0);
        int b = f(cost, 1);
        System.out.println(Integer.toBinaryString(10));
        String w = new StringBuilder("s").reverse().toString();
        return Math.min(a,b);
    }

    private static int f(int[] cost, int i) {
        int n = cost.length;
        if (i >= n) return 0;
        return cost[i] + Math.min(f(cost, i+1), f(cost, i+2));
    }
    public static long sumAndMultiply(int n) {
        String s = String.valueOf(n);
        StringBuilder sb = new StringBuilder();
        int sum =0;
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)!='0') {
                sb.append(s.charAt(i));
                sum+= Integer.parseInt(String.valueOf(s.charAt(i)));
            }
        }
        int x = Integer.parseInt(sb.toString());
        return x *  sum;
    }
}
