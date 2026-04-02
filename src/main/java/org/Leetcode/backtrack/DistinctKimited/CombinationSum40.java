package org.Leetcode.backtrack.DistinctKimited;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum40 {



    //Each number in candidates may only be used once in the combination.
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target){

        /**
         * candidate now has duplicates
         * sort and make sure nums[i] != nums[i-1]
         */
        Arrays.sort(candidates);
        LinkedList<Integer> track = new LinkedList<>();

        backtrack(candidates,0,track,target,0);
        return res;
    }

    void backtrack(int[] nums, int start, LinkedList<Integer> track, int target, int curSum){

        //base case
        if(target == curSum){
            res.add(new LinkedList<>(track) );
            return;
        }
        if(curSum > target) return;


        //backtrack
        for(int i = start; i < nums.length; i++){
            /**
             * note: i > start to make sure only the number before start are not the same
             */
            if(i > start && nums[i]== nums[i-1]) continue;
            track.addLast(nums[i]);
            curSum+=nums[i];
            backtrack(nums,i+1,track,target,curSum);
            curSum-=nums[i];
            track.removeLast();
        }
    }
}
