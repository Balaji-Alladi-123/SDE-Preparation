import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

public class practise {
    public static void main(String[] args) {
        //checkDivisibility(99);
        int[] arr = {415,616,749};
        maxBalancedShipments(arr);
    }

    public static List<String> partitionString(String s) {
        HashMap<String, Boolean> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        int n = s.length();
        for(int i=0;i<n;i++) {
            String str = String.valueOf(s.charAt(i));
            if(!map.containsKey(str)) {
                map.put(str,true);
                res.add(str);
            } else {
                int j = i+1;
                StringBuilder next = new StringBuilder();
                next.append(s.charAt(i));
                next.append(s.charAt(j));
                while(map.containsKey(next.toString()) && j<n) {
                    next.append(s.charAt(j));
                    j++;
                }
                map.put(next.toString(),true);
                res.add(next.toString());
                i = j;
            }
        }
        return res;
    }

    static boolean checkDivisibility(int n) {
        int sum=0,prod =1;
        int r =n;
        while(n>0) {
            sum+= n%10;
            prod*= n%10;
            n/=10;
        }
        if(r%sum ==0 && prod+sum ==r) {
            return true;
        }
        return false;
    }
    static int maxBalancedShipments(int[] weight) {
        int n = weight.length;
        int i=0,max = Integer.MIN_VALUE,count=0;
        while(i<n) {
            int j=i;
            while(j<n) {
                max= Math.max(max,weight[j]);
                if(max>weight[j]) {
                    count++;
                    i = j;
                    max = Integer.MIN_VALUE;
                }
                j++;
            }
            if(j==n) break;
            i++;
        }
        return count;
    }
}
