package Trees.leetCode;

import com.sun.source.tree.Tree;

import java.util.*;

public class TopViewOfBinaryTree {
    static ArrayList<Integer> topView(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<>();
        Map<Integer,Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        if(root == null) return res;

        queue.add(new Pair(root,0));
        while(!queue.isEmpty()) {
            Pair pair = queue.poll();
            int h = pair.hd;
            TreeNode temp = pair.node;
            if(!map.containsKey(h)) {
                map.put(h,temp.val);
            }
            if(temp.left !=null) {
                queue.add(new Pair(temp.left,h-1));
            }
            if(temp.right !=null) {
                queue.add(new Pair(temp.right,h+1));
            }
        }

        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }


    public static void main(String[] args) {
        TreeNode node  = new TreeNode(10);
        node.left = new TreeNode(20);
        node.right = new TreeNode(30);
        node.left.left = new TreeNode(40);
        node.left.right = new TreeNode(60);
        node.right.left = new TreeNode(90);
        node.right.right = new TreeNode(100);
        topView(node);
    }
}
class Pair {
    TreeNode node;
    int hd;

    Pair(TreeNode node, int hd) {
        this.node = node;
        this.hd = hd;
    }
}