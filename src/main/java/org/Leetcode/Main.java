package org.Leetcode;

import org.Leetcode.DP.SellStock;

public class Main {
    public static void main(String[] args) {
        /*
        PathSum113 p113 = new PathSum113();
        Integer[] arr = {5,4,8,11,null,13,4,7,2,null,null,5,1};
        TreeNode tree = Trees.fromLevelOrder(arr);
        p113.pathSum(tree,22);

         */

        /* main for permutation
        Permutation43 p = new Permutation43();
        var res = p.permute(new int[]{1,2,3})
        System.out.println(res);

         */

        /**
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] res = dailyTemperatures.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});
        for (int i:res
             ) {
            System.out.println(i);
        }
         */

        SellStock sellStock = new SellStock();
        int max = sellStock.maxProfit_sellStock2(new int[]{1,2,3,4,5});

        System.out.println(max);
    }
}