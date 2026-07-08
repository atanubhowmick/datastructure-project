package dev.atanu.ds.java.dp.knapsack.bounded;

/**
 * Sequence - 1
 * <br>
 *
 * Given n items where each item has some weight and profit associated with it and also given a bag with capacity W,
 * [i.e., the bag can hold at most W weight in it]. The task is to put the items into the bag such that the sum of
 * profits associated with them is the maximum possible.
 *
 * Input:  W = 9, profit[] = [2, 3, 1, 4], weight[] = [3, 4, 6, 5]
 * Output: 7
 *
 * <br>
 *
 * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
 * 
 * @author Atanu Bhowmick
 *
 */
public class KnapSackSolution {

	public static void main(String[] args) {
		KnapSackSolution solution = new KnapSackSolution();
		int[] wt = new int[] { 3, 4, 6, 5 };
		int[] val = new int[] { 2, 3, 1, 4 };
		int maxWt = 9;

		int n = wt.length;

		int maxValItr = solution.knapSackRecursion(maxWt, wt, val, n);
		System.out.println(maxValItr);

		int maxValMem = solution.knapSackMemoization(maxWt, wt, val, n);
		System.out.println(maxValMem);

		int maxVal = solution.knapSackBottomUp(maxWt, wt, val, n);
		System.out.println(maxVal);

		int maxFinal = solution.knapSackFinal(maxWt, wt, val, n);
		System.out.println(maxFinal);
	}

	/**
	 * Recursive approach
	 * 
	 * @param maxWt
	 * @param wt
	 * @param val
	 * @param n
	 * @return
	 */
	public int knapSackRecursion(int maxWt, int[] wt, int[] val, int n) {
		if (n == 0 || maxWt == 0) {
			return 0;
		}

		if (wt[n - 1] > maxWt) {
			return knapSackRecursion(maxWt, wt, val, n - 1);
		} else {
			return Math.max((val[n - 1] + knapSackRecursion(maxWt - wt[n - 1], wt, val, n - 1)),
					knapSackRecursion(maxWt, wt, val, n - 1));
		}
	}

	/**
	 * Using Memoization or top-down approach
	 * 
	 * @param maxWt
	 * @param wt
	 * @param val
	 * @param n
	 * @return
	 */
	public int knapSackMemoization(int maxWt, int[] wt, int[] val, int n) {
		int[][] dp = new int[n + 1][maxWt + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= maxWt; j++) {
				dp[i][j] = -1;
			}
		}
		return knapSackMemoizationRec(maxWt, wt, val, n, dp);
	}

	private int knapSackMemoizationRec(int maxWt, int[] wt, int[] val, int n, int[][] dp) {
		if (n == 0 || maxWt == 0) {
			return 0;
		}
		if (dp[n][maxWt] != -1) {
			return dp[n][maxWt];
		}

		if (wt[n - 1] > maxWt) {
			dp[n][maxWt] = knapSackMemoizationRec(maxWt, wt, val, n - 1, dp);
		} else {
			dp[n][maxWt] = Math.max((val[n - 1] + knapSackMemoizationRec(maxWt - wt[n - 1], wt, val, n - 1, dp)),
					knapSackMemoizationRec(maxWt, wt, val, n - 1, dp));
		}
		return dp[n][maxWt];
	}

	/**
	 * Iterative or bottom-up approach
	 * 
	 * @param maxWt
	 * @param wt
	 * @param val
	 * @param n
	 * @return
	 */
	public int knapSackBottomUp(int maxWt, int[] wt, int[] val, int n) {
		int[][] dp = new int[n + 1][maxWt + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= maxWt; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (wt[i - 1] <= j) {
					dp[i][j] = Math.max((val[i - 1] + dp[i - 1][j - wt[i - 1]]), dp[i - 1][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[n][maxWt];
	}

	/**
	 * Bottom-up using one dimensional array
	 * 
	 * @param maxWt
	 * @param wt
	 * @param val
	 * @param n
	 * @return
	 */
	public int knapSackFinal(int maxWt, int[] wt, int[] val, int n) {
		int[] dp = new int[maxWt + 1];
		// Need to initialize the dp array with zero. But it is not required in Java
		// array.

		for (int i = 1; i <= n; i++) {
			for (int j = maxWt; j >= 0; j--) {
				if (wt[i - 1] <= j) {
					// Single row and handle the previous row data if we start from end
					dp[j] = Math.max((val[i - 1] + dp[j - wt[i - 1]]), dp[j]);
				}
			}
		}
		return dp[maxWt];
	}

}
