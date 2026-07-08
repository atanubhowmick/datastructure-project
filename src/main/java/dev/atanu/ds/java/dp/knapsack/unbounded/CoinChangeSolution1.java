/**
 * 
 */
package dev.atanu.ds.java.dp.knapsack.unbounded;

/**
 * https://leetcode.com/problems/coin-change-2/
 * 
 * <br>
 * Number of ways to makeup the amount
 * 
 * @author Atanu Bhowmick
 *
 */
public class CoinChangeSolution1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CoinChangeSolution1 solution = new CoinChangeSolution1();
		int[] price = new int[] { 1, 2, 5 };
		int amount = 5;
		System.out.println(solution.coinChange(price, amount));
	}

	/**
	 * Unbounded knapsack
	 * 
	 * @param coins
	 * @param amount
	 * @return
	 */
	public int coinChange(int[] coins, int amount) {
		int n = coins.length;

		int[][] dp = new int[n + 1][amount + 1];

		// If amount is zero, and array is empty, 1 would be returned
		dp[0][0] = 1;

		// First row initialization
		for (int j = 1; j <= amount; j++) {
			dp[0][j] = 0;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= amount; j++) {
				if (coins[i - 1] <= j) {
					dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[n][amount];
	}

}
