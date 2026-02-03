package org.Leetcode.dfs;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * All Paths From Source to Target
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1,
 * find all possible paths from node 0 to node n - 1 and return them in any order.
 *
 * The graph is given as follows: graph[i] is a list of all nodes you can
 * visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 */
public class AllRouteFromSrcToDesc797 {

    /**
     *DAG, directed, acylic(no circle)
     * onPath(to avoid duplicate path)
     * visited:(to avoid traverse same node twice)
     * here, no circle (DAG) no need to use visited and onPath
     */

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph){
        LinkedList<Integer> path = new LinkedList<>();
        dfs(graph,0,path);
        return res;
    }

    //i as inx of the node
    //graph.length is the number of vertex the graph has
    //graph[0]=[1,2,3], graph[1]=[2,4,5,6]
    private void dfs(int[][] graph, int i, LinkedList<Integer> path){
        //base case, i reach end of vertex

        if(i == graph.length - 1) {
            res.add(new LinkedList<>(path));
            return;
        }

        //dfs framework
        for(int v: graph[i]){
            path.addLast(v);
            dfs(graph,v,path);
            path.removeLast();
        }

    }
}
