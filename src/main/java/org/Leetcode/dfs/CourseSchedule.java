package org.Leetcode.dfs;


import java.util.ArrayList;
import java.util.List;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi]
 * indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 */
public class CourseSchedule {


    public boolean canFinish(int numCourses, int[][] prerequisites){
        return true;
    }

    void dfs(List<Integer>[] graph, boolean[] hasCycle, int start){

    }

    List<Integer>[] buildGraph(int[][] pre, int numberOfCourse){
        List<Integer>[] graph = new ArrayList[numberOfCourse];
        //ini a new array list
        for(int i = 0; i < numberOfCourse; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < pre.length; i++){
            int course = pre[i][0];
            int preReq = pre[i][1];
            graph[preReq].add(course); // a->b a first then b
        }

        return graph;
    }
}
