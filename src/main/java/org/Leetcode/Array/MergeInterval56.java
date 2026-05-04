package org.Leetcode.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *

 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 */
public class MergeInterval56 {
    /**
     * 1. Sort Array by start, if same, sort by end desc (Need it when find overlap range)
     * [a,b], [c,d]
     * a <= c and b >= d. if not sort by end desc, [1,2], [1,4] will not categorize as overlap (1288)
     *
     * 2. int[] is reference (object)
     * int is primitive (value)
     *
     * get last range in res -> compare start and end with current interval
     * if start <= last[1] && end >= last[1]  Need to Merge
     * if start > last[1] start a new interval
     * skip if end  < last[1]
     */

    public int[][] merge(int[][] intervals){

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        //Arrays.sort(intervals, (a,b) -> a[0]-b[0]);
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for(int[] interval: intervals){
            int[] last = res.get(res.size() - 1);
            if(interval[0] > last[1]) res.add(interval); // current start > last end
            else{
                last[1] = Math.max(last[1],interval[1]); // since start is sorted, find the bigger end
            }
        }

        return res.toArray(new int[0][0]);
    }
}
