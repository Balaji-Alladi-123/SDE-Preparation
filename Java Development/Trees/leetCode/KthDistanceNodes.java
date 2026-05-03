package Trees.leetCode;

import java.util.*;

public class KthDistanceNodes {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        distanceK(root,root.left,2);
    }
    public  static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if(root==null) return new ArrayList<>();
        if(root.left == null && root.right ==null) return new ArrayList<>(root.val);
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        mapWithParent(root,parentMap);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        int level=0;
        Map<TreeNode, Boolean> visitedMap = new HashMap<>();
        visitedMap.put(target, true);
        while(!queue.isEmpty()) {
            if(level ==k) break;
            level++;
            int len = queue.size();
            for(int i=0;i<len;i++) {
                TreeNode curr = queue.poll();
                if(curr.left!= null && visitedMap.get(curr.left) ==null) {
                    queue.offer(curr.left);
                    visitedMap.put(curr.left,true);
                }
                if(curr.right!= null && visitedMap.get(curr.right) ==null) {
                    queue.offer(curr.right);
                    visitedMap.put(curr.right,true);
                }
                if(parentMap.get(curr)!=null && visitedMap.get(parentMap.get(curr)) ==null) {
                    queue.offer(parentMap.get(curr));
                    visitedMap.put(parentMap.get(curr),true);
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            result.add(curr.val);
        }
        return result;
    }
    public static void mapWithParent(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                queue.offer(curr.right);
            }
        }
    }
}
