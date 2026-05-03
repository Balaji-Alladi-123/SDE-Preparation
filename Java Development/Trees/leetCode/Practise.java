package Trees.leetCode;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Practise {
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return res;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(curr.val);
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);

      //  root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
//
         //root.right.left = new TreeNode(6);
//
//        root.left.right.left = new TreeNode(6);
//        root.left.right.right = new TreeNode(7);
//
//        root.right.right.right = new TreeNode(9);
//        postorderTraversal(root);
        //countNodes(root);
      //  kthSmallest(root, 1);
        //countNodes(root);
//        int[] arr = {8,5,1,7,10,12};
//        bstFromPreorder(arr);
        kthSmallest(root,1);
    }

    static public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        int count = 1;
        int c1 = countNodes(root.left);
        int c2 = countNodes(root.right);
        count += c1 + c2;
        return count;
    }

//    static int kthSmallest(TreeNode root, int k) {
//        List<Integer> list = new ArrayList<>();
//        inOrder(root, list);
//        return list.get(k - 1);
//    }
//
//    public static void inOrder(TreeNode root, List<Integer> list) {
//        if (root == null) return;
//        inOrder(root.left, list);
//        list.add(root.val);
//        inOrder(root.right, list);
//    }
    public static TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;
        for (int a : preorder) {
            root = construct(root, a);
        }
        return  root;
    }

    public static TreeNode construct(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val < val) {
            root.right = construct(root.right, val);
        }
        if (root.val > val) {
            root.left = construct(root.left, val);
        }
        return root;
    }
    static int result = -1;
    static int count=0;
    public static int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return result;
    }

    public static void inOrder(TreeNode root, int k) {
        if (root == null)
            return;
        if (count >= k) return;
        inOrder(root.left, k);
        count++;
        if (count == k) {
            result = root.val;
        };
        inOrder(root.right, k);
    }
}
