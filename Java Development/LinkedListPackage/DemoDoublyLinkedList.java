package LinkedListPackage;

import java.util.ArrayList;
import java.util.HashMap;

public class DemoDoublyLinkedList {
    static DoubleNode convertArrayToDoublyLinkedList(int[] a) {
        DoubleNode head = new DoubleNode(a[0]);
        DoubleNode mover = head;
        for (int i = 1; i < a.length; i++) {
            DoubleNode newNode = new DoubleNode(a[i]);
            mover.next = newNode;
            newNode.back = mover;
            mover = newNode;
        }
        return head;
    }

    static DoubleNode deleteHead(DoubleNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        DoubleNode prev = head;
        head = head.next;
        head.back = null;
        prev.next = null;
        return head;
    }

    static void deleteGivenNode(DoubleNode head) {
        DoubleNode prev = head.back;
        DoubleNode front = head.next;
        if (prev == null) {
            head = head.next;
            head.back = null;
            return;
        }
        if (front == null) {
            prev.next = null;
            head.back = null;
            return;
        }
        prev.next = front;
        front.back = prev;
        head.next = null;
        head.back = null;
        System.out.println(head);
    }

    static DoubleNode deletAtLast(DoubleNode head) {
        DoubleNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        DoubleNode newNode = tail.back;
        newNode.next = null;
        tail.back = null;
        return head;
    }

    static DoubleNode deleteAtKthElement(DoubleNode head, int k) {
        if (head == null) {
            return null;
        }
        DoubleNode tail = head;
        int c = 0;
        while (tail != null) {
            c++;
            if (c == k) break;
            tail = tail.next;
        }
        DoubleNode prev = tail.back;
        DoubleNode frwd = tail.next;
        if (prev == null && frwd == null) {
            return null;
        }
        if (frwd == null) {
            prev.next = null;
            return head;
        }
        if (prev == null) {
            head = head.next;
            head.back = null;
            return head;
        }
        prev.next = frwd;
        frwd.back = prev;
        tail.next = null;
        tail.back = null;
        return head;
    }

    DoubleNode insertAfterPthValue(DoubleNode head, int p, int x) {
        if (head == null) {
            return null;
        }
        DoubleNode tail = head;
        int c = -1;
        while (tail != null) {
            c++;
            if (c == p) break;
            tail = tail.next;
        }
        DoubleNode frwd = tail.next;
        DoubleNode back = tail.back;
        DoubleNode newNode = new DoubleNode(x);
        if (back == null && frwd == null) {
            newNode.back = tail;
            newNode.next = null;
            return head;
        }
        if (frwd == null) {
            newNode.next = frwd;
            newNode.back = tail;
            tail.next = newNode;
            return head;
        }
        if (back == null) {
            newNode.back = tail;
            newNode.next = frwd;
            return head;
        }
        newNode.next = frwd;
        newNode.back = tail;
        tail.next = newNode;
        return head;
    }

    DoubleNode insertBeforeHead(DoubleNode head, int val) {
        DoubleNode newNode = new DoubleNode(val, head, null);
        head.back = newNode;
        return newNode;
    }

    static DoubleNode insertBeforeTail(DoubleNode head, int val) {
        DoubleNode node = head;
        while (node.next != null) {
            node = node.next;
        }
        DoubleNode newNode = new DoubleNode(val);
        DoubleNode prev = node.back;
        newNode.back = prev;
        newNode.next = node;
        node.back = newNode;
        return head;
    }

    static DoubleNode deleteAllOccurOfX(DoubleNode head, int x) {
        DoubleNode temp = head;
        while (temp != null) {
            if (temp.data == x) {
                if (temp == head) {
                    head = head.next;
                } else {
                    DoubleNode frwd = temp.next;
                    DoubleNode back = temp.back;
                    if(back!=null) {
                        back.next = frwd;
                    }
                    if(frwd!=null) {
                        frwd.back = back;
                    }
                }
            }
            temp = temp.next;
        }
        return head;
    }
    static DoubleNode removeDuplicates(DoubleNode head){
        if(head.next == null) {
            return head;
        }
        DoubleNode temp = head;
        while(temp!=null) {
            if(temp.next == null) {
                break;
            }
            if(temp.data == temp.next.data) {

                head = head.next;
            }
            temp=temp.next;
        }

        return head;
    }

    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, DoubleNode head) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        DoubleNode temp = head;
        int i=0;
        while(temp!=null) {
            map.put(temp.data,map.getOrDefault(temp.data,0)+1);
            temp = temp.next;
        }
        DoubleNode t= head;
        while(t!=null) {
            int sum = target-t.data;
            ArrayList<Integer> list = new ArrayList<>();
            if(map.containsKey(sum)) {
                list.add(t.data);
                list.add(sum);
                map.remove(sum);
                res.add(list);
            }
            t = t.next;
        }
        return res;
    }

    public static void main(String[] args) {
        DoubleNode head = convertArrayToDoublyLinkedList(new int[]{1,2,3,4});
//        DoubleNode node  = deleteHead(head);
//        DoubleNode node = deletAtLast(head);
//        DoubleNode node = insertAfterPthValue(head, 2, 7);
//        DoubleNode node = insertBeforeHead(head, 10);
//        DoubleNode node = insertBeforeTail(head,100);
//        deleteGivenNode(node);
//        DoubleNode node = removeDuplicates(head);
         findPairsWithGivenSum(5,head);
//        while (node != null) {
//            System.out.print(node.data + "->");
//            node = node.next;
//        }
    }
}

class DoubleNode {
    int data;
    DoubleNode next;
    DoubleNode back;

    DoubleNode(int data1, DoubleNode next1, DoubleNode back1) {
        this.data = data1;
        this.next = next1;
        this.back = back1;
    }

    DoubleNode(int data1) {
        this.data = data1;
        this.next = null;
        this.back = null;
    }
}
