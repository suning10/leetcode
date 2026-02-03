package org.Leetcode.Stack;

import java.util.Stack;

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        // use mono stack

        Stack<Integer> stack = new Stack<>();
        int n = temperatures.length;
        int[] res = new int[n];

        for(int i = n-1; i >=0; i--){
            int cnt = 1;
            while(!stack.isEmpty() &&   stack.peek() <= temperatures[i]){
                stack.pop();
                cnt++;
            }
            res[i] = stack.isEmpty()?0:cnt;
            stack.push(temperatures[i]);
        }

        return res;
    }
}
