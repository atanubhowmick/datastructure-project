package dev.atanu.ds.java.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class GraphBfsSolution {

    public static void main(String[] args) {
        GraphBfsSolution solution = new GraphBfsSolution();
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        System.out.println(solution.numIslands(grid));
    }

    /**
     * Check of BFS Solution as well. DFS is the Best suitable.
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
                    count++;
                    bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        grid[row][col] = '2';
        queue.offer(new int[]{row, col});
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] dir : directions) {
                int newRow = cell[0] + dir[0];
                int newCol = cell[1] + dir[1];
                if (newRow >= 0
                        && newRow < grid.length
                        && newCol >= 0
                        && newCol < grid[0].length
                        && grid[newRow][newCol] == '1') {
                    grid[newRow][newCol] = '2';
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }
}
