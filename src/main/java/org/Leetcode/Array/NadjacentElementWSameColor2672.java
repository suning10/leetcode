package org.Leetcode.Array;

import java.util.Arrays;

/**
 * You are given an integer n representing an array colors of length n where all elements are set to 0's meaning uncolored.
 *
 * You are also given a 2D integer array queries where queries[i] = [indexi, colori]. For the ith query:
 *
 * Set colors[indexi] to colori.
 * Count the number of adjacent pairs in colors which have the same color (regardless of colori).
 * Return an array answer of the same length as queries where answer[i] is the answer to the ith query.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, queries = [[0,2],[1,2],[3,1],[1,1],[2,1]]
 *
 * Output: [0,1,1,0,2]
 *
 * Explanation:
 *
 * Initially array colors = [0,0,0,0], where 0 denotes uncolored elements of the array.
 * After the 1st query colors = [2,0,0,0]. The count of adjacent pairs with the same color is 0.
 * After the 2nd query colors = [2,2,0,0]. The count of adjacent pairs with the same color is 1.
 * After the 3rd query colors = [2,2,0,1]. The count of adjacent pairs with the same color is 1.
 * After the 4th query colors = [2,1,0,1]. The count of adjacent pairs with the same color is 0.
 * After the 5th query colors = [2,1,1,1]. The count of adjacent pairs with the same color is 2.
 */

public class NadjacentElementWSameColor2672 {

    /**
     *cnt-- when ind == ind-1 or ind = ind+1 before insert
     * cnt++ when ind == ind - 1 or ind++ after insert
     */
    public int[] colorTheArray(int n, int[][] queries) {
        int[] nums = new int[n];
        int cnt = 0;
        int[] res = new int[queries.length];

        for(int i = 0; i < queries.length; i++){
            int ind = queries[i][0];
            int color = queries[i][1];
            int pre = ind > 0? nums[ind-1]: 0;
            int next = ind < n - 1? nums[ind + 1]: 0;
            if(nums[ind] != 0 && pre == nums[ind]) cnt--;
            if(nums[ind] != 0 && next == nums[ind]) cnt--;
            nums[ind] = color;
            if(color == pre) cnt++;
            if(color == next) cnt++;

            res[i] = cnt;
        }

        return res;
    }
}
