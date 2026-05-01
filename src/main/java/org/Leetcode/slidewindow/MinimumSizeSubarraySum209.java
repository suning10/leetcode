package org.Leetcode.slidewindow;

public class MinimumSizeSubarraySum209 {
    /**
     * Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target.
     * If there is no such subarray, return 0 instead.

     * Example 1:
     *
     * Input: target = 7, nums = [2,3,1,2,4,3]
     * Output: 2
     * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
     */

    public int minSubArrayLen(int target, int[] nums){
        /**
         * when choose dp over slide window
         * if nums has negative numbers
         * move left or right does not guarantee increase or decrease the sum of window
         */

        /**
         * when to increase: window < target
         * shrink when > target
         * update curMin when == target
         */
        int curSum = 0;
        int minLength = nums.length + 1;
        int left = 0;
        int right = 0;
        while(right < nums.length){
            curSum = curSum + nums[right];
            while(curSum >= target){
                minLength = Math.max(minLength, right - left + 1);
                curSum -= nums[left];
                left++;
            }
            right++;
        }

        return minLength == nums.length + 1 ? 0 : minLength;
    }
}
