package org.Leetcode.slidewindow;


import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers nums and an integer k,
 * return the number of contiguous subarrays where the product of all the elements
 * in the subarray is strictly less than k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,5,2,6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 */
public class subArrayProductLessThanK713 {


    /**
     *
     * slide window
     * when to expand window -> when product < k
     * when to shrink the window -> when product >k
     * when to update the result -> after shrink, the subarray end with right must be legit
     *
     * intuition
     * [1,2,3,4,5] k = 100
     * end with 3, 1,2,3, 2,3 3
     * end with 4, 1,2,3,4 2,3,4, 3,4 4
     * end with 5, 1,2,3,4,5 2,3,4,5, 3,4,5, 4,5, 5
     *
     * conclusion: # of subarray ending with n = ind(n) - left + 1
     */
    public int numSubarrayProductLessThanK(int[] nums, int k){
        int left, right;
        left = 0;
        right = 0; // [0,1)
        long product = 1;
        int res = 0;
        while(right < nums.length){
            int in = nums[right];
            product = product * in;

            while(left <= right && product >= k){
                product /= nums[left];
                left++;
            }
            res+= right - left + 1;
            right++;
        }
        return res;
    }



}
