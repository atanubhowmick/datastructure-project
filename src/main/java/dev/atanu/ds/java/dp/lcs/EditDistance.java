package dev.atanu.ds.java.dp.lcs;

/**
 * 72. Edit Distance
 * https://leetcode.com/problems/edit-distance/
 *
 */
public class EditDistance {

    /**
     * Using Recursion
     *
     * @param word1 - word1
     * @param word2 - word2
     * @return min distance
     */
    public int minDistanceRecursion(String word1, String word2) {
        return minDistanceRecursion(word1, word2, word1.length(), word2.length());
    }

    int minDistanceRecursion(String word1, String word2, int m, int n) {
        if (m == 0) {
            return n;
        }
        if (n == 0) {
            return m;
        }

        if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
            return minDistanceRecursion(word1, word2, m - 1, n - 1);
        } else {
            int insertOperation = minDistanceRecursion(word1, word2, m, n - 1);
            int deleteOperation = minDistanceRecursion(word1, word2, m - 1, n);
            int replaceOperation = minDistanceRecursion(word1, word2, m - 1, n - 1);
            return 1 + Math.min(insertOperation, Math.min(deleteOperation, replaceOperation));
        }
    }


    /**
     * Using Memoization
     *
     * @param word1 - word1
     * @param word2 - word2
     * @return min distance
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        Integer[][] dp = new Integer[m + 1][n + 1];
        return minDistanceRecursionMemo(word1, word2, word1.length(), word2.length(), dp);
    }

    private int minDistanceRecursionMemo(String word1, String word2, int m, int n, Integer[][] dp) {
        if (m == 0) {
            return n;
        }
        if (n == 0) {
            return m;
        }
        if (dp[m][n] != null) {
            return dp[m][n];
        }

        int minEditDistance = 0;

        if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
            minEditDistance = minDistanceRecursionMemo(word1, word2, m - 1, n - 1, dp);
        } else {
            int insertOperation = minDistanceRecursionMemo(word1, word2, m, n - 1, dp);
            int deleteOperation = minDistanceRecursionMemo(word1, word2, m - 1, n, dp);
            int replaceOperation = minDistanceRecursionMemo(word1, word2, m - 1, n - 1, dp);
            minEditDistance = 1 + Math.min(insertOperation, Math.min(deleteOperation, replaceOperation));
        }
        dp[m][n] = minEditDistance;
        return dp[m][n];
    }


    /**
     * Using BottomUp
     *
     * @param word1 - word1
     * @param word2 - word2
     * @return min distance
     */
    public int minDistanceBottomUp(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if (m == 0) {
            return n;
        }
        if (n == 0) {
            return m;
        }

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word2.charAt(j - 1) == word1.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }

        return dp[m][n];
    }
}
