package org.Leetcode.Array.TwoDArray;


/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 */
public class rotateMatrix48 {


    /**
     * Solution 1: note cells are move in group
     * Solution 2: Transpose first and then reverse
     */
    public void rotate(int[][] matrix){

        //groupMove(matrix);
        transpose(matrix);
        reverse(matrix);
    }

    void groupMove(int[][] matrix){
        /**
         * (0,0), (0,2), (2,2), (2,0) are move in group
         * (0,1), (1,2), (2,1),(1,0) are move in group
         * 01,02,03,04,05
         * 06,07,08,09,10
         * 11,12,13,14,15
         * 16,17,18,19,20
         * 21,22,23,24,25
         * m * n matrix
         * for(i = 0 to (m + 1) / 2 - 1) // if loop in full it will rotate twice
         *  for (j = 0 to (n + 1) / 2 - 1)
         *      temp = matrix[i][j]
         *      swap the value inside the group
         *
         */

        int n = matrix.length;
        //in case odd, need to loop 5 / 2 = 2, need loop 3 times
        for(int i = 0; i < (n + 1) / 2; i++){
            for(int j = 0; j < (n) / 2; j++){ // already rotate 3 times, only need twice
                int temp = matrix[i][j];
                //16 -> 2
                matrix[i][j] = matrix[n-j-1][i];
                //24->16
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                //10->24
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                //2->10
                matrix[j][n-i-1] = temp;
            }
        }

    }

    void transpose(int[][]matrix){
        int n = matrix.length;
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){ //
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    void reverse(int[][] matrix){
        int n = matrix.length;
        for(int i = 0; i < n; i++){ // need to reverse every row
            for(int j = 0; j < n / 2; j++){ // the middle one does not need to be reversed if odd
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }



}
