package org.Leetcode.Stack;


import java.util.HashMap;
import java.util.Stack;

/**
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 *
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 *
 * Example 1:
 *
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 */
public class NextGreaterValue496 {


    public int[] nextGreaterElement(int[] nums1, int[] nums2){
        /**
         * nums1 is the subset of nums2
         * find all next great value of nums2
         * store it in a hashmap
         * How to Find Next Greater Value
         * 1. loop backwards
         * 2. while stack is not empty or next is smaller -> pop the top value of stack
         * 2.1 this works because the value before will be blocked by cur value
         * push cur value to the stack for prev
         */

        Stack<Integer> stack = new Stack<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = nums2.length - 1; i >=0; i--){
            // pop the one smaller than current
            // for further i, it will blocked by i
            while(!stack.isEmpty() && nums2[i] > stack.peek()) stack.pop();
            int next = stack.isEmpty()? -1: stack.peek();
            map.put(nums2[i], next);
            stack.push(nums2[i]);
        }
        int[] res = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            res[i] = map.get(nums1[i]);
        }

        return res;
    }
}
