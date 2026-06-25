package dev.atanu.ds.java.graph.dfs;

import java.util.Arrays;

public class GraphDfsSolution {

    public static void main(String[] args) {
        GraphDfsSolution solution = new GraphDfsSolution();

        int[][] image = {
                {0, 0, 0},
                {0, 0, 0}
        };
        System.out.println(Arrays.deepToString(solution.floodFill(image, 0, 0, 0)));

        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        System.out.println(solution.numIslands(grid));
    }


    /**
     * https://leetcode.com/problems/flood-fill/
     *
     * @param image - image
     * @param sr    - sr
     * @param sc    - sc
     * @param color - color
     * @return updated image
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        dfsFloodFill(image, sr, sc, image[sr][sc], color);
        return image;
    }

    public void dfsFloodFill(int[][] image, int sr, int sc, int originalColor, int color) {
        if(sr < 0 || sr >= image.length
                || sc < 0 || sc >= image[0].length
                || image[sr][sc] != originalColor
                || image[sr][sc] == color) {
            return;
        }

        image[sr][sc] = color;

        dfsFloodFill(image, sr + 1, sc, originalColor, color);
        dfsFloodFill(image, sr, sc + 1, originalColor, color);
        dfsFloodFill(image, sr - 1, sc, originalColor, color);
        dfsFloodFill(image, sr, sc - 1, originalColor, color);
    }


    /**
     * Check of BFS Solution as well. But DFS is the Best suitable.
     * https://leetcode.com/problems/number-of-islands/
     *
     * @param grid - grid
     * @return count
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfsNumIslands(grid, i, j);
                    count += 1;
                }

            }
        }
        return count;
    }

    private void dfsNumIslands(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length
                || j < 0 || j >= grid[0].length
                || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '2';

        dfsNumIslands(grid, i + 1, j);
        dfsNumIslands(grid, i, j + 1);
        dfsNumIslands(grid, i - 1, j);
        dfsNumIslands(grid, i, j - 1);
    }


    /**
     * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
     *
     * @param matrix - matrix
     * @return max increasing length
     */
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
