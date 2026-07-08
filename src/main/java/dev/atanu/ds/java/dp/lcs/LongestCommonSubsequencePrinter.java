/**
 * 
 */
package dev.atanu.ds.java.dp.lcs;

/**
 * https://www.geeksforgeeks.org/printing-longest-common-subsequence/
 * 
 * @author Atanu Bhowmick
 *
 */
public class LongestCommonSubsequencePrinter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LongestCommonSubsequencePrinter lcs = new LongestCommonSubsequencePrinter();
		String text1 = "bcacaaab";
		String text2 = "bbabaccc";
		System.out.println("LCS: " + lcs.lcsPrintBottomUp(text1, text2));
	}

	/**
	 * Print LCS - BottomUp
	 * 
	 * @param text1
	 * @param text2
	 * @return
	 */
	public String lcsPrintBottomUp(String text1, String text2) {
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

		System.out.println("LCS length: " + dp[m][n]);

		return getLcs(dp, text1, text2);
	}

	private String getLcs(int[][] dp, String text1, String text2) {
		int m = text1.length();
		int n = text2.length();

		int len = dp[m][n];
		char[] arr = new char[len];
		int i = m, j = n;

		while (i > 0 && j > 0) {
			if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
				arr[--len] = text1.charAt(i - 1);
				i--;
				j--;
			} else if (dp[i][j - 1] > dp[i - 1][j]) {
				j--;
			} else {
				i--;
			}
		}

		return new String(arr);
	}
}
