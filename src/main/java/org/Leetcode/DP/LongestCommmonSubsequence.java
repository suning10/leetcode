package org.Leetcode.DP;

public class LongestCommmonSubsequence {

    public int longestCommonSubsequence(String text1, String text2){
        /**
         * dp: lcs of text1[0,i-1] and text2[0,j-1]. in other words, first i element of text1 and first j element of text2
         * 0: no char in text 1 or text 2
         * how to handle base case if not using this
         * comparing empty text 2 with text 1 dp[i][0] always means first text2[0] which is the first char of text2
         * handle text2[j - 1] in the base case
         * dp[i][j] = dp[i-1][j-1] + 1: text[i-1] == text[j-1],
         *                      else:
         *                      Math.max(dp[i-1,j], dp[i,j-1])
         */

        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        //base case
        for(int i = 0; i <= n; i++){
            dp[0][i] = 0;
        }
        for(int i = 0; i <= m; i++){
            dp[i][0] = 0;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                //text1[i-1] == text2[j-1]
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        //get result, return dp[m][n]
        return dp[m][n];
    }
}
