package Stack.StackUsingLinkedList;

public class StackUsingLinkedList {
    Node top;

    void push(int x) {
      Node newNode = new Node(x);
      newNode.next = top;
      top = newNode;
    }

    int pop() {
       Node temp = top;
       top = top.next;
       return temp.data;
    }

    int peek() {
     return top.data;
    }

    public static void main(String[] args) {
        StackUsingLinkedList stack = new StackUsingLinkedList();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.pop();
        stack.pop();
        System.out.println(stack);
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}

