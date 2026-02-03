package org.Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DecodeString394 {

    public String decodeString(String s) {
        /**
         using stack

         two situtaions
         k[string]
         k[string k[string]] -> nested

         need to LIFO -> using stack

         different type of chars
         [ , ], char, 1 - 9

         when push the char into stack
         if not closing bracket ]
         when pop out the char
         when find a ], pop the char until seeing a [0-9]
         when to output the result
         push k * [] again into stack
         until reach the end of the string.
         pop the whole stack out with reverse order
         */

        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray()){

            if(c !=  ']'){
                stack.push(c);
            }

            else{
                //c == ]
                //start construct decoding string
                List<Character> decodedString = new ArrayList<>();
                //loop till stack.peek is [0-9]
                while(!stack.isEmpty() && stack.peek()!='['){
                    decodedString.add(stack.pop());
                }
                //k could more than 9
                //need another loop to get acutal k
                stack.pop(); // pop out open bracket [
                int k = 0;
                int base = 1;
                while(!stack.isEmpty() && Character.isDigit(c)){
                    k = base * (stack.pop() - '0') + k;
                    base *=10;
                }

                //loop k times and push that to stack, push in reverse order
                for(int j = k; j > 0; j--){
                    for(int m = decodedString.size() -1; m >=0; m--){
                        stack.push(decodedString.get(m));
                    }
                }
            }
        }
        //output the result in reverse order
        char[] result = new char[stack.size()];
        for(int i = result.length - 1; i >=0; i--){
            result[i] = stack.pop();
        }
        return new String(result);

    }
}
