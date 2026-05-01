package org.Leetcode.Stack;

import java.util.Stack;

public class ReversePolishNotation150 {
    /**
     * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
     *
     * Evaluate the expression. Return an integer that represents the value of the expression.
     *
     * Note that:
     *
     * The valid operators are '+', '-', '*', and '/'.
     * Each operand may be an integer or another expression.
     * The division between two integers always truncates toward zero.
     * There will not be any division by zero.
     * The input represents a valid arithmetic expression in a reverse polish notation.
     * The answer and all the intermediate calculations can be represented in a 32-bit integer.
     *
     *
     * Example 1:
     *
     * Input: tokens = ["2","1","+","3","*"]
     * Output: 9
     * Explanation: ((2 + 1) * 3) = 9
     */

    public int evalRPN(String[] tokens){
        /**
         * when to add to stack
         * 1. number
         * 2. after calculation
         *
         * when to pop: encounter a operator
         *
         */
        Stack<String> stack = new Stack<>();
        for(String s: tokens){
            if("+-*/".contains(s)){
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                switch (s){
                    case "+":
                        stack.push( String.valueOf(a + b));
                        break;
                    case "-":
                        stack.push(String.valueOf(a - b));
                        break;
                    case "*":
                        stack.push(String.valueOf(a * b));
                        break;
                    case "/":
                        stack.push(String.valueOf(a / b));
                        break;
                }
            }

            else stack.push(s);
        }

        return Integer.valueOf(stack.pop());

}
}
