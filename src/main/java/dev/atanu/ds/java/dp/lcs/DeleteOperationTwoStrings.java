package dev.atanu.ds.java.dp.lcs;

/**
 * 583. Delete Operation for Two Strings
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 *
 */
public class DeleteOperationTwoStrings {

    public int minDistance(String word1, String word2) {
        int lcsLen = lcs(word1, word2);
        return (word1.length() - lcsLen) + (word2.length() - lcsLen);
    }

    private int lcs(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i -1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i -1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
