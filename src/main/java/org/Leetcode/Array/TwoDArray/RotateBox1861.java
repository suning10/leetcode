package org.Leetcode.Array.TwoDArray;

public class RotateBox1861 {

    public char[][] rotateTheBox(char[][] boxGrid){


        var res = transpose(boxGrid);
        reverse(res);

    }

    public char[][] transpose(char[][] boxGrid){
        int col, row;
        row = boxGrid.length;
        col = boxGrid[0].length;
        char[][] res = new char[col][row];
        for(int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){
                System.out.println(boxGrid[j][i]);
                res[i][j] = boxGrid[j][i];
            }
        }

        return res;
    }

    public void reverse(char[][] boxGrid){
        int r = boxGrid.length;
        int c = boxGrid[0].length;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c / 2; j++){
                char temp = boxGrid[i][j];
                boxGrid[i][j] = boxGrid[i][c - j - 1];
                boxGrid[i][c - j - 1] = temp;
            }
        }
    }
}
