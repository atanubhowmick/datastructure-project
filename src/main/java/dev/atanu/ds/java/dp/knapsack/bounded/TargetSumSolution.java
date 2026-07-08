/**
 * 
 */
package dev.atanu.ds.java.dp.knapsack.bounded;

/**
 * Sequence - 6
 * <br>
 * 
 * https://leetcode.com/problems/target-sum/
 * 
 * @author Atanu Bhowmick
 *
 */
public class TargetSumSolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = new int[] { 1, 1, 1, 1, 1 };
		TargetSumSolution solution = new TargetSumSolution();
		System.out.println(solution.findTargetSumWays(nums, 3));
	}

	/**
	 * Find target sum expression count
	 * 
	 * <p>
	 * Same as {@link CountSubsetsGivenDiffSolution}
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int findTargetSumWays(int[] nums, int target) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}

		if (target > sum || target < -sum || (sum - target) % 2 == 1) {
			return 0;
		}

		// s1 - s2 = diff
		// s1 + s2 = sum
		// -------------------------
		// (Add) 2 * s1 = diff + sum
		// s1 = (diff + sum) / 2
		int reqSum = (target + sum) / 2;

		CountOfSubsetSumSolution solution = new CountOfSubsetSumSolution();
		return solution.getSubsetCount(nums, reqSum);
	}
}
