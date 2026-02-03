package org.Leetcode.DP;

public class MaxSubarray53 {

    //Given an integer array nums, find the subarray with the largest sum, and return its sum.

    public int maxSubArray(int[] nums){
        /**
         * dp[i]: max sum ending with i
         * dp[i] = Max(nums[i],dp[i-1]) in case nums[i-1] is negative
         * here i means ending with nums[i]
         * dp[0] means ending with first num
         */

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for(int i = 0; i<nums.length;i++){
            res = Math.max(res,dp[i]);
        }

        return res;

    }
}
