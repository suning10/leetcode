package org.Leetcode.DP;

public class EditDistance72 {

    public int minDistance(String word1, String word2){

        /**
         * dp[i][j]: # of changes need to make word1[0,i] and word2[0,j] the same
         * dp[i][j] = dp[i-1][j-1] if word1[i] == word2[j]
         *          = min(dp[i-1][j] + 1, dp[i][j-1] + 1, dp[i-1][j-1] + 1)
         *          word1[0,i-1] and word2[0,j] same, delete word1[i]
         *          word1[0,i] and word2[0,j-1] same, add word2[j]  to word1[i]
         *          otherwise replace
         */
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1]; // dp[0][j] or dp[i][0]: empty string do have meaning

        for(int i = 1; i <= m; i++) dp[i][0] = i;
        for(int i = 1; i <= n; i++) dp[0][i] = i;

        for(int i = 1; i<=m; i++){
            for(int j = 1; j <=n; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else{
                    int tempMin = Math.min(dp[i-1][j], dp[i][j-1]);
                    dp[i][j] = Math.min(dp[i-1][j-1],tempMin) + 1;
                }
            }
        }

        return dp[m][n];
    }
}
