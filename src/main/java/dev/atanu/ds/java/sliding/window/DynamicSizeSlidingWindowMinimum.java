package dev.atanu.ds.java.sliding.window;

import java.util.HashMap;
import java.util.Map;

public class DynamicSizeSlidingWindowMinimum {


    /**
     * https://leetcode.com/problems/minimum-size-subarray-sum/
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int start = 0, end = 0, sum = 0;
        int minLen = Integer.MAX_VALUE;
        while(end < nums.length) {
            sum += nums[end];
            while (sum >= target) {
                int len = end - start + 1;
                minLen = Math.min(len, minLen);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }


    /**
     * https://leetcode.com/problems/minimum-window-substring/
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int minLen = Integer.MAX_VALUE;
        int start = 0, end = 0;

        while(end < s.length()) {
            char endChar = s.charAt(end);
            if(map.containsKey(endChar)) {
                map.put(endChar, map.getOrDefault(endChar, 0) - 1);
                if(map.get(endChar) <= 0) {
                    map.remove(endChar);
                }
            }
            if(map.isEmpty()) {
                int len = end - start + 1;
                minLen = Math.min(minLen, len);
            }
            end++;
        }

        return minLen == Integer.MAX_VALUE? "" : s.substring(start, start + minLen);
    }
}
