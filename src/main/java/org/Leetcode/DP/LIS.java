package org.Leetcode.DP;

public class LIS {

    public int lengthOfLIS(int[] nums){
        /**
         * dp: LIS of Nums ending with i
         * dp[i] = max(dp[j]+1): for j = 1 to i - 1 and nums[i] > nums[j]
         * Time: O(n^2)
         * Space O(n)
         */

        //dp: index of nums, not then length of array
        int[] dp = new int[nums.length];
        //base case
        dp[0] = 1;
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j ++){
                if(nums[j] > nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        //get result
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
