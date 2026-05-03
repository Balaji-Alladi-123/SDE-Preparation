package Stack.Conversions;

import java.util.Stack;

public class PostToInfix {
    static String postFixToInfix(String s) {
        int n = s.length(),i=0;
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        while(i<n) {
            char ch = s.charAt(i);
            sb = new StringBuilder();
            if((ch >= 'a' && ch<='z') || (ch >= 'A' && ch<= 'Z')) {
                stack.push(String.valueOf(ch));
            } else {
                if(!stack.isEmpty()) {
                 String op1 = stack.pop();
                 String op2 = stack.pop();
                 sb.append(op2+ch+op1);
                }
                stack.push(sb.toString());
            }
            i++;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String s = postFixToInfix("AB+CD-*");
        System.out.println(s);
    }
}
