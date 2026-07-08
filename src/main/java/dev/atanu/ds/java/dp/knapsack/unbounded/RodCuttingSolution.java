/**
 * 
 */
package dev.atanu.ds.java.dp.knapsack.unbounded;

/**
 * https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
 * 
 * @author Atanu Bhowmick
 *
 */
public class RodCuttingSolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RodCuttingSolution solution = new RodCuttingSolution();
		int[] price = new int[] { 2, 5, 8, 9, 10, 17, 19, 22 };
		int maxLen = price.length;
		System.out.println(solution.cutRodRecursion(price, maxLen));
		System.out.println(solution.cutRodTopDown(price, maxLen));
		System.out.println(solution.cutRodBottomUp(price, maxLen));
	}

	/**
	 * Unbounded knapsack - recursion approach
	 * 
	 * @param price
	 * @param maxLen
	 * @return
	 */
	public int cutRodRecursion(int[] price, int maxLen) {
		int[] len = new int[maxLen];
		for (int i = 1; i <= maxLen; i++) {
			len[i - 1] = i;
		}
		return cutRodRecursion(price, len, maxLen, price.length, 0);
	}

	private int cutRodRecursion(int[] price, int[] len, int maxLen, int n) {
		if (n == 0 || maxLen == 0) {
			return 0;
		}

		if (len[n - 1] <= maxLen) {
			return Math.max(price[n - 1] + cutRodRecursion(price, len, maxLen - len[n - 1], n),
					cutRodRecursion(price, len, maxLen, n - 1));
		} else {
			return cutRodRecursion(price, len, maxLen, n - 1);
		}
	}

	private int cutRodRecursion(int[] price, int[] len, int maxLen, int n, int i) {
		if (i == n || maxLen == 0) {
			return 0;
		}

		if (len[i] <= maxLen) {
			return Math.max(price[i] + cutRodRecursion(price, len, maxLen - len[i], n, i),
					cutRodRecursion(price, len, maxLen, n, i + 1));
		} else {
			return cutRodRecursion(price, len, maxLen, n, i + 1);
		}
	}

	/**
	 * Unbounded Knapsack using top-down approach
	 * 
	 * @param price
	 * @param maxLen
	 * @return
	 */
	public int cutRodTopDown(int[] price, int maxLen) {
		int[] len = new int[maxLen];
		for (int i = 1; i <= maxLen; i++) {
			len[i - 1] = i;
		}
		int[][] dp = new int[maxLen + 1][maxLen + 1];
		for (int i = 0; i <= maxLen; i++) {
			for (int j = 0; j <= maxLen; j++) {
				dp[i][j] = -1;
			}
		}
		return cutRodTopDownRec(price, len, maxLen, price.length, dp);
	}

	private int cutRodTopDownRec(int[] price, int[] len, int maxLen, int n, int[][] dp) {
		if (n == 0 || maxLen == 0) {
			return 0;
		}

		if (dp[n][maxLen] != -1) {
			return dp[n][maxLen];
		}

		if (len[n - 1] <= maxLen) {
			return Math.max(price[n - 1] + cutRodTopDownRec(price, len, maxLen - len[n - 1], n, dp),
					cutRodTopDownRec(price, len, maxLen, n - 1, dp));
		} else {
			return cutRodTopDownRec(price, len, maxLen, n - 1, dp);
		}
	}

	/**
	 * Unbounded Knapsack using bottom-up approach
	 * 
	 * @param len
	 * @param price
	 * @param maxLen
	 * @return maxPrice
	 */
	public int cutRodBottomUp(int[] price, int maxLen) {
		int[] len = new int[maxLen];
		for (int i = 1; i <= maxLen; i++) {
			len[i - 1] = i;
		}
		int[][] dp = new int[maxLen + 1][maxLen + 1];
		for (int i = 0; i <= maxLen; i++) {
			for (int j = 0; j <= maxLen; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (len[i - 1] <= j) {
					dp[i][j] = Math.max((dp[i][j - len[i - 1]] + price[i - 1]), dp[i - 1][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[maxLen][maxLen];
	}

}
