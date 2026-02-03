package org.Leetcode.backtrack.UnlimitedDistinct;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 */
public class CombinitionSum {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        /**
         * equal to combinations
         * not increase start by 1 everytime
         * this way the track will consider using unlimited number of nums[i]
         */
        LinkedList<Integer> track = new LinkedList<>();
        backtrak(candidates,0,track,target,0);
        return res;


    }

    void backtrak(int[] nums, int start, LinkedList<Integer> track,int target, int curSum){

        //base case
        if(target == curSum){
            res.add(new LinkedList<>(track) );
            return;
        }
        if(curSum > target) return;


        //backtrack
        for(int i = start; i < nums.length; i++){
            track.addLast(nums[i]);
            curSum+=nums[i];
            backtrak(nums,i,track,target,curSum);
            curSum-=nums[i];
            track.removeLast();
        }
    }
}
