package org.Leetcode.BinaryTree;

import org.Leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelTraverse102 {
    /**
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[3],[9,20],[15,7]]
     */
    public List<List<Integer>> levelOrder(TreeNode root){

        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> curLevel = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode cur = q.poll();
                curLevel.add(cur.val);
                if(cur.left != null) q.offer(cur.left);
                if(cur.right != null) q.offer(cur.right);
            }
            res.add(curLevel);
        }
        return res;
    }
}
