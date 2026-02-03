package org.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutation43 {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums){
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(visited,path,nums);
        return res;
    }

    void dfs(boolean[] visited, LinkedList<Integer> path, int[] nums){
        //path == nums.length
        if(path.size() == nums.length){

            res.add(new LinkedList<>(path));
            return;
        }

        //prone branches


        for(int i = 0; i < nums.length; i++){
        //prone branches
            if(visited[i]) continue;
            //make choice
            path.addLast(nums[i]);
            visited[i] = true;
            dfs(visited,path,nums);
            //void choice
            path.removeLast();
            visited[i] = false;
        }
    }

}
