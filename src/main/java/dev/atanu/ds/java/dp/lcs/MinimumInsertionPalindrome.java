/**
 * 
 */
package dev.atanu.ds.java.dp.lcs;

/**
 * Sequence - 8
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/
 * 
 * @author Atanu Bhowmick
 *
 */
public class MinimumInsertionPalindrome {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MinimumInsertionPalindrome palindrom = new MinimumInsertionPalindrome();
		System.out.println(palindrom.minInsertions("atanu"));
	}

	/**
	 * 
	 * @param s - s
	 * @return min insertion
	 */
	public int minInsertions(String s) {
		String s2 = new StringBuffer(s).reverse().toString();
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		int lcsLen = lcs.lcsBottomUp(s, s2);
		return s.length() - lcsLen;
	}

}
