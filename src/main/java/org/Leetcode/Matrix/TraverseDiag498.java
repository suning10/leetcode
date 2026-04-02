//package org.Leetcode.Matrix;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Given an m x n matrix mat,
// * return an array of all the elements of the array in a diagonal order.
// *  Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
// * Output: [1,2,4,7,5,3,6,8,9]
// */
//public class TraverseDiag498 {
//    /**
//     * left down: i--, j ++
//     * right up: i++, j--
//     * 1. reach row 0, move right, j++
//     * 2. reach col 0: move down, i++
//     * 3. reach row m - 1: move right
//     * 4. reach col n - 1: move down
//     */
//    public int[] findDiagonalOrder(int[][] mat) {
//        int m = mat.length;
//        int n = mat[0].length;
//        // (m -1,n -1) = m + n - 2
//        boolean leftDown = true;
//        int i,j;
//        i = 1;
//        int[] res = new int[m * n];
//        res[0] = mat[0][0];
//        for(int ind = 1; i < m * n; i++){
//            res[ind] = mat[i][j];
//            //
//        }
//    }
//
//
//    /**
//     * (i,j): i + j always the same on same diagonal
//     * odd: normal
//     * even: reverse
//     */
////    public int[] findDiagonalOrderSameSum(int[][] mat) {
////
////    }
//}
