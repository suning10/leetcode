package org.Leetcode.slidewindow;


import java.util.HashMap;

/**
 * You are given a string s and an integer k.
 * You can choose any character of the string and change it to any other uppercase English character.
 * You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter
 * you can get after performing the above operations.
 */
public class LongestRepeatingCharReplacement424 {

    public int characterReplacement(String s, int k){
        /**
         *
         * # of operations need to keep the chars in the window all the same
         * right - left - maxCharCount [)
         * when to expand the window when # of different chars less than or equal to k
         * shrink: when # of different chars great than k
         * return: update the result after the shrink loop
         */

        int left, right;
        left = right = 0;
        int[] count = new int[26];
        int maxCharCount = 0;
        int res = -1;
        while(right < s.length()){
            int in = s.charAt(right) - 'A';
            //update map
            count[in]++;
            //update maxCharCount
            maxCharCount = Math.max(count[in], maxCharCount);
            right ++;
            while(right - left - maxCharCount > k){
                int out = s.charAt(left) - 'A';
                count[out]--;
                left++;
            }
            res = Math.max(right-left,res);
        }

        return res;
    }
}
