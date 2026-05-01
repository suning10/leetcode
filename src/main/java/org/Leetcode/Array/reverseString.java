package org.Leetcode.Array;

public class reverseString {

    public String reverse(String s){
        if(s.length()<2) return s;
        int n = s.length();
        if(s.charAt(0) != s.charAt(n-1)) return s;
        char[] cur = s.toCharArray();
        int left = 1;
        int right = n-2;
        while(left < right){
            char temp = cur[left];
            cur[left] = cur[right];
            cur[right] = temp;
        }

        return new String(cur);
    }
}
