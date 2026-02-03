package org.Leetcode.backtrack.SubsetNotDistinctLimited;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SubsetTwo90 {

    List<List<Integer>> res = new ArrayList<>();
    //Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
    public List<List<Integer>> subsetsWithDup(int[] nums){

        /**
         * nums contains duplicates
         * 1,2,2' == 1,2'2
         * ways to prone branches to avoid duplicate
         * Sort nums first, continue the loop when nums[i] != nums[i-1]
         * make sure index not out of bound
         */
        LinkedList<Integer> track = new LinkedList<>();
        Arrays.sort(nums);
        backtrack(nums,0,track);
        return res;


    }

    void backtrack(int[] nums, int start,LinkedList<Integer> track  ){
        //base case, each time reach here is a new subset
        // start == nums.length - 1
        res.add(new ArrayList<>(track));

        for(int i = start; i < nums.length; i++){
            /**
             * note: i > start to make sure only the number before start are not the same
             */
            if(i > start && nums[i] == nums[i-1]) continue;
            track.addLast(nums[i]);
            backtrack(nums,i+1,track);
            track.removeLast();
        }


    }
}
