package org.Leetcode.backtrack.PermutationNotDistinctLimited;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Permutation47 {

    List<List<Integer>> res = new ArrayList<>();
    //Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
    public List<List<Integer>> permuteUnique(int[] nums){
        /**
         * nums has duplicate, permutation
         * in addition to nums[i] != nums[i-1], need to fix order, 2-> 2' -> 2''
         * achieve this by make sure used[i-1] == true;
         * this means only add to track if previous num already been used
         */
        LinkedList<Integer> track = new LinkedList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack(nums,used,track);
        return  res;


    }

    void backtrack(int[] nums, boolean[] used, LinkedList<Integer> track){
        //base case
        if(track.size() == nums.length){
            res.add(new LinkedList<>(track));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i]) continue;;
            if(i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            used[i] = true;
            track.addLast(nums[i]);
            backtrack(nums,used,track);
            used[i] = false;
            track.removeLast();
        }
    }
}
