package org.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PathSum113 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum){
        needSum = targetSum;
        dfs(root);
        System.out.println(res);
        return res;
    }
    int cur = 0;
    int needSum = 0;
    LinkedList<Integer> l = new LinkedList<>();

    private void dfs(TreeNode root){
        if(root == null) return;
        //preorder: add root.val to cur
        cur += root.val;

        if(root.left == null && root.right == null && cur == needSum) {
            l.addLast(root.val);
            res.add(new LinkedList<>(l));
            l.removeLast();
            cur -= root.val;
            return;
        }
        l.addLast(root.val);
        dfs(root.left);
        dfs(root.right);
        l.removeLast();
        cur -= root.val;

    }



}
