package dev.atanu.ds.java.sliding.window;

import java.util.HashSet;

/**
 * https://leetcode.com/discuss/study-guide/3722472/mastering-sliding-window-technique-a-comprehensive-guide
 *
 * There are four categories in Sliding Window pattern
 * <p>
 * A. Fixed Size Window
 * B. Variable Size Window — Longest/Maximum
 * C. Variable Size Window — Smallest/Minimum
 * D. Sliding Window + HashMap/Frequency Count
 */
public class DynamicSizeSlidingWindow {

    public static void main(String[] args) {
        DynamicSizeSlidingWindow solution = new DynamicSizeSlidingWindow();
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }


    /**
     * https://leetcode.com/problems/max-consecutive-ones/
     *
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int start = 0, end = 0;
        while(end < nums.length) {
            if(nums[end] == 1) {
                int count = end - start + 1;
                maxCount = Math.max(maxCount, count);
            } else {
                start = end + 1;
            }

            end++;
        }
        return maxCount;
    }


    /**
     * https://www.geeksforgeeks.org/dsa/max-number-of-one-ii/
     *
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnesII(int[] nums) {
        int maxCount = 0;
        int start = 0, end = 0;
        int k = 1;
        while(end < nums.length) {
            if(nums[end] == 1) {
                int count = end - start + 1;
                maxCount = Math.max(maxCount, count);
            } else {
                start = end + 1;
            }

            end++;
        }
        return maxCount;
    }


    /**
     * https://leetcode.com/problems/max-consecutive-ones-iii/
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int i = 0, j;
        for (j = 0; j < nums.length; ++j) {
            if (nums[j] == 0) {
                k--;
            }
            if (k < 0) {
                if(nums[i] == 0) {
                    k++;
                }
                i++;
            }
        }
        return j - i;
    }


    /**
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0, start = 0, end = 0;
        HashSet<Character> set = new HashSet<>();
        while(end < s.length()) {
            char endChar = s.charAt(end);
            while(set.contains(endChar)) {
                char startChar = s.charAt(start);
                set.remove(startChar);
                start++;
            }
            int len = end - start + 1;
            maxLen = Math.max(maxLen, len);
            set.add(endChar);
            end++;
        }

        return maxLen;
    }




}
