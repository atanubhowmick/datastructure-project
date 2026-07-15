package dev.atanu.ds.java.dp.lcs;

/**
 * Sequence - 10
 *
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if(s == null || s.isEmpty()) {
            return "";
        }

        int start = 0, end = 0;
        int maxLen = 0;

        for(int i = 0; i < s.length(); i++) {
            int[] subStr1 = expandFromMiddle(s, i, i);
            int[] subStr2 = expandFromMiddle(s, i, i + 1);

            int len1 = subStr1[1] - subStr1[0] + 1;
            int len2 = subStr2[1] - subStr2[0] + 1;
            if(len1 > len2) {
                if(len1 > maxLen) {
                    start = subStr1[0];
                    end = subStr1[1];
                    maxLen = len1;
                }
            } else {
                if(len2 > maxLen) {
                    start = subStr2[0];
                    end = subStr2[1];
                    maxLen = len2;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    private int[] expandFromMiddle(String s, int left, int right) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return new int[] {left + 1, right - 1};
    }
}
