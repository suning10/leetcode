package org.Leetcode.slidewindow;

import java.util.HashMap;

public class MinimumWindowSubstring76 {

    /**
     * Given two strings s and t of lengths m and n respectively,
     * return the minimum window substring of s such that every character in t (including duplicates)
     * is included in the window. If there is no such substring, return the empty string "".
     *
     * The testcases will be generated such that the answer is unique.
     *
     * Example 1:
     *
     * Input: s = "ADOBECODEBANC", t = "ABC"
     * Output: "BANC"
     * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
     */

    public String minWindow(String s, String t){

        /**
         * when to increase the window, window have all the char of t
         * when to decrease the window, shrinked window still have all char in t
         * update once shrink complete
         */

        HashMap<Character,Integer> need = new HashMap<>();
        HashMap<Character,Integer> window = new HashMap<>();
        int left, right;
        left = right = 0;
        int curMin = Integer.MAX_VALUE;
        int valid = 0; // track number of chars already meet requirement
        int start = 0; // track start char of a valid solution

        for(char c: t.toCharArray()){
            need.put(c,need.getOrDefault(c,0) + 1);
        }

        while(right < s.length()){
            char in = s.charAt(right);
            //update window
            window.put(in, window.getOrDefault(in,0) + 1 );
            if(need.get(in).equals(window.get(in))) valid++;
            right++;

            while(valid == t.length()){
                //now it is a valid solution
                //update start
                if(right - left < curMin){
                    start = left;
                    curMin = right - left;
                }
                char out = s.charAt(left);
                //now decrease
                window.put(out,window.get(out) - 1);
                if(!window.containsKey(out)) continue;
                if(window.get(out) < need.get(out)) valid--;
            }
        }
        //no match found
        return curMin == Integer.MAX_VALUE ? "" : s.substring(start, start + curMin);



    }
}
