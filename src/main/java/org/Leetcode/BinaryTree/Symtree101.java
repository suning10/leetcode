package org.Leetcode.BinaryTree;

import org.Leetcode.TreeNode;


//Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
public class Symtree101 {
    public boolean isSymmetric(TreeNode root){
        if(root == null) return true;
        return dfs(root.left,root.right);

    }

    boolean dfs(TreeNode a, TreeNode b){
        if(a==null || b==null) return a == b;
        if(a.val != b.val  ) return false;
        boolean left = dfs(a.left, b.right);
        boolean right = dfs(a.right, b.left);

        return left && right;
    }


}
