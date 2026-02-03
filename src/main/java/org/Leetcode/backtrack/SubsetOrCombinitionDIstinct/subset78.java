package org.Leetcode.backtrack.SubsetOrCombinitionDIstinct;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class subset78 {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums){
        /**
         * no duplicate, choose only once
         * choice: choose from nums
         * track: current choice from nums
         * when to stop: every combinition at level n: n < nums.length
         * use start to keep the same order, 1,2,3, 3,1,2 2,1,3
         * fix 1 will only have 1,2,3. 1,3,2
         * O(2^n) choice^depth
         * choice take or skip = 2
         * depth = n (for loop inside each recursion
         */

        LinkedList<Integer> track = new LinkedList<>();
        backtrak(nums,0,track);
        return res;

    }

    void backtrak(int[] nums, int start, LinkedList<Integer> track){

        //base case
        res.add(new LinkedList<>(track) );

        //backtrack
        for(int i = start; i < nums.length; i++){
            track.addLast(nums[i]);
            backtrak(nums,i+1,track);
            track.removeLast();
        }
    }
}
