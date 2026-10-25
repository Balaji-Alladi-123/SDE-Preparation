package RecursionProblems.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class Paranthesis {
    public static void main(String[] args) {
        //generateParenthesis(3);
        uniquePaths(3,7);
    }
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(res, n,0,0,"");
        return res;
    }

    public static void  generate(List<String> res, int n , int open, int close, String unProcessed) {
        if(unProcessed.length() == 2*n) {
            res.add(unProcessed);
            return;
        }
        if(open<n) {
            generate(res, n, open+1, close,unProcessed+"(");
        }

        if(close<open) {
            generate(res,n, open, close+1,unProcessed+")");
        }
    }

    public static int uniquePaths(int m, int n) {
        if (m == 1 && n == 1) return 1;
        if (m <= 0 || n <= 0) return 0;

        int up = uniquePaths(m-1, n);
        int left = uniquePaths(m,n-1);
        return up+left;
    }
}
