/**
 * 
 */
package dev.atanu.ds.java.dp.knapsack.bounded;

/**
 * Sequence - 5
 * <br>
 * https://leetcode.com/problems/target-sum/
 * https://leetcode.com/discuss/interview-question/1271034/count-no-of-subsets-with-given-difference-dp
 * 
 * @author Atanu Bhowmick
 *
 */
public class CountSubsetsGivenDiffSolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = new int[] { 3, 5, 6, 10, 15 };
		CountSubsetsGivenDiffSolution solution = new CountSubsetsGivenDiffSolution();
		System.out.println(solution.countWithGivenDiff(nums, 1));
	}

	public int countWithGivenDiff(int[] nums, int diff) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}

		// s1 - s2 = diff
		// s1 + s2 = sum
		// -------------------------
		// (Add) 2 * s1 = diff + sum
		// s1 = (diff + sum) / 2
		int reqSum = (diff + sum) / 2;

		CountOfSubsetSumSolution solution = new CountOfSubsetSumSolution();
		return solution.getSubsetCount(nums, reqSum);
	}

}
