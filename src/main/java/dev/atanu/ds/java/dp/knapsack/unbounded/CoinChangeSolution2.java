/**
 * 
 */
package dev.atanu.ds.java.dp.knapsack.unbounded;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/coin-change/
 * 
 * <br>
 * Minimum number of coins to makeup the amount
 * 
 * @author Atanu Bhowmick
 *
 */
public class CoinChangeSolution2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CoinChangeSolution2 solution = new CoinChangeSolution2();
		int[] price = new int[] { 4, 7, 3 };
		int amount = 18;
		System.out.println(solution.coinChange(price, amount));
		System.out.println(solution.coinChangeTopDown1D(price, amount));
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

		// If coins is empty, and amount is zero, 1 would be returned
		dp[0][0] = 0;

		// First row initialization
		for (int j = 1; j <= amount; j++) {
			dp[0][j] = Integer.MAX_VALUE - 1;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= amount; j++) {
				if (coins[i - 1] <= j) {
					dp[i][j] = Math.min(1 + dp[i][j - coins[i - 1]], dp[i - 1][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[n][amount] >= Integer.MAX_VALUE - 1 ? -1 : dp[n][amount];
	}

	/**
	 * Unbounded knapsack. One dimension solution.
	 * 
	 * @param coins
	 * @param amount
	 * @return
	 */
	public int coinChangeTopDown1D(int[] coins, int amount) {
		int n = coins.length;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, amount + 1);
		dp[0] = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= amount; j++) {
				if (coins[i] <= j) {
					dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}

}
