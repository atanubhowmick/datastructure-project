package dev.atanu.ds.java.dp.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
    }

    public int lengthOfLISRecurrsion(int[] nums) {
        return lengthOfLISRecurrsion(nums, 0, Integer.MIN_VALUE);
    }

    private int lengthOfLISRecurrsion(int[] nums, int index, int prev) {
        if (index >= nums.length) {
            return 0;
        }

        int withCurrentElement = 0, withoutCurrentElement = 0;

        withoutCurrentElement = lengthOfLISRecurrsion(nums, index + 1, prev);
        if (nums[index] > prev) {
            withCurrentElement = 1 + lengthOfLISRecurrsion(nums, index + 1, nums[index]);
        }
        return Math.max(withCurrentElement, withoutCurrentElement);
    }

    /**
     *
     * @param nums
     * @return
     */
    public int lengthOfLISMemoization(int[] nums) {
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, -1);
        return lengthOfLISMemoization(nums, 0, -1, dp);
    }

    private int lengthOfLISMemoization(int[] nums, int index, int prevIndex, int[] dp) {
        if (index >= nums.length) {
            return 0;
        }

        if(dp[prevIndex + 1] != -1) {
            return dp[prevIndex + 1];
        }

        int withCurrentElement = 0, withoutCurrentElement = 0;

        withoutCurrentElement = lengthOfLISMemoization(nums, index + 1, prevIndex, dp);
        if (prevIndex == -1 || nums[index] > nums[prevIndex]) {
            withCurrentElement = 1 + lengthOfLISMemoization(nums, index + 1, index, dp);
        }
        dp[prevIndex + 1] = Math.max(withCurrentElement, withoutCurrentElement);

        return dp[prevIndex + 1];
    }
}
