package Stack.Conversions;

import java.util.Stack;

public class PrefixToPostFix {
    static String prefixToPostFix(String s) {
        int n = s.length(),i=n-1;
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        while(i>=0) {
            char ch = s.charAt(i);
            sb = new StringBuilder();
            if((ch >= 'a' && ch<='z') || (ch >= 'A' && ch<= 'Z')) {
                stack.push(String.valueOf(ch));
            } else {
                if(!stack.isEmpty()) {
                    sb.append('(');
                    sb.append(stack.pop());
                }
                if(!stack.isEmpty()) {
                    sb.append(stack.pop());
                    sb.append(ch);
                    sb.append(')');
                }
                stack.push(sb.toString());
            }
            i--;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String s = prefixToPostFix("*-A/BC-/AKL");
        System.out.println(s);
    }
}
