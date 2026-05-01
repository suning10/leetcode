package org.Leetcode.Design;


import java.util.*;

/**
 * Implement the RandomizedSet class:
 *
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present.
 * Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present.
 * Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements
 * (it's guaranteed that at least one element exists when this method is called).
 * Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * Output
 * [null, true, false, true, 2, true, false, 2]
 *
 * Explanation
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 * randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 * randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
 * randomizedSet.insert(2); // 2 was already in the set, so return false.
 * randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 */
public class InsertDeleteGetRandom380 {

    /**
     * why not use HashSet
     * Insert O(1), remove O(1). but get random needs O(n) as need to get all keys
     *
     * Use an arrayList + Hashmap (value, ind of array)
     * Insert O(1):
     * check if exists O(1) in hashmap
     * put into map O(1), addLast O(1)
     *
     * Remove:
     * get ind from map O(1)
     * swap ind with last element in arraylist O(1)
     * remove last element O(1) --- remove ind is O(n) because need to shift elements
     * remove n in map O(1)
     *
     *
     * Random
     * get(list.size.random) O(1)
     */

    HashMap<Integer,Integer> map;
    List<Integer> list;
    public InsertDeleteGetRandom380() {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if(!map.containsKey(val)){
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }
        return false;

    }

    public boolean remove(int val) {
        if(map.containsKey(val)) {
            int idx = map.get(val);
            int last = list.get(list.size() - 1);
            //swap
            list.set(idx, list.get(list.size() - 1));
            list.remove(list.size() - 1);
            map.remove(val);
            map.put(last,idx);
            return true;
        }
        return false;
    }

    public int getRandom() {
        Random random = new Random();
        return this.list.get(random.nextInt(this.list.size()));
    }
}
