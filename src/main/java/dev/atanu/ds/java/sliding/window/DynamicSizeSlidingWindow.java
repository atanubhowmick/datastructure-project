package dev.atanu.ds.java.sliding.window;

import java.util.Arrays;
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
        int start = 0, end = 0;
        int maxLen = 0;
        int k = 1;
        while(end < nums.length) {
            if(nums[end] == 0) {
                k--;
            }
            while(k < 0) {
                if(nums[start] == 0) {
                    k++;
                }
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1);

            end++;
        }
        return maxLen;
    }


    /**
     * https://leetcode.com/problems/max-consecutive-ones-iii/
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int start = 0, end = 0;
        int maxLen = 0;

        while(end < nums.length) {
            if(nums[end] == 0) {
                k--;
            }
            while(k < 0) {
                if(nums[start] == 0) {
                    k++;
                }
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1);

            end++;
        }
        return maxLen;
    }


    /**
     * https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
     *
     * Same framework like above
     * @param nums
     * @return
     */
    public int longestSubarray(int[] nums) {
        int start = 0, end = 0;
        int maxLen = 0, k = 1;

        while(end < nums.length) {
            if(nums[end] == 0) {
                k--;
            }

            while(k < 0) {
                if(nums[start] == 0) {
                    k++;
                }
                start++;
            }
            // As 1 element must always be deleted,
            // len = (end - start) instead of (end - start + 1).

            // If 1 element can be deleted (instead of must be),
            // len = (end - start + k)
            int len = end - start;
            maxLen = Math.max(len, maxLen);
            end++;
        }

        return maxLen;
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


    /**
     * https://leetcode.com/problems/frequency-of-the-most-frequent-element/
     * https://leetcode.com/problems/frequency-of-the-most-frequent-element/solutions/1175090/javacpython-sliding-window-by-lee215-2qgq/
     * https://leetcode.com/problems/frequency-of-the-most-frequent-element/solutions/1175088/c-maximum-sliding-window-cheatsheet-temp-bxw4/
     *
     * See the related problems - this is a unique pattern
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxFrequency(int[] nums, int k) {
        int res = 1, start = 0, end = 0;
        long sum = 0;
        Arrays.sort(nums);
        while (end < nums.length) {
            sum += nums[end];
            while (sum + k < (long) nums[end] * (end - start + 1)) {
                sum -= nums[start];
                start += 1;
            }
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }

}
