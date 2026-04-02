package org.Leetcode.PQ;

import java.util.*;

public class HighFive1086 {

    /**
     *
     Given a list of the scores of different students, items,
     where items[i] = [IDi, scorei] represents one score from a student with IDi,
     calculate each student's top five average.

     Return the answer as an array of pairs result,
     where result[j] = [IDj, topFiveAveragej] represents the student with IDj and their top five average.
     Sort result by IDj in increasing order.

     A student's top five average is calculated by taking the sum of their top five scores
     and dividing it by 5 using integer division.
     */


    public int[][] highFive(int[][] items) {

        /**
         * by sorting
         * sort by id and then by score(desc)
         * calculate average after reach 5
         * skip after reach 5 && id = curId
         * write answer to an arraylist
         */

        /**
         * when see question ask for top n, use PQ!!! (max heap/min heap)
         * use a treemap if need the hashmap to be ordered
         */

        TreeMap<Integer, Queue> records = new TreeMap<>();
        for(int i = 0 ; i < items.length; i++){

            int id = items[i][0];
            //check if exists the key, if no create a new pq
            if(!records.containsKey(items[i][0])) {
                //records.put(items[i][0], new PriorityQueue<Integer>((a,b) -> b-a));
                records.put(items[i][0], new PriorityQueue<Integer>(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2 - o1;
                    }
                }));

            }
            //o(logN) * N = o(NlogN)
            records.get(id).add(items[i][1]);
        }

        //retrieve the data and calculate average
        List<int[]> solution = new ArrayList<>();
        for(int id: records.keySet()){
            int i = 0;
            int curSum = 0;
            while(i < 5){
                curSum += (int) records.get(id).poll();
                i++;
            }
            solution.add(new int[]{id,curSum / 5});
        }

        int[][] res = new int[solution.size()][2];
        for(int i = 0; i < solution.size(); i++){
            res[i][0] = solution.get(i)[0];
            res[i][1] = solution.get(i)[1];
        }

        return res;
    }

    public int[][] highFiveSort(int[][] items){

        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o2[1] - o1[0]; //higher score first desc
                else return o1[0] - o2[0]; // lower id first
            }
        };
        //custom sorting
        Arrays.sort(items,comparator);
        int i = 0;
        List<int[]> solution = new ArrayList<>();
        while(i < items.length){

            int id = items[i][0];
            int sum = 0;
            for(int cnt = 0; cnt < 5; cnt++){
                sum += items[i][1];
                i++;
            }
            solution.add(new int[]{id,sum/5});
            while(i < items.length && id == items[i][0]) i++; //skip same id
        }

        int[][] solutionArray = new int[solution.size()][];
        return solution.toArray(solutionArray);

    }
}
