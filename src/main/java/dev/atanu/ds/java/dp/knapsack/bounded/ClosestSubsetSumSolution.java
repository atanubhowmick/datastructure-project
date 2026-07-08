/**
 * 
 */
package dev.atanu.ds.java.dp.knapsack.bounded;

/**
 * Sequence - 8
 * <br>
 * 
 * https://leetcode.com/problems/closest-subsequence-sum/
 * 
 * @author Atanu Bhowmick
 *
 */
public class ClosestSubsetSumSolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = new int[] { 1, 5, 6, 10, 15 };
		ClosestSubsetSumSolution solution = new ClosestSubsetSumSolution();
		System.out.println(solution.minAbsDifference(nums, 18));
	}

	/**
	 * Works only for positive input array
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int minAbsDifference(int[] nums, int target) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if (target >= sum) {
			return target - sum;
		} else if (target <= 0) {
			return 0 - target;
		}
		return minDiffSubsetSum(nums, sum, nums.length, target);
	}

	private int minDiffSubsetSum(int[] nums, int sum, int n, int target) {
		boolean[][] dp = new boolean[n + 1][sum + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= sum; j++) {
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
		int j = 0;
		while (j < sum) {
			if ((target - j > 0 && dp[n][target - j]) || (target + j < sum && dp[n][target + j])) {
				return j;
			}
			j++;
		}
		return diff;
	}
}
