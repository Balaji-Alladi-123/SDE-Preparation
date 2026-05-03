package Stack.StackArray;

import java.util.Stack;

public class StackArray {
    final int max = 1000;
    int top = -1;
    int[] arr = new int[max];
    boolean push(int x) {
        if (top >= max - 1) {
            System.out.println("stack is overflow");
            return false;
        } else {
            top++;
            arr[top] = x;
            System.out.println("pushed into the stack");
            return true;
        }
    }

    boolean isEmpty() {
        if (top == -1) {
            return true;
        }
        return false;
    }

    int pop() {
        if (top == -1) {
            System.out.println("stack in underflow");
            return 0;
        } else {
            int ele = arr[top--];
            System.out.println("popped element is:" + ele);
            return ele;
        }
    }

    int peek() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return 0;
        } else {
            int x = arr[top];
            return x;
        }
    }

    static class demo {
        public static void main(String[] args) {
            StackArray stack = new StackArray();
            stack.push(1);
            stack.push(20);
            stack.push(30);
            stack.push(40);
            System.out.println(stack.peek());
            System.out.println(stack.pop());
            System.out.println(stack.peek());
            System.out.println(stack.isEmpty());
        }
    }
}
