package org.Leetcode.DP;

import java.util.Arrays;

public class CoinChange322 {

    public int coinChange1(int[] coins, int amount){
        /**
         * knapsack problem
         * choice take coin or not
         * state: amount change or not
         */

        /**
         * dp[i]: # fewest # of coins need to get amount i
         * dp[i] = Min(dp[i-nums[j]) for j in coins
         * is dp[0] is a legit entry
         *
         */

        int[] dp = new int[amount + 1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;


        for(int i = 1; i<= amount; i++){
            for(int coin: coins){
                if(i>=coin){
                    dp[i] = Math.min(dp[i],dp[i-coin] + 1);
                }
            }
        }

        return dp[amount] == amount + 1 ? -1:dp[amount];


    }
}
