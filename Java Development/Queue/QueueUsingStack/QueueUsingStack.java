package Queue.QueueUsingStack;

import java.util.Stack;

public class QueueUsingStack {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    public void enQueue(int x) {
       while(!stack1.isEmpty()) {
           stack2.push(stack1.pop());
       }
       stack1.push(x);
       while(!stack2.isEmpty()) {
           stack1.push(stack2.pop());
       }
    }
    public int deQueue() {
        if(stack1.isEmpty()) {
            return -1;
        }
        return stack1.pop();
    }
    public int peek() {
        if(stack1.isEmpty()) {
            return -1;
        }
        return stack1.peek();
    }
    public static void main(String[] args) {
        QueueUsingStack stack = new QueueUsingStack();
         stack.enQueue(10);
         stack.enQueue(20);
         stack.enQueue(30);
        System.out.println(stack.peek());
    }
}
