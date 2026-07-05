package dev.atanu.ds.java.graph.dfs;

/**
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
public class LargestIncreasingPath {

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Integer[][] dp = new Integer[m][n];
        int maxLen = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int len = dfsIncreasingPath(matrix, i, j, dp);
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }

    private int dfsIncreasingPath(int[][] matrix, int i, int j, Integer[][] dp) {
        if(dp[i][j] != null) {
            return dp[i][j];
        }

        int maxLen = 1;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for(int[] dir: dirs) {
            int x = i + dir[0], y = j + dir[1];
            if(x < 0 || x >= matrix.length
                    || y < 0 || y >= matrix[0].length
                    || matrix[i][j] >= matrix[x][y]) {
                continue;
            }
            int len = 1 + dfsIncreasingPath(matrix, x, y, dp);
            maxLen = Math.max(maxLen, len);
        }

        dp[i][j] = maxLen;
        return dp[i][j];
    }
}
