/**
 * 
 */
package dev.atanu.ds.java.dp.lcs;

/**
 * @author Atanu Bhowmick
 *
 */
public class LongestRepeatingSubsequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LongestRepeatingSubsequence lrs = new LongestRepeatingSubsequence();
		System.out.println(lrs.longestRepeatingSubsequence("atanu"));
	}

	/**
	 * https://www.geeksforgeeks.org/longest-repeating-subsequence/
	 * 
	 * @param text1
	 * @return
	 */
	public int longestRepeatingSubsequence(String text1) {
		int m = text1.length();
		int[][] dp = new int[m + 1][m + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= m; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (text1.charAt(i - 1) == text1.charAt(j - 1) && i != j) {
					// Condition added (i != j). Rest same as LCS.
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[m][m];
	}

	
	/**
	 * https://leetcode.com/problems/longest-subsequence-repeated-k-times/
	 * 
	 * @param s
	 * @param k
	 * @return
	 */
	public String longestSubsequenceRepeatedK(String s, int k) {
		return null;
	}
}
