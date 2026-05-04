package org.Leetcode.ancestor;

import java.util.*;

public class findCommonAncestor {

    Map<Integer, List<Integer>> buildGraph(int[][] edges){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge: edges){
            int parent = edge[0];
            int child = edge[1];
            if(!graph.containsKey(child)){
                graph.put(child, new ArrayList<>());
            }
            graph.get(child).add(parent);
        }
        return graph;
    }

    Set<Integer> findAncestor(Map<Integer, List<Integer>> graph, int child){
        HashSet<Integer> visited = new HashSet<>();
        dfs(graph,child,visited);
        return visited;
    }

    private void dfs(Map<Integer, List<Integer>> graph, int child, HashSet<Integer> visited) {
        for(int parent: graph.get(child)){
            if(!visited.contains(parent)){
                visited.add(parent);
                dfs(graph,parent,visited);
            }
        }
    }


}
