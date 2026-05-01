package org.Leetcode.dfs;


import java.util.*;

/**
 * There is an integer array nums that consists of n unique elements, but you have forgotten it. However,
 * you do remember every pair of adjacent elements in nums.
 *
 * You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi]
 * indicates that the elements ui and vi are adjacent in nums.
 *
 * It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs,
 * either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
 *
 * Return the original array nums. If there are multiple solutions, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: adjacentPairs = [[2,1],[3,4],[3,2]]
 * Output: [1,2,3,4]
 * Explanation: This array has all its adjacent pairs in adjacentPairs.
 * Notice that adjacentPairs[i] may not be in left-to-right order.
 */
public class RestoreArray1743 {

    HashSet<Integer> visited;
    int[] res;
    public int[] restoreArrayDFS(int[][] adjacentPairs) {

        var graph = buildGraph(adjacentPairs);
        visited = new HashSet<>();
        //vertex with one neighbor must be start or end
        int start = -1;
        for(int i: graph.keySet()){
            if(graph.get(i).size() == 1){
                start = i;
                break;
            }
        }
        res = new int[graph.size()];
        dfs(graph,start,0);

        return res;

    }

    public int[] restoreArray(int[][] adjacentPairs) {

        var graph = buildGraph(adjacentPairs);
        //vertex with one neighbor must be start or end
        int start = -1;
        for(int i: graph.keySet()){
            if(graph.get(i).size() == 1){
                start = i;
                break;
            }
        }
        res = new int[graph.size()];
        res[0] = start;
        res[1] = graph.get(start).get(0); // only has one neighbor for start or end
        int prev = res[0];
        for(int i = 2; i < graph.size(); i++){
            //the one not equal to previous one is the next num
            //eg [1,2], [2,3], in graph, 2 -> [1,3]
            //prev = 1, take 3

            //get current neighbor
            List<Integer> curr = graph.get(res[i-1]);
            //find 1's neighbor
            for(int nei: curr){
                if(nei != prev) {
                    res[i] = nei; // find the one not equal to prev
                    break;
                }

            }
            prev = res[i-1];
        }

        return res;

    }

    void dfs(HashMap<Integer,List<Integer>> graph, int start,int idx){
        if(visited.contains(start)) return;
        if(graph.get(start).isEmpty()) return;
        res[idx] = start;
        visited.add(start);
        for(int nei: graph.get(start)){
            dfs(graph,nei,idx + 1);
        }

    }


    HashMap<Integer,List<Integer>> buildGraph(int[][] adjacentPairs){
        HashMap<Integer,List<Integer>> graph = new HashMap<>();
        for(int[] pair: adjacentPairs){
            if(!graph.containsKey(pair[0])) graph.put(pair[0], new ArrayList<>());
            if(!graph.containsKey(pair[1])) graph.put(pair[1], new ArrayList<>());
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }

        return graph;
    }

    class vertex{
        private int val;
        private List<vertex> neighbor;
        public vertex(int val){
            this.val = val;
            this.neighbor = new ArrayList<>();
        }
        public void setNeighbor(vertex neighbor) {
            this.neighbor.add(neighbor);
        }

        public List<vertex> getNeighbor() {
            return neighbor;
        }
        public int getVal() {
            return val;
        }
    }
}
