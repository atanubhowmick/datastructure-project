package dev.atanu.ds.java.dp.leetcode;

/**
 * https://leetcode.com/problems/last-stone-weight-ii/description/
 *
 * @author Atanu Bhowmick
 */
public class StoneWeight {

    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        return minDiffSubsetSum(stones, sum, stones.length);
    }

    private int minDiffSubsetSum(int[] nums, int sum, int n) {
        int target = sum / 2;
        boolean[][] dp = new boolean[n + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = false;
                } else if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int diff = sum;
        for (int j = target; j >= 0; j--) {
            if (dp[n][j]) {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }
}
