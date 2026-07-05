package dev.atanu.ds.java.graph.dfs;

/**
 * https://leetcode.com/problems/number-of-islands/
 *
 */
public class NumberOfIslandsDFS {

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count += 1;
                }
            }
        }
        return count;
    }

    // Check other submissions for other approaches
    private void dfs(char[][] grid, int i, int j) {
        if (i >= grid.length || i < 0 || j >= grid[0].length
                || j < 0 || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
    }
}
