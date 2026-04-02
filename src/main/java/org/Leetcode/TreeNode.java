package org.Leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}

/** Utilities for constructing/serializing binary trees from level-order arrays. */
final class Trees {

    private Trees() {}

    /**
     * Build a binary tree from a level-order array.
     * Example: [1,2,3,null,4] ->
     * 1
     * / \
     * 2 3
     * \
     * 4
     *
     * @param levelOrder Integer[] where null means no node at that position
     * @return root TreeNode (or null if array empty or root is null)
     */
    public static TreeNode fromLevelOrder(Integer[] levelOrder) {
        if (levelOrder == null || levelOrder.length == 0 || levelOrder[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int i = 1;

        while (!q.isEmpty() && i < levelOrder.length) {
            TreeNode node = q.poll();
            // left child
            if (i < levelOrder.length) {
                Integer v = levelOrder[i++];
                if (v != null) {
                    node.left = new TreeNode(v);
                    q.add(node.left);
                }
            }
            // right child
            if (i < levelOrder.length) {
                Integer v = levelOrder[i++];
                if (v != null) {
                    node.right = new TreeNode(v);
                    q.add(node.right);
                }
            }
        }
        return root;
    }

    /**
     * Serialize a tree to a trimmed level-order list (trailing nulls removed).
     * Useful for testing equality vs. your input array.
     */
    public static List<Integer> toLevelOrder(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        if (root == null) return out;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n == null) {
                out.add(null);
            } else {
                out.add(n.val);
                q.add(n.left);
                q.add(n.right);
            }
        }
        // trim trailing nulls
        int last = out.size() - 1;
        while (last >= 0 && out.get(last) == null) last--;
        return out.subList(0, last + 1);
    }
}


