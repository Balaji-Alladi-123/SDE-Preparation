package Trees.leetCode;

public class DeleteNode {
   static int result = -1;
  static   int count = 0;
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
//        deleteNode(root,5);
        kthSmallest(root,3);
    }
    static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if(root.val == key) {
            return helper(root);
        }

        TreeNode original = root;

        while(root!=null) {
            if(root.val > key) {
                if(root.left !=null && root.left.val == key) {
                    root.left = helper(root.left);
                    break;
                } else {
                    root = root.left;
                }
            } else {
                if(root.right !=null && root.right.val == key) {
                    root.right = helper(root.right);
                    break;
                } else {
                    root = root.right;
                }
            }
        }
        return original;
    }

    static  TreeNode helper(TreeNode root) {
        if(root.left ==null) {
            return root.right;
        }
        if(root.right == null) {
            return root.left;
        }
        TreeNode rightChild = root.right;
        TreeNode left = findLast(root.left);
        left.right = rightChild;
        return root.left;
    }

    public static TreeNode findLast(TreeNode left) {
        while(left.right == null) {
            return left;
        }
        return findLast(left.right);
    }


    static int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return result;
    }

    static void inOrder(TreeNode root, int k) {
        if (root == null)
            return;

        if (count >= k) return;

        inOrder(root.left, k);
        count++;
        if (count == k) {
            result = root.val;
            return;
        }
        inOrder(root.right, k);
    }

}
