/**
 * 
 */
package dev.atanu.ds.java.dp.leetcode;

/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 * 
 * @author Atanu Bhowmick
 *
 */
public class MinCostClimbingStairs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] cost = new int[] { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 };
		MinCostClimbingStairs climb = new MinCostClimbingStairs();
		System.out.println(climb.minCostClimbingStairs(cost));
	}

	/**
	 * Using recursion
	 * 
	 * @param cost
	 * @return
	 */
	public int minCostClimbingStairsRecursion(int[] cost) {
		return Math.min(minCostClimbingStairsRecursion(cost, 0), minCostClimbingStairsRecursion(cost, 1));
	}

	private int minCostClimbingStairsRecursion(int[] cost, int current) {
		if (current >= cost.length) {
			return 0;
		}

		return cost[current] + Math.min(minCostClimbingStairsRecursion(cost, current + 1),
				minCostClimbingStairsRecursion(cost, current + 2));
	}

	/**
	 * Using Memoization or top-down approach
	 * 
	 * @param cost
	 * @return
	 */
	public int minCostClimbingStairs(int[] cost) {
		int[] dp = new int[cost.length];
		for (int i = 0; i < cost.length; i++) {
			dp[i] = -1;
		}

		return Math.min(minCostClimbingStairsMemoization(dp, cost, 0), minCostClimbingStairsMemoization(dp, cost, 1));
	}

	private int minCostClimbingStairsMemoization(int[] dp, int[] cost, int current) {
		if (current >= cost.length) {
			return 0;
		}

		if (dp[current] != -1) {
			return dp[current];
		}

		return dp[current] = cost[current] + Math.min(minCostClimbingStairsMemoization(dp, cost, current + 1),
				minCostClimbingStairsMemoization(dp, cost, current + 2));
	}

	/**
	 * Bottom-up using one dimensional array
	 * 
	 * @param cost
	 * @return
	 */
	public int minCostClimbingStairsBottomUp(int[] cost) {
		int n = cost.length;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			if (i < 2) {
				dp[i] = cost[i];
			} else {
				dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
			}
		}
		return Math.min(dp[n - 1], dp[n - 2]);
	}
}
