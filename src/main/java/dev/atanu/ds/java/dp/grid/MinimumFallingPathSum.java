package dev.atanu.ds.java.dp.grid;

/**
 * 931. Minimum Falling Path Sum
 * <p>
 * https://leetcode.com/problems/minimum-falling-path-sum/
 */
public class MinimumFallingPathSum {

    /**
     * Using Recursion
     *
     * @param matrix - matrix
     * @return min path
     */
    public int minFallingPathSumRecursion(int[][] matrix) {
        int n = matrix[0].length;
        int result = Integer.MAX_VALUE;

        for(int j = 0; j < n; j++) {
            result = Math.min(result, minFallingPathSumRecursion(matrix, 0, j));
        }
        return result;
    }

    private int minFallingPathSumRecursion(int[][] matrix, int i, int j) {
        int m = matrix.length;
        int n = matrix[0].length;
        if(i == m - 1) {
            return matrix[i][j];
        }

        int result = Integer.MAX_VALUE;
        result = Math.min(result, matrix[i][j] + minFallingPathSumRecursion(matrix, i + 1, j));

        if(j > 0) {
            result = Math.min(result, matrix[i][j] + minFallingPathSumRecursion(matrix, i + 1, j - 1));
        }

        if(j < n - 1) {
            result = Math.min(result, matrix[i][j] + minFallingPathSumRecursion(matrix, i + 1, j + 1));
        }
        return result;
    }


    /**
     * Using Memoization
     *
     * @param matrix - matrix
     * @return min path
     */
    public int minFallingPathSumMemoization(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int result = Integer.MAX_VALUE;
        Integer[][] dp = new Integer[m][n];

        for (int j = 0; j < n; j++) {
            result = Math.min(result, minFallingPathSumMemoization(matrix, 0, j, dp));
        }
        return result;
    }

    private int minFallingPathSumMemoization(int[][] matrix, int i, int j, Integer[][] dp) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (i == m - 1) {
            return matrix[i][j];
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        int result = Integer.MAX_VALUE;
        result = Math.min(result, matrix[i][j] + minFallingPathSumMemoization(matrix, i + 1, j, dp));

        if (j > 0) {
            result = Math.min(result, matrix[i][j] + minFallingPathSumMemoization(matrix, i + 1, j - 1, dp));
        }

        if (j < n - 1) {
            result = Math.min(result, matrix[i][j] + minFallingPathSumMemoization(matrix, i + 1, j + 1, dp));
        }
        return dp[i][j] = result;
    }


    /**
     * Using BottomUp
     *
     * @param matrix - matrix
     * @return min path sum
     */
    public int minFallingPathSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int result = Integer.MAX_VALUE;
        Integer[][] dp = new Integer[m][n];

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j>= 0; j--) {
                if(i == m-1) {
                    dp[i][j] = matrix[i][j];
                    continue;
                }

                dp[i][j] = matrix[i][j] + dp[i + 1][j];
                if(j > 0) {
                    dp[i][j] = Math.min(dp[i][j], matrix[i][j] + dp[i + 1][j - 1]);
                }
                if(j < n - 1) {
                    dp[i][j] = Math.min(dp[i][j], matrix[i][j] + dp[i + 1][j + 1]);
                }
            }
        }

        for(int j = 0; j < n; j++) {
            result = Math.min(result, dp[0][j]);
        }
        return result;
    }
}
