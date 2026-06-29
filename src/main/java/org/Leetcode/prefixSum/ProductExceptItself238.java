package org.Leetcode.prefixSum;

public class ProductExceptItself238 {
    /**
     * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
     *
     * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
     *
     * You must write an algorithm that runs in O(n) time and without using the division operation.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,4]
     * Output: [24,12,8,6]
     */

    public int[] productExceptSelf(int[] nums){
        /**
         * similar to 724,
         * find left product, right product
         */

        int[] preLeft = new int[nums.length];
        preLeft[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            preLeft[i] = preLeft[i - 1] * nums[i];
        }
        int[] preRight = new int[nums.length];
        preRight[nums.length - 1] = nums[nums.length - 1];
        for(int i = nums.length - 2; i >= 0; i--){
            preRight[i] = preRight[i + 1] * nums[i];
        }
        int[] res = new int[nums.length];
        res[0] = preRight[1];
        res[nums.length - 1] = preLeft[nums.length - 2];
        for(int i = 1; i < nums.length - 1; i++){
            res[i] = preLeft[i - 1] * preRight[i + 1];
        }
        return res;
    }
}
