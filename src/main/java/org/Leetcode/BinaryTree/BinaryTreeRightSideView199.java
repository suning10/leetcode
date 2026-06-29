package org.Leetcode.BinaryTree;

import org.Leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView199 {
    public List<Integer> rightSideView(TreeNode root){
        /**
         * use BFS
         * add to res once current level by the end of current loop
         */

        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            int right = -101;
            for(int i = 0; i < size; i++){
                TreeNode cur = q.poll();
                right = cur.val;
                q.offer(cur.left);
                q.offer(cur.right);
            }
            res.add(right);
        }
        return res;
    }
}
