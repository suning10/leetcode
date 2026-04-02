package org.Leetcode.Design;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedHashmap {

    int capacity;
    LinkedHashMap linkedHashMap;
    public LRUCacheLinkedHashmap(int capacity){

        this.capacity = capacity;
        this.linkedHashMap = new LinkedHashMap(5,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }
}

