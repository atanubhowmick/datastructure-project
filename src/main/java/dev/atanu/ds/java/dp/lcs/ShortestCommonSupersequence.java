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
public class ShortestCommonSupersequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ShortestCommonSupersequence scs = new ShortestCommonSupersequence();
		String text1 = "acdk";
		String text2 = "abced";
		System.out.println(scs.scsBottomUp(text1, text2));
	}

	/**
	 * LCS - BottomUp
	 * 
	 * @param text1
	 * @param text2
	 * @return
	 */
	public int scsBottomUp(String text1, String text2) {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		int len = lcs.lcsBottomUp(text1, text2);
		return text1.length() + text2.length() - len;
	}
}
