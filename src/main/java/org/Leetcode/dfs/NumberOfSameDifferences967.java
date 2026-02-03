package org.Leetcode.dfs;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given two integers n and k, return an array of all the
 * integers of length n where the difference between every
 * two consecutive digits is k. You may return the answer in any order.
 *
 * Note that the integers should not have leading zeros. Integers as 02 and 043 are not allowed.
 */
public class NumberOfSameDifferences967 {

    /**
     *choice: 0 - 9
     * base case, when path = n
     *
     */
    List<Integer> res = new ArrayList<>();
    public int[] numsSameConsecDiff(int n, int k){

        //do not know the start point from n
        //need to loop
        /**
         * backtrack(n,k,1,0) will only find valid output start with 1
         */
//        for(int i = 0; i<10; i++){
//            backtrack(n,k,i,0);
//        }

        backtrack(n,k,0,0);
        res = res.stream().distinct().collect(Collectors.toList());
        int[] result = new int[res.size()];
        for(int i = 0; i < result.length; i++){
                result[i] = res.get(i);
        }
        return result;
    }

    void backtrack(int n, int k, int path, int curDigit){
        if(String.valueOf(path).length() == n){
            res.add(path);
            return;
        }

        for(int i = 0; i < 10; i++ ){

            if(curDigit ==0 && i == 0) continue;
            //first digit does not need to be k
            if(curDigit > 0 && Math.abs(i - path % 10) != k) {
                continue;
            }



            path = path * 10 + i;

            backtrack(n,k,path,curDigit + 1);

            path = path / 10;

        }
    }
}
