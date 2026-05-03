package Stack.Conversions;

import java.util.Stack;

public class PostFixToPreFix {
    static String postFixToPrefix(String s) {
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
                    sb.append(stack.pop());
                }
                if(!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(ch);
                sb.reverse();
                stack.push(sb.toString());
            }
            i++;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String s = postFixToPrefix("AB+CD-*");
        System.out.println(s);
    }
}
