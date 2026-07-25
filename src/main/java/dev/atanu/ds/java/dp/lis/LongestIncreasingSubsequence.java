package dev.atanu.ds.java.dp.lis;

import java.util.Arrays;

/**
 * 300. Longest Increasing Subsequence
 *
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 */
public class LongestIncreasingSubsequence {

    /**
     * Using recursion
     *
     * @param nums - nums
     * @return lis
     */
    public int lengthOfLISRecursion(int[] nums) {
        return lisRecursion(nums, 0, Integer.MIN_VALUE);
    }

    private int lisRecursion(int[] nums, int i, int prev) {
        if(i >= nums.length) {
            return 0;
        }

        int maxLen = 0;
        if(nums[i] > prev) {
            maxLen =  1 + lisRecursion(nums, i + 1, nums[i]);
        }
        maxLen = Math.max(maxLen, lisRecursion(nums, i + 1, prev));

        return maxLen;
    }


    /**
     *
     * @param nums - nums
     * @return lis
     */
    public int lengthOfLISMemoization(int[] nums) {
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, -1);
        return lisMemoization(nums, 0, -1, dp);
    }

    private int lisMemoization(int[] nums, int i, int prevIdx, int[] dp) {
        if(i >= nums.length) {
            return 0;
        }

        if(dp[prevIdx + 1] != -1) {
            return dp[prevIdx + 1];
        }

        int maxLen = 0;
        if(prevIdx == -1 || nums[i] > nums[prevIdx]) {
            maxLen =  1 + lisMemoization(nums, i + 1, i, dp);
        }
        maxLen = Math.max(maxLen, lisMemoization(nums, i + 1, prevIdx, dp));
        return dp[prevIdx  + 1] = maxLen;
    }


    /**
     * TopDown approach
     *
     * @param nums - nums
     * @return lis
     */
    public int lengthOfLISBottomUp(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int prevIdx = i - 1; prevIdx >= -1; prevIdx--) {
                int take = 0;
                if (prevIdx == -1 || nums[i] > nums[prevIdx]) {
                    take = 1 + dp[i + 1][i + 1];
                }
                int skip = dp[i + 1][prevIdx + 1];
                dp[i][prevIdx + 1] = Math.max(take, skip);
            }
        }
        return dp[0][0];
    }
}
