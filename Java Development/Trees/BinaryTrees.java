package Trees;

import java.util.Scanner;

class Node  {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
    }
}

public class BinaryTrees {

    BinaryTrees() {
    }
    private Node root;

    public void insertRoot(Scanner scanner) {
        System.out.print("Enter the Root node:");
        int value = scanner.nextInt();
        root = new Node(value);
        insertChildren(scanner, root);
    }

    private void insertChildren(Scanner scanner, Node root) {
        System.out.println("Do you want to enter the left of" +root.value);
        boolean leftOp = scanner.nextBoolean();
        if(leftOp) {
            System.out.println("Enter the value of the left of" + root.value);
            int value = scanner.nextInt();
            root.left = new Node(value);
            insertChildren(scanner,root.left);
        }
        System.out.println("Do you want to enter the right of" + root.value);
        boolean rightOp = scanner.nextBoolean();
        if(rightOp) {
            System.out.println("Enter the value of the right of" + root.value);
            int value = scanner.nextInt();
            root.right = new Node(value);
            insertChildren(scanner,root.right);
        }
    }

    void display() {
        displayBinaryTree(root, 0);
    }

    void displayBinaryTree(Node root, int level) {
        if(root == null) {
            return;
        }
        displayBinaryTree(root.left, level+1);
        if(level!=0) {
            for(int i = 0;i<level-1;i++) {
                System.out.print("| \t \t");
            }
            System.out.println("|---->"+ root.value);
        } else {
            System.out.println(root.value);
        }
        displayBinaryTree(root.right,level+1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinaryTrees binaryTrees = new BinaryTrees();
        binaryTrees.insertRoot(sc);
         binaryTrees.display();
    }
}
