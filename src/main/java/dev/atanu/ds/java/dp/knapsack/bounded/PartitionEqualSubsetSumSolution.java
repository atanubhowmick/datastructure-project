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
		System.out.println(solution.canPartion(nums));
	}

	public boolean canPartion(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if (sum % 2 == 1) {
			return false;
		}
		SubsetSumSolution subsetSumSolution = new SubsetSumSolution();
		return subsetSumSolution.hasSubsetSumBottomUp(nums, sum / 2, nums.length);
	}

}
