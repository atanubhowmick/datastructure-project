/**
 * 
 */
package dev.atanu.ds.java.dp.lcs;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * https://www.geeksforgeeks.org/longest-common-subsequence-dp-using-memoization/
 * 
 * @author Atanu Bhowmick
 *
 */
public class LongestCommonSubsequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		String text1 = "acdk";
		String text2 = "abced";
		System.out.println(lcs.lcsRecurrsion(text1, text2));
		System.out.println(lcs.lcsMemoization(text1, text2));
		System.out.println(lcs.lcsBottomUp(text1, text2));
	}

	/**
	 * LCS - Recursive
	 * 
	 * @param text1
	 * @param text2
	 * @return
	 */
	public int lcsRecurrsion(String text1, String text2) {
		return lcsRecurrsion(text1, text2, text1.length(), text2.length());
	}

	private int lcsRecurrsion(String text1, String text2, int m, int n) {
		if (m == 0 || n == 0) {
			return 0;
		}
		if (text1.charAt(m - 1) == text2.charAt(n - 1)) {
			return 1 + lcsRecurrsion(text1, text2, m - 1, n - 1);
		} else {
			return Math.max(lcsRecurrsion(text1, text2, m - 1, n), lcsRecurrsion(text1, text2, m, n - 1));
		}
	}

	/**
	 * LCS - Memoization or top-down approach
	 * 
	 * @param text1
	 * @param text2
	 * @return
	 */
	public int lcsMemoization(String text1, String text2) {
		int m = text1.length();
		int n = text2.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				dp[i][j] = -1;
			}
		}
		return lcsMemoization(dp, text1, text2, m, n);
	}

	private int lcsMemoization(int[][] dp, String text1, String text2, int m, int n) {
		if (m == 0 || n == 0) {
			return 0;
		}

		if (dp[m][n] != -1) {
			return dp[m][n];
		}

		if (text1.charAt(m - 1) == text2.charAt(n - 1)) {
			dp[m][n] = 1 + lcsMemoization(dp, text1, text2, m - 1, n - 1);
		} else {
			dp[m][n] = Math.max(lcsMemoization(dp, text1, text2, m - 1, n), 
						lcsMemoization(dp, text1, text2, m, n - 1));
		}
		return dp[m][n];
	}

	/**
	 * LCS - BottomUp
	 * 
	 * @param text1
	 * @param text2
	 * @return
	 */
	public int lcsBottomUp(String text1, String text2) {
		int m = text1.length();
		int n = text2.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[m][n];
	}
}
