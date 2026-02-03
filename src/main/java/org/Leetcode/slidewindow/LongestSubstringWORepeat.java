package org.Leetcode.slidewindow;


import java.util.HashMap;

/**Given a string s, find the length of the longest substring without duplicate characters.
 *
 */
public class LongestSubstringWORepeat {


    /**
     * Use Slide window
     * Q1: When to increase the window: when no repetitive chars in the window
     * Q2: when to decrease the window: when one char appear more than once
     * Q3 when to return the result: when right reach the end
     */
    public int lengthOfLongestSubstring(String s){
        int left;
        int right;
        left = right = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        int res = 0;
        //[), no char ini
        while(right < s.length()){
            char c = s.charAt(right);
            map.put(c,map.getOrDefault(c,0) + 1) ; // increase

            right++;

            while(map.get(c) > 1){
                char o = s.charAt(left);
                map.put(o,map.get(c) - 1);
                left++;
            }
            //update result,
            //must be a legit result at this point
            res = Math.max( res,right - left);
        }

        return res;
    }
}
