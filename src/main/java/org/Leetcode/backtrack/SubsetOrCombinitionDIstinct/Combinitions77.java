package org.Leetcode.backtrack.SubsetOrCombinitionDIstinct;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combinitions77 {

    List<List<Integer>> res = new ArrayList<>();
    //Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
    public List<List<Integer>> combine(int n, int k){
        /**
         * can not chooose more than 1 time, no duplicate
         * equal to subset only have k element
         */
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(n,k,1,track);
        return res;

    }

    void backtrack(int n, int k, int start, LinkedList<Integer> track){

        //base case
        if(track.size() == k) res.add(new LinkedList<>(track));
        //backtrack
        for(int i = start; i <= n; i++){
            track.addLast(i);
            backtrack(n,k,i+1,track);
            track.removeLast();
        }
    }
}
