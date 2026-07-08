/**
 * 
 */
package dev.atanu.ds.java.dp.lcs;

/**
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/
 * 
 * @author Atanu Bhowmick
 *
 */
public class MinimumInsertionPalindrom {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MinimumInsertionPalindrom palindrom = new MinimumInsertionPalindrom();
		System.out.println(palindrom.minInsertions("atanu"));
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public int minInsertions(String s) {
		String s2 = new StringBuffer(s).reverse().toString();
		LongestCommonSubstring lcs = new LongestCommonSubstring();
		int lcsLen = lcs.lcSubstringBottomUp(s, s2);
		return s.length() - lcsLen;
	}

}
