import java.util.Arrays;

public class BinarySearch {
    public static  void  main(String[] args) {
//        int[] arr = {1,3,34,45,67,88,98,100};
        int[][] arr = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int[] a = {2, 3, 6, 7, 9},b = {1,4, 8, 10};
        int target = 3;
        //kthElement(a,b,5);
//        searchMatrix(arr,target);
        int[] nums = {2,3,4};
        twoSum(nums,6);
    }
    static int search(int[] arr,int target, int s, int e) {
        int m = s+(e-s)/2;
        if(s>e) {
            return -1;
        }
        if(arr[m] == target) {
            return m;
        }
        if(arr[m]>target) {
            return search(arr, target, s,m-1);
        }
        return search(arr,target,m+1,e);
    }
    static boolean searchMatrix(int[][] matrix, int target) {
        int low =0, n= matrix.length, m=matrix[0].length,high =(n*m-1);
        while(low<=high) {
            int mid = (low+high)/2;
            int rows = mid/m,cols = mid%m;
            if(matrix[rows][cols]== target) {
                return true;
            }
            else if(matrix[rows][cols]<target) {
                low = mid+1;
            }
            else {
                high = mid-1;
            }
        }
        return false;
    }
    static int kthElement(int a[], int b[], int k) {
        // code here
        int n1 = a.length,n2= b.length,i=0,j=0;
        int[] arr = new int[n1+n2];

        while(k<arr.length) {
            if(i<n1) {
                arr[k++] = a[i];
                i++;
            }
            if(j<n2) {
                arr[k++] = b[j];
                j++;
            }
        }
        Arrays.sort(arr);

        return arr[k-1];
    }

    public static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for(int i=0;i<n;i++) {
            int sum = target-nums[i];
            int index = binarySearch(i,n-1,nums,sum);
            if(index!=-1) {
                return new int[]{i+1,index+1};
            }
        }
        return new int[]{-1,-1};

    }

    public static int binarySearch(int start, int end, int[] arr, int target) {

        while(start<end) {
            int mid = end-(start+end)/2;
            if(arr[mid] == target) {
                return mid;
            } else if(arr[mid]> target) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return -1;
    }
}

