package dev.atanu.ds.java.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class BfsSolution {

    public static void main(String[] args) {

    }

    /**
     * https://leetcode.com/problems/rotting-oranges/description/
     *
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int countFresh = 0;

        Queue<int[]> queue = new LinkedList<>();

        // Put the position of all rotten oranges in queue
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    countFresh++;
                }
            }
        }

        // If count of fresh oranges is zero then return 0
        if (countFresh == 0) {
            return 0;
        }

        int count = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // BFS starting from initially rotten oranges
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                for (int[] dir : directions) {
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    // If x or y is out of bound or empty or the orange at (x , y) is already rotten, do nothing
                    if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 || grid[x][y] == 2) {
                        continue;
                    }
                    // Mark the orange at (x , y) as rotten
                    grid[x][y] = 2;

                    // Put the new rotten orange at (x , y) in queue
                    queue.offer(new int[]{x, y});

                    // Decrease the count of fresh oranges by 1
                    countFresh--;
                }
            }
        }
        return countFresh == 0 ? (count - 1) : -1;
    }
}
