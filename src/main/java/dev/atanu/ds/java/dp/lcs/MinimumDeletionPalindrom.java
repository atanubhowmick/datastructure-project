/**
 * 
 */
package dev.atanu.ds.java.dp.lcs;

/**
 * https://www.geeksforgeeks.org/minimum-number-deletions-make-string-palindrome/
 * 
 * @author Atanu Bhowmick
 *
 */
public class MinimumDeletionPalindrom {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MinimumDeletionPalindrom deletionPalindrom = new MinimumDeletionPalindrom();
		System.out.println(deletionPalindrom.minimumDeletionPalindrom("atata1"));
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public int minimumDeletionPalindrom(String s) {
		String reversedString = new StringBuffer(s).reverse().toString();
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		int lcsLen = lcs.lcsBottomUp(s, reversedString);
		return s.length() - lcsLen;
	}
}
