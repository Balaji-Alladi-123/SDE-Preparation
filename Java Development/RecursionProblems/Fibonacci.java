package RecursionProblems;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Fibonacci {
    public static void main(String[] args) {
//        System.out.println(myPow( 2.00,  -200000000));
       // fibonnaciNumber(5);
    }

    private static int rev(int n) {
           int digits = (int) (Math.log10(n))+1;
       return helper(n,digits);
    }
    private static int helper(int n, int digits) {
        if(n%10 == n) {
            return n;
        }
        int rem = n%10;
        return rem * (int) (Math.pow(10,digits-1)) + helper(n/10,digits-1);
    }
/// power of number
public static double myPow(double x, int n) {
    double power = 1;
    if(n<0) return helper(power,x, Math.abs(n), true);
    return helper(power,x, n, false);
}

    public static double helper(double power,double x, int n, boolean neg) {
        if (n == 0) {
            if(neg) { return 1/power; }
            return power;
        }
        power *= x;
        return helper(power,x, n - 1, neg);
    }

    public static int fibonnaciNumber(int n) {
        if(n<=1) {
            return n;
        }
        return fibonnaciNumber(n-1) + fibonnaciNumber(n-2);
    }

}
