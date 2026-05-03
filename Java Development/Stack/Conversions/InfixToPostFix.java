package Stack.Conversions;

import java.util.Stack;

public class InfixToPostFix {
    public static String infixToPostfix(String s) {
        // Your code here
        int n = s.length();
        StringBuilder res = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')){
                res.append(ch);
            }
            else if (ch == '(') {
                stack.push(ch);
            }
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    res.append(stack.pop());
                }
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }  else {
                while (!stack.isEmpty() && ((prec(stack.peek())< prec(ch)) || prec(stack.peek()) == prec(ch))) {
                    res.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while(!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = infixToPostfix("a+b*(c^d-e)^(f+g*h)-i");
        System.out.println(s);
    }

    static int prec(char c) {
        if (c == '^')
            return 3;
        else if (c == '/' || c == '*')
            return 2;
        else if (c == '+' || c == '-')
            return 1;
        else
            return -1;
    }
}
