package Trees;


public class AVLTrees {
    AVLTrees() {

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
        return rotate(root);
    }

    public BinaryNode rotate(BinaryNode node) {
        if (height(node.left) - height(node.right) > 0) {
            if (height(node.left.left) - height(node.left.right) > 0) {
                return rotateRight(node);
            }
            if (height(node.left.left) - height(node.left.right) < 0) {
                node.left = rotateLeft(node);
                return rotateRight(node);
            }
        }
        if (height(node.left) - height(node.right) < -1) {
            if (height(node.right.left) - height(node.right.right) < 0) {
                return  rotateLeft(node);
            }
            if (height(node.right.left) - height(node.right.right) > 0) {
                node.right = rotateRight(node);
                return rotateLeft(node);
            }
        }
        return node;
    }

    public BinaryNode rotateRight(BinaryNode p) {
        BinaryNode c = p.left;
        BinaryNode t = c.right;
        c.right = p;
        p.left = t;

        p.height = Math.max(height(p.left), height(p.right));
        c.height = Math.max(height(c.left), height(c.right));
        return c;
    }

    public BinaryNode rotateLeft(BinaryNode p) {
        BinaryNode c = p.right;
        BinaryNode t = c.left;

        c.left = p;
        p.right = t;
        p.height = Math.max(height(p.left), height(p.right));
        c.height = Math.max(height(c.left), height(c.right));
        return c;
    }

    boolean balanced() {
        return balanced(root);
    }

    boolean balanced(BinaryNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 && balanced(root.left) && balanced(root.right);
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

