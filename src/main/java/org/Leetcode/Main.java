package org.Leetcode;

public class Main {
    public static void main(String[] args) {

        PathSum113 p113 = new PathSum113();
        Integer[] arr = {5,4,8,11,null,13,4,7,2,null,null,5,1};
        TreeNode tree = Trees.fromLevelOrder(arr);


        p113.pathSum(tree,22);
    }
}