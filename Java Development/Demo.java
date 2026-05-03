import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo {
    public static int countSubarrWithEqualZeroAndOne(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                arr[i] = -1;
            }
        }
        // Step 2: Use a hash map to store cumulative sums and their frequencies
        HashMap<Integer, Integer> map = new HashMap<>();
        int cumulativeSum = 0;
        int count = 0;
        map.put(0, 1);
        for (int i = 0; i < n; i++) {
            cumulativeSum += arr[i];
            // If cumulativeSum has been seen before, it means there's a subarray with sum 0
            if (map.containsKey(cumulativeSum)) {
                count += map.get(cumulativeSum);
            }
            map.put(cumulativeSum, map.getOrDefault(cumulativeSum, 0) + 1);
        }
   System.out.println(map);
        return count;
    }

    public static void main(String[] args) {
        int[] A = {1, 0, 0, 1, 0, 1, 1};
        int n = A.length;
        int result = countSubarrWithEqualZeroAndOne(A, n);
        System.out.println(result);
    }

}