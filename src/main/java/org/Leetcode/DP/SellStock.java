package org.Leetcode.DP;

import java.util.Arrays;

public class SellStock {

    /**
    You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

    On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However,
    you can sell and buy the stock multiple times on the same day, ensuring you never hold more than one share of the stock.

    Find and return the maximum profit you can achieve.
     */
    public int maxProfit_sellStock2(int[] prices){
        //use dp
        /**
         * dp[i][j][0]: ith day, j times transaction (buy and sell is one transaction)
         * 0: no stock; 1: has stock
         * in this setting, j has unlimited transactions, j = j - 1
         * dp[i][0] = max(dp[i-1][1] + price[i], dp[i-1][0])
         * Do nothing vs sell on ith day
         */

        int day = prices.length;
        //base case
        int[][] dp = new int[day + 1][2];
        //Arrays.fill(dp,Integer.MIN_VALUE);
        dp[1][0] = 0;
        dp[1][1] = - prices[0];

        for(int i = 2; i <= day; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i-1]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i-1]);
        }

        return dp[day][0];
    }

    /**
     *     You are given an array prices where prices[i] is the price of a given stock on the ith day.
     *
     *     Find the maximum profit you can achieve. You may complete as many transactions as you like
     *     (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
     *
     *     After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
     */

    public int maxProfit_sellStock3(int[] prices){
        /**
         * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + price[i])
         * dp[i][1] = max(dp[i-2][0] - price[i], dp[i-1][1])
         */

        int days = prices.length;
        int[][] dp = new int[days][2];
        //day 0 no stock
        dp[0][0] = 0;
        //day 0 buy stock
        dp[0][1] = -prices[0];
        // need i - 2 to avoid index out of range
        dp[1][0] = Math.max(0,prices[1] - prices[0]);
        dp[1][1] = Math.max(-prices[0], - prices[1]);

        for(int i = 2; i < days; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
        }

        return dp[days-1][0];

    }

    /**
     * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
     *
     * Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     */

    public int maxProfit_sellStock4(int[] prices, int k){
        /**
         * dp[i][j][0/1]: ith day, j transactions, have or not have stocks
         * dp[i][j][0] = max(dp[i-1][j][0], dp[i-1][j][1] + price[i])
         * dp[i][j][1] = max(dp[i-1][j-1][0] - price[i], dp[i-1][j][1])
         */
        int days = prices.length;
        int[][][] dp = new int[days][k+1][2];


        for(int i = 0; i < days; i++){
            dp[i][0][0] = 0; // when k == 0, no transaction can be made
            dp[i][0][1] = Integer.MIN_VALUE;
        }
        for(int j = 1; j <=k; j++){
            dp[0][j][0] = 0;
            dp[0][j][1] = -prices[0];
        }

        for(int i = 1; i < days; i++){
            for(int j = 1; j <= k; j++){
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j-1][0] - prices[i], dp[i-1][j][1]);
            }
        }

        return dp[days-1][k][0];

    }

}
