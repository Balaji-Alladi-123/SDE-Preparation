import java.util.*;

public class Solution {
    static public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
       for(int i=0;i<n;i++) {
           for(int j=0;j<n;j++) {
               map.computeIfAbsent(i-j,k-> new ArrayList<>()).add(grid[i][j]);
           }
       }
        for (int key : map.keySet()) {
            List<Integer> diagonal = map.get(key);
            if (key >= 0) {
                Collections.sort(diagonal, Collections.reverseOrder());
            } else {
                Collections.sort(diagonal);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                grid[i][j] = map.get(i - j).remove(0);
                System.out.println(map.get(i - j).remove(0));
            }
        }

        return grid;
    }

    public static void main(String[] arga) {
//        int[][] n = {{1,7,3},{9,8,2},{4,5,6}};
//        sortMatrix(n);
         Stack<Integer> stack = new Stack<>();
         stack.push(3);
         stack.push(2);
        stack.push(1);
        stack.push(7);
        stack.push(6);
reverse(stack);
    }

    public static int[] reverseDemo(int[]  nums) {
        return reverseArray(nums,0,nums.length-1);
    }

    private static int[] reverseArray(int[] nums, int start, int end) {
        if(start == end) return nums;
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
       return  reverseArray(nums,start+1,end-1);
    }
    static void reverse(Stack<Integer> s) {
        Stack<Integer> res = new Stack<>();
        while(s.size()>0) {
            res.push(s.pop());
        }

        s =res;
    }
}



