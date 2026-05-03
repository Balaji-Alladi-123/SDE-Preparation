package leetcodePractiseQuestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PrimesSolutions {
    public static void main(String[] args) {
        completePrime(17);
    }
    static boolean completePrime(int num) {
        boolean[] primes = new boolean[num + 1];
        sieve(primes, num);
        String s =  String.valueOf(num);
        int n =s.length();
        HashSet<String> set = new HashSet<>();
        for(int i=0;i<n;i++) {
            set.add(s.substring(i));
        }
        for(int i=n-1;i>=0;i--) {
            set.add(s.substring(i));
        }

        for(String k: set) {
            if(primes[Integer.parseInt(k)]) {
                return false;
            }
        }
        return true;
    }


    static public int largestPrime(int n) {
        boolean[] primes = new boolean[n + 1];
        sieve(primes, n);
        int sum = 0, max = 0;
        for (int i = 2; i <= n; i++) {
            if (!primes[i]) {
                sum += i;
                if(primes[sum]) continue;
                if (sum > n ) return max;
                max = Math.max(max, sum);
            }
        }
        return 0;
    }

    public static void sieve(boolean[] primes, int n) {
        primes[0] = true;
        primes[1] = true;
        for (int i = 2; i * i <= n; i++) {
            if (!primes[i]) {
                for (int j = i * 2; j <= n; j += i) {
                    primes[j] = true;
                }
            }
        }
    }

    public boolean isPrime(int x) {
        if (x <= 1)
            return false;
        if (x <= 3)
            return true;
        if (x % 2 == 0 || x % 3 == 0)
            return false;

        for (int i = 5; i * i <= x; i += 6) {
            if (x % i == 0 || x % (i + 2) == 0)
                return false;
        }
        return true;
    }
}
