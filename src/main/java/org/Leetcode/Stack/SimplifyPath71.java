package org.Leetcode.Stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * You are given an absolute path for a Unix-style file system, which always begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.
 *
 * The rules of a Unix-style file system are as follows:
 *
 * A single period '.' represents the current directory.
 * A double period '..' represents the previous/parent directory.
 * Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
 * Any sequence of periods that does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are valid directory or file names.
 * The simplified canonical path should follow these rules:
 *
 * The path must start with a single slash '/'.
 * Directories within the path must be separated by exactly one slash '/'.
 * The path must not end with a slash '/', unless it is the root directory.
 * The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
 * Return the simplified canonical path.
 */
public class SimplifyPath71 {

    public String simplifyPath1(String path){
        /**
         * ../. => use stack to track
         * /home/user/doc/../pictures
         * once see a .. pop the top one
         * if stack.peek = '/' and current char is /, continue

         */
        Stack<String> stack = new Stack<>();
        String[] paths = path.split("/");
        for(String s: paths){
            if(s.isEmpty() || s.equals(".")) continue;
            if(s.equals("..")){
                if(!stack.isEmpty()) stack.pop();
                continue;
            }
            stack.push(s);
        }

        StringBuilder sb = new StringBuilder();
        for (String dir: stack){
            sb.append("/").append(dir);
        }
        return sb.isEmpty() ? "/" : sb.toString();
    }
}
