package dev.atanu.ds.java.dp.houserobber;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/house-robber/description/
 *
 * @author Atanu Bhowmick
 */
public class HouseRobber {

    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();
        int[] nums = new int[] {2, 7, 9, 3, 1};
        System.out.println(houseRobber.robRecursion(nums));
        System.out.println(houseRobber.robMemoization(nums));
        System.out.println(houseRobber.rob(nums));
    }

    /**
     * Using recursion
     *
     * @param nums - represents the amount present in the array
     * @return max rob amount
     */
    public int robRecursion(int[] nums) {
        return robRecursion(nums, nums.length - 1);
    }

    private int robRecursion(int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        return Math.max(robRecursion(nums, i - 2) + nums[i], robRecursion(nums, i - 1));
    }

    /**
     * Using memoization
     *
     * @param nums - represents the amount present in the array
     * @return max rob amount
     */
    public int robMemoization(int[] nums) {
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, -1);
        return robMemoization(dp, nums, nums.length);
    }

    private int robMemoization(int[] dp, int[] nums, int n) {
        if (n <= 0) {
            return 0;
        }
        if (dp[n] >= 0) {
            return dp[n];
        }
        int result = Math.max(robMemoization(dp, nums, n - 2) + nums[n - 1], robMemoization(dp, nums, n - 1));
        dp[n] = result;
        return result;
    }

    /**
     * Using bottom up
     *
     * @param nums - represents the amount present in the array
     * @return max rob amount
     */
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = nums[0];

        for(int i = 2; i <= n; i++) {
            dp[i] = Math.max(nums[i-1] + dp[i - 2], dp[i - 1]);
        }

        return dp[n];
    }

}
