package Trees;

class BinaryNode {
    int value;
    int height;
    BinaryNode left;
    BinaryNode right;

    BinaryNode(int value) {
        this.value = value;
    }
}

public class BinarySearchTree {
    BinarySearchTree() {

    }

    BinaryNode root;

    void insert(int value) {
        root = insert(value, root);
    }

    BinaryNode insert(int value, BinaryNode root) {
        if (root == null) {
            root = new BinaryNode(value);
            return root;
        }

        if (value < root.value) {
            root.left = insert(value, root.left);
        }

        if (value > root.value) {
            root.right = insert(value, root.right);
        }
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }

    boolean balanced() {
        return balanced(root);
    }

    boolean balanced(BinaryNode root) {
        if(root==null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <=1 && balanced(root.left) && balanced(root.right);
    }

    int height() {
        return height(root);
    }

    int height(BinaryNode root) {
        if (root == null) {
            return -1;
        }
        return root.height;
    }

    void populate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            this.insert(nums[i]);
        }
    }

    void display() {
        displayBinaryTree(root, 0);
    }

    void displayBinaryTree(BinaryNode root, int level) {
        if (root == null) {
            return;
        }
        displayBinaryTree(root.left, level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("| \t \t");
            }
            System.out.println("|---->" + root.value);
        } else {
            System.out.println(root.value);
        }
        displayBinaryTree(root.right, level + 1);
    }

    public static void main(String[] args) {
        int[] nums = {10, 3, 4, 6, 77, 56, 34, 0, 86, 43, 76};
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.populate(nums);
        binarySearchTree.display();
        System.out.println(binarySearchTree.height());
        System.out.println(binarySearchTree.balanced());
    }

}
