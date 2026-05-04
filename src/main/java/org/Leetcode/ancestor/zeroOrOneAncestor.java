package org.Leetcode.ancestor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class zeroOrOneAncestor {

    public List<Integer> findzeroOrOneAncestor(int[][] edges){
        /**
         * use hashmap to track in degree
         */
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int[] edge: edges){
            int parent = edge[0];
            int child = edge[1];
            map.put(child,map.getOrDefault(child, 0) + 1);
            map.putIfAbsent(parent, 0); // important, could miss indegree == 0
        }
        List<Integer> res = new ArrayList<>();
        //loop the map and find inDegree == 0 or 1
        for(int i: map.keySet()){
            if(map.get(i) < 2) res.add(i);
        }

        return res;
    }
}
