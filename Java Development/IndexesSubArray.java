import java.util.ArrayList;

public class IndexesSubArray {
    public static ArrayList<Integer> subarraySum(int[] arr, int n, int s) {
        // code here
        ArrayList<Integer> list =new ArrayList<>();
        int sum=0,k=0;
        for(int i=0;i<n;i++) {
            sum+= arr[i];
            if(sum==s) {
                list.add(k+1);
                list.add(i+1);
                break;
            }
            if(sum>s) {
                k+=1;
                i=k-1;
                sum=0;
            }
            if(k>n-1) {
                list.add(-1);
            }
        }
        return list;
    }
//    public static int sum(int p, int[] arr, int s) {
//        ArrayList<Integer> list = new ArrayList<>();
//        if(p == s) {
//
//        }
//        p = arr[0];
//        p+sum(,s);
//    }
    public static void main(String[] args) {
        int[] arr = {1};
        System.out.println(subarraySum(arr,1,0));
    }
}
