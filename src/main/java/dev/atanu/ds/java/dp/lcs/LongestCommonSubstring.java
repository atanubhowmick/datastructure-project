/**
 * 
 */
package dev.atanu.ds.java.dp.lcs;

/**
 * https://www.codeburps.com/dsa/longest-common-substring
 * https://www.geeksforgeeks.org/longest-common-substring-dp-29/
 * 
 * Only look into the bottom-up approach
 * 
 * @author Atanu Bhowmick
 *
 */
public class LongestCommonSubstring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LongestCommonSubstring lcSubstring = new LongestCommonSubstring();
		String text1 = "abcdef";
		String text2 = "bcdee";
		System.out.println(lcSubstring.lcSubstringRecurrsion(text1, text2));
		System.out.println(lcSubstring.lcSubstringMemoization(text1, text2));
		System.out.println(lcSubstring.lcSubstringBottomUp(text1, text2));
	}

	/**
	 * Longest Common Substring - Recursive
	 * 
	 * @param text1
	 * @param text2
	 * @return
	 */
	public int lcSubstringRecurrsion(String text1, String text2) {
		return lcSubstringRecurrsion(text1, text2, text1.length(), text2.length(), 0);
	}

	private int lcSubstringRecurrsion(String text1, String text2, int m, int n, int len) {
		if (m == 0 || n == 0) {
			return len;
		}

		if (text1.charAt(m - 1) == text2.charAt(n - 1)) {
			len = lcSubstringRecurrsion(text1, text2, m - 1, n - 1, len + 1);
		}

		len = Math.max(len, Math.max(lcSubstringRecurrsion(text1, text2, m - 1, n, 0),
				lcSubstringRecurrsion(text1, text2, m, n - 1, 0)));

		return len;
	}

	/**
	 * Longest Common Substring - Memoization or top-down approach
	 * 
	 * @param text1
	 * @param text2
	 * @return
	 */
	public int lcSubstringMemoization(String text1, String text2) {
		int m = text1.length();
		int n = text2.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				dp[i][j] = -1;
			}
		}
		lcSubstringMemoization(dp, text1, text2, m, n);
		return maxlenMemoization;
	}

	int maxlenMemoization = 0;

	private int lcSubstringMemoization(int[][] dp, String text1, String text2, int m, int n) {
		if (m == 0 || n == 0) {
			return 0;
		}

		if (dp[m][n] != -1) {
			return dp[m][n];
		}

		if (text1.charAt(m - 1) == text2.charAt(n - 1)) {
			dp[m][n] = 1 + lcSubstringMemoization(dp, text1, text2, m - 1, n - 1);
		}

		lcSubstringMemoization(dp, text1, text2, m - 1, n);
		lcSubstringMemoization(dp, text1, text2, m, n - 1);

		maxlenMemoization = Math.max(maxlenMemoization, dp[m][n]);
		dp[m][n] = Math.max(dp[m][n], 0);
		return dp[m][n];
	}

	/**
	 * Longest Common Substring - BottomUp
	 * 
	 * @param text1
	 * @param text2
	 * @return
	 */
	public int lcSubstringBottomUp(String text1, String text2) {
		int m = text1.length();
		int n = text2.length();
		int[][] dp = new int[m + 1][n + 1];
		int maxLen = -1;
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = 0;
				}
				maxLen = Math.max(maxLen, dp[i][j]);
			}
		}
		return maxLen;
	}
}
