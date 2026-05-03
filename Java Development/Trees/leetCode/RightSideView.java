package Trees.leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightSideView {
    static List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        queue.offer(root);
        while (!queue.isEmpty()) {

            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode curr = queue.poll();
                if(i == len-1) {
                    list.add(curr.val);
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }

        }
        return list;
    }
    public static void main(String[] args) {
        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(20);
        node.right = new TreeNode(30);
        node.left.left = new TreeNode(40);
        node.left.right = new TreeNode(60);
        node.right.left = new TreeNode(90);
        node.right.right = new TreeNode(100);
        rightSideView(node);
    }
}
