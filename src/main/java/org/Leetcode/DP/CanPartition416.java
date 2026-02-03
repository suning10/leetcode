package org.Leetcode.DP;

import java.util.Arrays;

public class CanPartition416 {

    /**
     *General Knapsack Problems
     * w[i] : weight of ith item
     * v[i]: value of ith item
     * w: max weight allowed
     *** Goal: calculate max value while <= w
     * dp[i][j]: first i element with weight limit j, max value
     * dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i] + v[i]) if w[i] < j
     */

    /**
     * can partition by 2, if we can pick number that sum is nums / 2
     */
    public boolean canPartition(int[] nums){
        int sum = 0;
        for(int i: nums){
            sum+= i;
        }
        if(sum % 2 != 0) return false;
        sum = sum / 2;
        boolean[][] dp = new boolean[nums.length+1][sum+1];
        /**
         * dp[i][j]: can get j use first i element
         * Is Empty has a meaning -> yes. Eg Sell stock, is day - 1 has a meaning? no
         * in this problem, dp[0][sum] and dp[i][0] has a meaning
         * dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]
         */

        for(int i = 0; i <= nums.length;i++) dp[i][0] = true;
        for(int i = 0; i <= sum;i++) dp[0][i] = false;

        for(int i = 1; i<= nums.length; i++){
            for(int j = 1; j <= sum; j++){
                if(nums[i-1] <= j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i-1]];
                }
                else dp[i][j] = dp[i-1][j];
            }
        }

        return dp[nums.length][sum];

    }
}
