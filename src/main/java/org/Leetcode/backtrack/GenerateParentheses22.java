package org.Leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses22 {
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n){
        /**
         * all parentheses, two rules
         * # of ( == # of )
         * at any point, # of ( >= # of )
         * use left and right to track # of parentheses
         */

        //use stringbuilder as track
        StringBuilder sb = new StringBuilder();
        backtrack(n,sb,0,0);
        return res;
    }

    void backtrack(int n, StringBuilder sb, int left, int right){

        //base case
        if(left == n && right == n){
            res.add(sb.toString());
            return;
        }
        //right has more than left
        if(right > left) return;
        //left and right out of bound
        if(left > n || right > n) return;


        sb.append("(");
        backtrack(n,sb,left+1,right);
        sb.deleteCharAt(sb.length()-1);

        sb.append(")");
        backtrack(n,sb,left,right+1);
        sb.deleteCharAt(sb.length() - 1);

        }
    }

