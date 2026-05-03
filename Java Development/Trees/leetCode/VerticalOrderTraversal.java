package Trees.leetCode;

import java.util.*;

public class VerticalOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        verticalTraversal(root);
    }

    static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer,List<Integer>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root,0));
        map.computeIfAbsent(0, k -> new ArrayList<>()).add(root.val);
        while(!queue.isEmpty()) {
            int len = queue.size();
            while(len>0) {
                Pair pair= queue.poll();
                TreeNode node = pair.node;
                int col = pair.hd;
                if(node.left!=null) {
                    queue.offer(new Pair(node.left,col-1));
                    map.computeIfAbsent(col-1, k-> new ArrayList<>()).add(node.left.val);
                }
                if(node.right!=null) {
                    queue.offer(new Pair(node.right,col+1));
                    map.computeIfAbsent(col+1, k-> new ArrayList<>()).add(node.right.val);
                }
                len--;
            }
        }
        for(int i: map.keySet()) {
            res.add(map.get(i));
        }
        return res;
    }
}
