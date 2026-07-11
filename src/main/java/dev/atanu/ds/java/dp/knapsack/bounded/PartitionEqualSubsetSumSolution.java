/**
 * 
 */
package dev.atanu.ds.java.dp.knapsack.bounded;

/**
 * Sequence - 3
 * <br>
 * 
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * 
 * @author Atanu Bhowmick
 *
 */
public class PartitionEqualSubsetSumSolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = new int[] { 1, 5, 11, 5 };
		PartitionEqualSubsetSumSolution solution = new PartitionEqualSubsetSumSolution();
		System.out.println(solution.canPartition(nums));
	}


	/**
	 * Using existing solution
	 *
	 * @param nums - nums
	 * @return - boolean
	 */
	public boolean canPartition(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}

		// If total sum is odd, cannot partition equally
		if (sum % 2 == 1) {
			return false;
		}

		SubsetSumSolution subsetSumSolution = new SubsetSumSolution();
		return subsetSumSolution.hasSubsetSumBottomUp(nums, sum / 2, nums.length);
	}


	/**
	 * Space optimization - One-D-Array
	 *
	 * @param nums - nums
	 * @return boolean
	 */
	public boolean canPartition1(int[] nums) {
		int sum = 0;

		for (int num : nums) {
			sum += num;
		}

		// If total sum is odd, cannot partition equally
		if (sum % 2 != 0) {
			return false;
		}

		int target = sum / 2;
		boolean[] dp = new boolean[target + 1];
		dp[0] = true;

		for (int num : nums) {
			for (int j = target; j >= num; j--) {
				dp[j] = dp[j] || dp[j - num];
			}
		}

		return dp[target];
	}
}
