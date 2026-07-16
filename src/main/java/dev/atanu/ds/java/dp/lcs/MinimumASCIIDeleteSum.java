package dev.atanu.ds.java.dp.lcs;

/**
 * 712. Minimum ASCII Delete Sum for Two Strings
 *
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 *
 */
public class MinimumASCIIDeleteSum {

    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        int total = 0;
        for (char ch : s1.toCharArray()) {
            total += ch;
        }
        for (char ch : s2.toCharArray()) {
            total += ch;
        }

        return total - 2 * dp[n][m];
    }

}
