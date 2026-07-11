/**
 * 
 */
package dev.atanu.ds.java.dp.knapsack.bounded;

/**
 * Sequence - 2
 * <br>
 * 
 * https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
 * 
 * @author Atanu Bhowmick
 *
 */
public class SubsetSumSolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = new int[] { 2, 5, 10, 7, 18, 14, 20 };
		SubsetSumSolution solution = new SubsetSumSolution();
		System.out.println(solution.hasSubsetSum(nums, 25, nums.length));
		System.out.println(solution.hasSubsetSumMemoization(nums, 25, nums.length));
		System.out.println(solution.hasSubsetSumBottomUp(nums, 25, nums.length));
	}

	/**
	 * Recursive solution
	 *
	 * @param nums - nums
	 * @param sum - sum
	 * @param n - n
	 * @return boolean
	 */
	public boolean hasSubsetSum(int[] nums, int sum, int n) {
		if (sum == 0) {
			return true;
		} else if (n == 0) {
			return false;
		}

		if (nums[n - 1] <= sum) {
			return hasSubsetSum(nums, sum - nums[n - 1], n - 1) || hasSubsetSum(nums, sum, n - 1);
		} else {
			return hasSubsetSum(nums, sum, n - 1);
		}

	}

	/**
	 * Check subset sum with memoization. For boolean output always use Boolean
	 * (wrapper class) array matrix to store the result of the sub-problems. Boolean
	 * gives TRUE, FALSE and NULL values to differentiate the outcome.
	 *
	 * @param nums - nums
	 * @param sum - sum
	 * @param n - n
	 * @return boolean
	 */
	public boolean hasSubsetSumMemoization(int[] nums, int sum, int n) {
		Boolean[][] dp = new Boolean[n + 1][sum + 1];
		// No need to initialize the array as by default it would be filled with NULL
		// values. Will use this NULL check to get the previous sub-problem outcome.
		return hasSubsetSumMemoizationRec(dp, nums, sum, n);
	}

	private Boolean hasSubsetSumMemoizationRec(Boolean[][] dp, int[] nums, int sum, int n) {
		if (dp[n][sum] != null) {
			return dp[n][sum];
		}

		if (sum == 0) {
			return Boolean.TRUE;
		} else if (n == 0) {
			return Boolean.FALSE;
		}

		if (nums[n - 1] <= sum) {
			dp[n][sum] = hasSubsetSumMemoizationRec(dp, nums, sum - nums[n - 1], n - 1) 
					|| hasSubsetSumMemoizationRec(dp, nums, sum, n - 1);
		} else {
			dp[n][sum] = hasSubsetSumMemoizationRec(dp, nums, sum, n - 1);
		}

		return dp[n][sum];
	}

	/**
	 * Bottom-up solution
	 * 
	 * @param nums - nums
	 * @param sum - sum
	 * @param n - n
	 * @return boolean
	 */
	public boolean hasSubsetSumBottomUp(int[] nums, int sum, int n) {
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
		return dp[n][sum];
	}

}
