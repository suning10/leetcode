package org.Leetcode.BinaryTree;

import org.Leetcode.TreeNode;

public class validateBST98 {
    public boolean isValidBST(TreeNode root){
        return dfs(root,null,null);
    }

    Boolean dfs(TreeNode root, TreeNode min, TreeNode max){
        /**
         * giving a tree, return true if it is a valid BST
         * valid BST: min < root < max
         * left subtree: min < left < root
         * right subtree root < right < max;
         */

        if(root == null) return true;
        if(min != null && root.val < min.val) return false;
        if(max != null && root.val > max.val) return false;
        return dfs(root.left, min, root) && dfs(root.right,root, max);
    }
}
