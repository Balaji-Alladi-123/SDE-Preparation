package LinkedListPackage;

import java.net.StandardSocketOptions;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class DemoLinkedList {
    Node head;
    private static Node convertArrayToLinkedList(int[] arr) {
        Node head = new Node(arr[0]);
        Node mover = head;
        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i]);
            mover.next = temp;
            mover = temp;
        }
        return head;
    }
    // Insert at beginning
    void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    // Insert at end
    void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = newNode;
    }

    static Node removeKthElement(Node node, int k) {
        if (k == 1) {
            node = node.next;
            return node;
        }
        int count = 0;
        Node temp = node, prev = null;
        while (temp != null) {
            count++;
            if (count == k) {
                prev.next = prev.next.next;
                return node;
            }
            prev = temp;
            temp = temp.next;
        }
        return node;
    }

    static Node removeHead(Node head) {
        head = head.next;
        return head;
    }

    static Node removeTail(Node node) {
        Node temp = node;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        return temp;
    }

    static Node addHead(Node head) {
        return new Node(789, head);
    }

    static Node insertKthElement(Node head, int k, int val) {
        if (head == null) {
            if (k == 1) return new Node(val);
            return null;
        }
        if (k == 1) return new Node(val, head);
        Node temp = head;
        int c = 1;
        while (temp != null) {
            c++;
            if (c == k) {
                Node newNode = new Node(val, temp.next);
                temp.next = newNode;
                break;
            }
            temp = temp.next;
        }
        return head;
    }

    static boolean hasCycle(Node head) {
        HashSet<Node> visitedNodes = new HashSet<>();
        while (head != null) {
            if (visitedNodes.contains(head)) {
                return true;
            }
            visitedNodes.add(head);
            head = head.next;
        }
        return false;
    }

    static public Node deleteMiddle(Node head) {
        Node prev = null, fast = head, slow = head;
        if (head.next.next == null) {
            head.next = null;
            return head;
        }
        if (head.next == null) {
            return null;
        }
        while (fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
            if (fast.next == null) {
                break;
            }
        }
        prev.next = prev.next.next;
        return head;
    }

    static Node removeNthFromEnd(Node head, int n) {
//        Node dummy = new Node(0);
//        dummy.next = head;
//        Node fast = dummy, slow = dummy;
        Node fast = head, slow=head;
        for(int i=0;i<=n;i++) {
            fast = fast.next;
        }
        while(fast!=null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    static public Node oddEvenList(Node head) {
        Node evenList = new Node(-1),oddList = new Node(-1);
        Node even = evenList, odd = oddList, temp = head;
        if (head == null || head.next == null) {
            return head;
        }
        int i=0;
        while(temp!=null) {
            if(i%2==0) {
                even.next = temp;
                even = temp;
            } else {
                odd.next = temp;
                odd = temp;
            }
            i++;
            temp = temp.next;
        }
        even.next = oddList.next;
        odd.next  = null;
        Node newNode = evenList.next;
        return newNode;
    }

  static public Node reverseLL(Node head) {
        Node curr = head, pointer = null;
        while(curr!=null) {
            pointer = curr;
            curr =curr.next;
        }
        return pointer;
 }

    static Node rotateRight(Node head, int k) {
        Node tail = head;
        if(head == null) {
            return head;
        }
        int n = 1;
        while (tail.next != null) {
            n++;
            tail = tail.next;
        }
        if (k % n == 0) {
            return head;
        }
        k = k % n;
        tail.next = head;
        Node newNode = findNNode(head, n-k);
        head = newNode.next;
        newNode.next = null;
        return head;
    }

    public static Node findNNode(Node head, int k) {
        int c=1;
        while(head!=null) {
            if(c==k) {
                return head;
            }
            c++;
            head = head.next;
        }
        return head;
    }

    static  Node deleteDuplicates(Node head) {
        Node temp = head;
        if (head == null || head.next == null) {
            return head;
        }
        while (temp.next != null) {
            if (temp.data == temp.next.data) {
                if (temp == head) {
                    head = head.next;
                } else if (temp.next.next == null) {
                    temp.next = null;
                } 
                else {
                    Node newNode = temp.next;
                }
            }
            temp = temp.next;
        }
        return head;
    }

    static public Node modifiedList(int[] nums, Node head) {
        ArrayList<Integer> list = new ArrayList<>();
        while(head!=null) {
            list.add(head.data);
            head = head.next;
        }


        Node node = new Node(list.get(0));
        for(int j=1;j<list.size();j++) {
            Node newNode = new Node(list.get(j));
            node.next = newNode;
            node = node.next;
        }
        return node;
    }
    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true;

        // Step 1: Find middle
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        Node secondHalf = reverse(slow.next);

        // Step 3: Compare halves
        Node firstHalf = head;
        while (secondHalf != null) {
            if (firstHalf.data != secondHalf.data) return false;
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    public static Node reverse(Node head) {
        if(head.next == null) return head;
        Node prev = null;
        while(head!=null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
       // modifiedList(a,head);
        //printList(removeKthElement(head,3));
       //printList(insertKthElement(head,4,90));
       // printList()
        System.out.println(isPalindrome(head));
//        Node head = convertArrayToLinkedList(a);
//        Node head2 = convertArrayToLinkedList(a2);
//        Node node = insertKthElement(head, 6, 567);
//        System.out.println(hasCycle(head));
//        Node node = deleteMiddle(head);
      // Node node = removeNthFromEnd(head,2);
//          Node node  = rotateRight(head,2);
            //Node node =  deleteDuplicates(head);
//        while (node != null) {
//            System.out.print(node.data + " ");
//            node = node.next;
//        }
//        LinkedListPackage.Node t = removeTail(node);
//        while(t!=null) {
//            System.out.println(t.data+" "+ "->");
//            t =t.next;
//        }
//        while (node != null) {
//            System.out.print(node.data + " ");
//            node = node.next;
//        }

    }
}

class Node {
    int data;
    Node next;

    Node(int data1, Node next1) {
        this.data = data1;
        this.next = next1;
    }

    Node(int data1) {
        this.data = data1;
        this.next = null;
    }
}
