package dev.atanu.ds.java.dp.grid;

import java.util.PriorityQueue;

/**
 * 1289. Minimum Falling Path Sum II
 * <p>
 * https://leetcode.com/problems/minimum-falling-path-sum-ii/
 */
public class MinimumFallingPathSumII {

    public int minFallingPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 1; i < m; i++) {
            // Insert into Priority Queue to find min elements
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            for (int j = 0; j < n; j++) {
                priorityQueue.offer(grid[i - 1][j]);
            }

            // Get first two smallest elements
            int firstSmallest = priorityQueue.poll();
            int secondSmallest = priorityQueue.poll();

            // Create the DP matrix
            for (int j = 0; j < n; j++) {
                if (grid[i - 1][j] == firstSmallest) {
                    grid[i][j] = grid[i][j] + secondSmallest;
                } else {
                    grid[i][j] = grid[i][j] + firstSmallest;
                }
            }
        }

        // Check the last row for minimum falling sum
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            result = Math.min(result, grid[m - 1][j]);
        }

        return result;
    }

}
