package dev.atanu.ds.java.dp.leetcode;

import java.util.List;

/**
 * https://leetcode.com/problems/triangle/description/
 *
 * @author Atanu Bhowmick
 */
public class TriangleMinimumTotal {

    public static void main(String[] args) {
        TriangleMinimumTotal solution = new TriangleMinimumTotal();
        List<List<Integer>> input = List.of(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );
        System.out.println(solution.minimumTotalRecursion(input));
        System.out.println(solution.minimumTotalMemoization(input));
        System.out.println(solution.minimumTotalBottomUp(input));
    }


    /**
     * Using Recursion
     *
     * @param triangle
     * @return
     */
    public int minimumTotalRecursion(List<List<Integer>> triangle) {
        return minimumTotalRecursion(triangle, 0, 0);
    }

    private int minimumTotalRecursion(List<List<Integer>> triangle, int row, int col) {
        if (row >= triangle.size()) {
            return 0;
        }

        return triangle.get(row).get(col) + Math.min(minimumTotalRecursion(triangle, row + 1, col),
                minimumTotalRecursion(triangle, row + 1, col + 1));
    }

    /**
     * Using Memoization
     *
     * @param triangle
     * @return
     */
    public int minimumTotalMemoization(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return minimumTotalMemoization(dp, triangle, 0, 0);
    }

    private int minimumTotalMemoization(int[][] dp, List<List<Integer>> triangle, int row, int col) {
        if(row >= triangle.size()) {
            return 0;
        }

        if(dp[row][col] != -1) {
            return dp[row][col];
        }

        return dp[row][col] = triangle.get(row).get(col) + Math.min(minimumTotalMemoization(dp, triangle, row + 1, col),
                minimumTotalMemoization(dp, triangle, row + 1, col + 1));
    }

    /**
     * With BottomUp approach
     * @param triangle
     * @return
     */
    public int minimumTotalBottomUp(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        int[][] dp = new int[m][n];

        for(int i = m - 1; i >= 0; i--) {
            for(int j = triangle.get(i).size() - 1; j >= 0; j--) {
                if(i == m - 1) {
                    dp[i][j] = triangle.get(i).get(j);
                } else {
                    dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
                }
            }
        }

        return dp[0][0];
    }

}
