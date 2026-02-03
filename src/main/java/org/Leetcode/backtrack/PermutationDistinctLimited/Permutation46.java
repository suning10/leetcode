package org.Leetcode.backtrack.PermutationDistinctLimited;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutation46 {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums){
        /**
         * can't use start to fix relative order within nums
         * 1,2 != 2,1
         * instead use used to track if a num has been used
         * b^d
         * choice at each digit, it has nums.length choices, b = n
         * each recursion has n depth (for loop)
         * O(n!)
         */

        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums,used,track);

        return res;
    }

    void backtrack(int[] nums, boolean[] used, LinkedList<Integer> track){

        if(track.size() == nums.length){
            res.add(new LinkedList<>(track));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(used[i]) continue;
            used[i] = true;
            track.addLast(nums[i]);
            backtrack(nums,used,track);
            used[i] = false;
            track.removeLast();

        }
    }
}
