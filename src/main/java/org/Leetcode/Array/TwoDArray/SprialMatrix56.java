package org.Leetcode.Array.TwoDArray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SprialMatrix56 {

    /**
     *
     * direction: always right -> down -> left -> up
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int upperBound = 0;
        int loweBound = matrix.length - 1;
        int leftBound = 0;
        int rightBound = matrix[0].length - 1;

        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        while(res.size() < m * n){
            //right
            if(upperBound <= loweBound){
                for(int i = leftBound; i <= rightBound; i++){
                    res.add(matrix[upperBound][i]);
                }
                upperBound++;
                System.out.println("current lowerbound is " + loweBound);
                System.out.println("current upperbound is " + upperBound);
                System.out.println("move right done, current rightbound is " + rightBound);
                System.out.println("move right done, current leftbound is " + leftBound);

                System.out.println(res.stream().toList());
            }


            if(leftBound <= rightBound){
                //down
                for(int i = upperBound; i <= loweBound ; i++){
                    res.add(matrix[i][rightBound]);
                }
                rightBound--;
                System.out.println("current lowerbound is " + loweBound);
                System.out.println("current upperbound is " + upperBound);
                System.out.println("move down done, current rightbound is " + rightBound);
                System.out.println("move down done, current leftbound is " + leftBound);
                System.out.println(res.stream().toList());
            }

            //left
            if(upperBound <= loweBound){
                for(int i = rightBound; i >= leftBound ; i--){
                    res.add(matrix[loweBound][i]);
                }
                loweBound--;
                System.out.println("current lowerbound is " + loweBound);
                System.out.println("current upperbound is " + upperBound);
                System.out.println("move left done, current right bound is " + rightBound);
                System.out.println("move left done, current left bound is " + leftBound);
                System.out.println(res.stream().toList());
            }


            //up
            if(leftBound <= rightBound) {
                for(int i = loweBound; i >= upperBound ; i--){
                    res.add(matrix[i][leftBound]);
                }
                leftBound++;
                System.out.println("current lowerbound is " + loweBound);
                System.out.println("current upperbound is " + upperBound);
                System.out.println("move up done ,current left bound is " + leftBound);
                System.out.println("move up done ,current rightbound is " + rightBound);
                System.out.println(res.stream().toList());
            }
            System.out.println("current size is " + res.size());
        }

        return res;
    }

    public List<Integer> spiralOrder1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int upper_bound = 0, lower_bound = m - 1;
        int left_bound = 0, right_bound = n - 1;
        List<Integer> res = new LinkedList<>();
        // res.size() == m * n 则遍历完整个数组
        while (res.size() < m * n) {
            if (upper_bound <= lower_bound) {
                // 在顶部从左向右遍历
                for (int j = left_bound; j <= right_bound; j++) {
                    res.add(matrix[upper_bound][j]);
                }
                // 上边界下移
                upper_bound++;
            }

            if (left_bound <= right_bound) {
                // 在右侧从上向下遍历
                for (int i = upper_bound; i <= lower_bound; i++) {
                    res.add(matrix[i][right_bound]);
                }
                // 右边界左移
                right_bound--;
            }

            if (upper_bound <= lower_bound) {
                // 在底部从右向左遍历
                for (int j = right_bound; j >= left_bound; j--) {
                    res.add(matrix[lower_bound][j]);
                }
                // 下边界上移
                lower_bound--;
            }

            if (left_bound <= right_bound) {
                // 在左侧从下向上遍历
                for (int i = lower_bound; i >= upper_bound; i--) {
                    res.add(matrix[i][left_bound]);
                }
                // 左边界右移
                left_bound++;
            }

            System.out.println("current size is " + res.size());
        }
        return res;
    }
}
