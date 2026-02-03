package org.Leetcode.DP;

import java.util.Arrays;

public class CoinChange518 {

    public int change(int amount, int[] coins){
        /**
         * dp[i]: # of ways to get amount i
         * dp[i] = sum(dp[i-coin]) for coin in coins
         */

        int[] dp = new int[amount+1];
        Arrays.fill(dp,0);
        dp[0] = 1;
        //loop coin first
        // avoid permuatation
        //each time add a different digit at the end
        //order do matters
        /**
         * 2 = 1+1 || 0, 2
         */
        for(int coin: coins){
            for(int i = coin; i <= amount; i++){
                    dp[i] += dp[i-coin];
                }
            }


        return dp[amount] == 0? -1: dp[amount];
    }

    public int changeKnapsack(int amount, int[] coins){
        /**
         * dp[i][j]: # of ways to get amount j using first i coins
         * choice: get another i or not take i
         * dp[i][j] = dp[i][j-coins[i-1]] + dp[i-1][j]
         */

        int[][] dp = new int[coins.length + 1][amount+1];

        for(int i = 0; i <= coins.length; i++ ){
            dp[i][0] = 1;
        }

        for(int i = 1; i <= coins.length; i++){
            for(int j = 1; j <= amount; j++){
                if(coins[i-1] > j){
                    dp[i][j] = dp[i-1][j];
                }

                else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                }
            }
        }


        return dp[coins.length][amount] == 0? -1: dp[coins.length][amount];
    }


}
