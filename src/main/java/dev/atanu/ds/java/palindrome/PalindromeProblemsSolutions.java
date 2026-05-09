package dev.atanu.ds.java.palindrome;

import java.util.HashMap;
import java.util.Map;

public class PalindromeProblemsSolutions {

    public static void main(String[] args) {
        PalindromeProblemsSolutions solutions = new PalindromeProblemsSolutions();
        System.out.print(solutions.longestPalindrome("atanu"));
    }

    /**
     * https://leetcode.com/problems/longest-palindrome/
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromMiddle(s, i, i);
            int len2 = expandFromMiddle(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - ((len - 1) / 2);
                end = i + (len / 2);
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandFromMiddle(String s, int left, int right) {
        if (s == null || left > right) {
            return 0;
        }

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }


    /**
     * https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/
     *
     * @param words
     * @return
     */
    public int longestPalindrome(String[] words) {
        int len = 0;
        Map<String, Integer> map = new HashMap<>();
        for(String word : words) {
            String key = new StringBuffer(word).reverse().toString();
            int count = map.getOrDefault(key, 0);
            if(word.charAt(0) == word.charAt(1)) {
                map.put(word, count + 1);
            } else if(count > 0) {
                len += 4;
                map.put(key, count - 1);
            } else {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        boolean hasOddCount = false;

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            String word = entry.getKey();
            int count  = entry.getValue();
            if(word.charAt(0) == word.charAt(1)) {
                if(count % 2 == 0) {
                    len += count * 2;
                } else {
                    len += (count - 1) * 2;
                    hasOddCount = true;
                }
            }
        }

        return len + (hasOddCount ? 2 : 0);
    }
}
