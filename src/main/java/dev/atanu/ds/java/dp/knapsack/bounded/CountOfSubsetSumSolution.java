/**
 * 
 */
package dev.atanu.ds.java.dp.knapsack.bounded;

/**
 * Sequence - 4
 * <br>
 * 
 * https://www.geeksforgeeks.org/count-of-subsets-with-sum-equal-to-x/
 * 
 * @author Atanu Bhowmick
 *
 */
public class CountOfSubsetSumSolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = new int[] { 10, 1, 2, 3, 3, 5 };
		CountOfSubsetSumSolution solution = new CountOfSubsetSumSolution();
		System.out.println(solution.getSubsetCount(nums, 10));
	}

	/**
	 * 
	 * @param nums
	 * @param sum
	 * @return
	 */
	public int getSubsetCount(int[] nums, int sum) {
		int n = nums.length;
		int[][] dp = new int[n + 1][sum + 1];

		// If sum is zero, and array is empty, 1 would be returned
		dp[0][0] = 1;
		
		// First row initialization
		for (int j = 1; j <= sum; j++) {
			dp[0][j] = 0;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= sum; j++) {
				if (nums[i - 1] <= j) {
					dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[n][sum];
	}

}
