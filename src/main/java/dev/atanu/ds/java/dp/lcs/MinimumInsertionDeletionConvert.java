/**
 * 
 */
package dev.atanu.ds.java.dp.lcs;

/**
 * Sequence - 9
 * https://www.geeksforgeeks.org/problems/minimum-number-of-deletions-and-insertions0209/1
 * 
 * @author Atanu Bhowmick
 *
 */
public class MinimumInsertionDeletionConvert {

	public int minOperations(String s1, String s2) {
		LongestCommonSubsequence solution = new LongestCommonSubsequence();
		int lcsLen = solution.lcsBottomUp(s1, s2);
		int deletion = s1.length() - lcsLen;
		int insertion = s2.length() - lcsLen;
		return deletion + insertion;
	}

}
