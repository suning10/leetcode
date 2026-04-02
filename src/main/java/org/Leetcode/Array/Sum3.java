package org.Leetcode.Array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Given an integer array nums, return all the triplets [nums[i], nums[j],
 *
 * nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 */

public class Sum3 {
    public List<List<Integer>> threeSum(int[] nums){
        /**
         *
         * 1. 3sum = 0
         * find first nums[i], find 2sum == -nums[i]
         *
         * 2. how to address duplicate triplets
         * -1,-2,-2,3,3 will find -1,-2,3 three times
         * key is to skip the same element while move left and right pointer
         * also when looping nums, skip the same number
         */
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            int toFind = -nums[i];
            List<List<Integer>> cur = twoSum(nums, toFind,i);
            for(List<Integer> t: cur){
                t.add(nums[i]);
                res.add(t);
            }
            //skip same i
            int curNumber = nums[i];
            while(i < nums.length && nums[i] == curNumber) i++;
        }
        return res;


    }

    //also need start to avoid search from beginning which will still result in duplicates
    List<List<Integer>> twoSum(int[] nums, int target, int start){
        int left = start;
        int right = nums.length - 1;
        List<List<Integer>> res = new ArrayList<>();
        while(left < right){
            if (nums[left] + nums[right] == target) {
                res.add(new ArrayList<>(Arrays.asList(nums[left],nums[right])));
                //skip same value of left and right
                int curLeft = nums[left];
                while(left < right && nums[left] == curLeft) left++;
                int curRight = nums[right];
                while(left < right && nums[right] == curRight) right--;
            }
            else if (nums[left] + nums[right] < target) {
                int curLeft = nums[left];
                while(left < right && nums[left] == curLeft) left++;
            }
            else{
                int curRight = nums[right];
                while(left < right && nums[right] == curRight) right--;
            }
        }

        return res;
    }
}
