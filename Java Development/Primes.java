import java.util.Arrays;
import java.util.List;

public class Primes {
    public static  void main(String[] ar) {
        int n=50;
        boolean[] prime = new boolean[n+1];
       // primes(n,prime);
        sumOfPrimesInRange(10);
    }
    private static void primes(int n, boolean[] prime) {
        for(int i =2;i<= n;i++) {
            if(!prime[i]) {
                for(int j=i*2;j<=n;j+=i) {
                    prime[j] = true;
                }
            }
        }
        for(int i=2;i<=n;i++) {
            if(!prime[i]) {
                System.out.print(i+ " ");
            }
        }
    }

    public static int sumOfPrimesInRange(int n) {
        int rev = reverse(n);
        int sum = 0;
        int start = rev > n ? n : rev;
        int end = rev > n ? rev : n;
        boolean[] primes = new boolean[end + 1];
        Arrays.fill(primes, true);
        sieveErathonsis(start, end, primes);
        primes[1] = false;
        for (int i = start; i <= end; i++) {
            if (primes[i]) {
                sum += i;
            }
        }
        return sum;
    }

    public static void sieveErathonsis(int start, int end, boolean[] primes) {
        for (int i = 2; i * i <= end; i++) {
            if (primes[i]) {
                for (int j = i * i; j <= end; j += i) {
                    primes[j] = false;
                }
            }
        }
    }

    public static int reverse(int n) {
        int rev = 0;
        while (n > 0) {
            int r = n % 10;
            rev = (rev * 10) + r;
            n /= 10;
        }
        return rev;
    }

    public static void checkPrime() {
        List<Integer> nums = Arrays.asList(4, 3, 23, 45, 89, 22, 67, 100, 45);
        boolean isPrime = nums.stream()
                .anyMatch(this::getPrime);
        System.out.println("List contains a prime number: " + isPrime);
    }

    public static boolean getPrime(int number) {
        if (number == 2 || number == 3) return true;
        if (number <= 1 || number == 4) return false;
        for (int i = 5; i <= (i * i); i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }
        return true;
    }

}
