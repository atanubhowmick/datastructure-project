/**
 * 
 */
package dev.atanu.ds.java.dp.lcs;

/**
 * https://leetcode.com/problems/shortest-common-supersequence
 * 
 * @author Atanu Bhowmick
 *
 */
public class ShortestCommonSupersequencePrinter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ShortestCommonSupersequencePrinter scs = new ShortestCommonSupersequencePrinter();
		String text1 = "abac";
		String text2 = "cab";
		System.out.println(scs.sscPrint(text1, text2));
		System.out.println(scs.sscPrintBottomUp(text1, text2));
	}

	/**
	 * 
	 * @param text1
	 * @param text2
	 * @return
	 */
	public String sscPrint(String text1, String text2) {
		LongestCommonSubsequencePrinter lcsPrinter = new LongestCommonSubsequencePrinter();
		String lcs = lcsPrinter.lcsPrintBottomUp(text1, text2);

		int i = 0, j = 0, k = 0;
		int m = text1.length();
		int n = text2.length();
		int len = lcs.length();

		StringBuffer sb = new StringBuffer();

		while (i < m && j < n) {
			char ch1 = text1.charAt(i);
			char ch2 = text2.charAt(j);
			if (k >= len) {
				sb.append(ch1);
				sb.append(ch2);
				i++;
				j++;
			} else if (lcs.charAt(k) == ch1 && lcs.charAt(k) == ch2) {
				sb.append(lcs.charAt(k));
				i++;
				j++;
				k++;
			} else if (lcs.charAt(k) != ch1 && lcs.charAt(k) == ch2) {
				sb.append(ch1);
				i++;
			} else {
				sb.append(ch2);
				j++;
			}
		}

		while (i < m) {
			sb.append(text1.charAt(i++));
		}

		while (j < n) {
			sb.append(text2.charAt(j++));
		}

		return sb.toString();
	}

	/**
	 * 
	 * @param text1
	 * @param text2
	 * @return
	 */
	public String sscPrintBottomUp(String text1, String text2) {
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

		return getSSC(dp, text1, text2);
	}

	private String getSSC(int[][] dp, String text1, String text2) {
		int m = text1.length();
		int n = text2.length();

		int len = text1.length() + text2.length() - dp[m][n];
		char[] arr = new char[len];
		int i = m, j = n;

		while (i > 0 && j > 0) {
			if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
				arr[--len] = text1.charAt(i - 1);
				i--;
				j--;
			} else if (dp[i][j - 1] > dp[i - 1][j]) {
				arr[--len] = text2.charAt(j - 1);
				j--;
			} else {
				arr[--len] = text1.charAt(i - 1);
				i--;
			}
		}

		while (i > 0) {
			arr[--len] = text1.charAt(i - 1);
			i--;
		}

		while (j > 0) {
			arr[--len] = text2.charAt(j - 1);
			j--;
		}

		return new String(arr);
	}
}
