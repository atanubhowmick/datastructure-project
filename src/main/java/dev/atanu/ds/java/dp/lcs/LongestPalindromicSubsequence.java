/**
 * 
 */
package dev.atanu.ds.java.dp.lcs;

/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 * 
 * @author Atanu Bhowmick
 *
 */
public class LongestPalindromicSubsequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LongestPalindromicSubsequence palindromicSubsequence = new LongestPalindromicSubsequence();
		System.out.println(palindromicSubsequence.longestPalindromeSubseq("akk"));
	}

	
	/**
	 * @param s
	 * @return
	 */
	public int longestPalindromeSubseq(String s) {
		String reversedString = new StringBuffer(s).reverse().toString();
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        return lcs.lcsBottomUp(s, reversedString);
    }

}
