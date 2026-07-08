package dev.atanu.ds.java.dp.leetcode;

/**
 * https://leetcode.com/problems/ones-and-zeroes/description/
 *
 * @author Atanu Bhwomick
 */
public class OnesAndZeros {

    /**
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxFormRecursion(String[] strs, int m, int n) {
        return findMaxFormRecursion(strs, m, n, strs.length);
    }

    private int findMaxFormRecursion(String[] strs, int m, int n, int index) {
        if(m <= 0 && n <= 0) {
            return 0;
        }

        if(index <= 0) {
            return 0;
        }

        String s = strs[index - 1];
        int zeros = 0, ones = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '1') {
                ones++;
            } else {
                zeros++;
            }
        }

        if(zeros > m || ones > n) {
            return findMaxFormRecursion(strs, m, n, index - 1);
        } else {
            return Math.max(1 + findMaxFormRecursion(strs, m - zeros, n - ones, index - 1),
                    findMaxFormRecursion(strs, m, n, index - 1));
        }
    }

    /**
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxFormMemoization(String[] strs, int m, int n) {
        int[][][] dp = new int[m + 1][n + 1][strs.length + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for(int k = 0; k <= strs.length; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return findMaxFormMemoization(dp, strs, m, n, strs.length);
    }

    private int findMaxFormMemoization(int[][][] dp, String[] strs, int m, int n, int index) {
        if(m <= 0 && n <= 0) {
            return 0;
        }

        if(index <= 0) {
            return 0;
        }

        if(dp[m][n][index] != -1) {
            return dp[m][n][index];
        }

        String s = strs[index - 1];
        int zeros = 0, ones = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '1') {
                ones++;
            } else {
                zeros++;
            }
        }

        if(zeros > m || ones > n) {
            dp[m][n][index] = findMaxFormMemoization(dp, strs, m, n, index - 1);
        } else {
            dp[m][n][index] = Math.max(1 + findMaxFormMemoization(dp, strs, m - zeros, n - ones, index - 1),
                    findMaxFormMemoization(dp, strs, m, n, index - 1));
        }

        return dp[m][n][index];
    }

}
