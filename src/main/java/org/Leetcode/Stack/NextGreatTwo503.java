package org.Leetcode.Stack;


import java.util.Stack;

/**
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
 * return the next greater number for every element in nums.
 *
 * The next greater number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number.
 * If it doesn't exist, return -1 for this number.
 *
 *
 */
public class NextGreatTwo503 {

    public int[] nextGreaterElements(int[] nums){
        /**
         * address circular
         * loop 2 * n and use % to get the index
         */

        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];
        for(int i = n * 2 - 1; i>=0; i--){
            while(!stack.isEmpty() && nums[i%n] >= stack.peek() ) stack.pop();
            res[i % n] = stack.isEmpty()?-1:stack.peek();// refresh the value when enter the first half
            stack.push(nums[i%n]);

        }

        return res;
    }
}
