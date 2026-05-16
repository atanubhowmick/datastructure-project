package dev.atanu.ds.java.sliding.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicSizeSlidingWindowMinimum {

    public static void main(String[] args) {
        DynamicSizeSlidingWindowMinimum solution = new DynamicSizeSlidingWindowMinimum();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
    }

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
     * Similar questions: (See below)
     * https://leetcode.com/problems/permutation-in-string/
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/
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

        int counter = t.length();
        int minStart = 0, minLen = Integer.MAX_VALUE;
        int start = 0, end = 0;

        while(end < s.length()) {
            char endChar = s.charAt(end);
            if (map.getOrDefault(endChar, 0) > 0) {
                counter--;
            }

            map.put(endChar, map.getOrDefault(endChar, 0) - 1);

            while(counter == 0) {
                if(end - start + 1 < minLen) {
                    minStart = start;
                    minLen = end - start + 1;
                }
                char startChar = s.charAt(start);
                map.put(startChar, map.getOrDefault(startChar, 0) + 1);
                if (map.getOrDefault(startChar, 0) > 0) {
                    counter++;
                }
                start++;
            }
            end++;
        }

        return minLen == Integer.MAX_VALUE? "" : s.substring(minStart, minStart + minLen);
    }


    /**
     * https://leetcode.com/problems/permutation-in-string/
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (char ch : s1.toCharArray()) {
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }

        Map<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0, matchCount = 0;

        while (end < s2.length()) {
            char endChar = s2.charAt(end);
            if(frequency.containsKey(endChar)) {
                map.put(endChar, map.getOrDefault(endChar, 0) + 1);
                if(map.get(endChar).equals(frequency.get(endChar))) {
                    matchCount++;
                }
            }

            while(matchCount == frequency.size()) {
                if(end - start + 1 == s1.length()) {
                    return true;
                }

                char startChar = s2.charAt(start);
                if(frequency.containsKey(startChar)) {
                    map.put(startChar, map.get(startChar) - 1);
                    if(map.get(startChar) < frequency.get(startChar)) {
                        matchCount--;
                    }
                }
                start++;
            }
            end++;
        }
        return false;
    }


    /**
     * https://leetcode.com/problems/permutation-in-string/
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion1(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) {
            return false;
        }

        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        if (allZero(count)) {
            return true;
        }

        for (int i = len1; i < len2; i++) {
            count[s2.charAt(i) - 'a']--;
            count[s2.charAt(i - len1) - 'a']++;
            if (allZero(count)) {
                return true;
            }
        }

        return false;
    }

    private boolean allZero(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        Map<Character, Integer> frequency = new HashMap<>();
        for(char ch : p.toCharArray()) {
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }

        Map<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0, matchCount = 0;
        while(end < s.length()) {
            char endChar = s.charAt(end);
            if(frequency.containsKey(endChar)) {
                map.put(endChar, map.getOrDefault(endChar, 0) + 1);
                if(map.get(endChar).equals(frequency.get(endChar))) {
                    matchCount++;
                }
            }

            while(matchCount == frequency.size()) {
                if(end - start + 1 == p.length()) {
                    ans.add(start);
                }

                char startChar = s.charAt(start);
                if(frequency.containsKey(startChar)) {
                    map.put(startChar, map.get(startChar) - 1);
                    if(map.get(startChar) < frequency.get(startChar)) {
                        matchCount--;
                    }
                }
                start++;
            }
            end++;
        }
        return ans;
    }


    /**
     * https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
     *
     * @param nums
     * @param x
     * @return
     */
    public int minOperations(int[] nums, int x) {
        int sum  = 0;
        for(int num : nums) {
            sum += num;
        }

        int target = sum - x;
        int maxLen = -1, n = nums.length;
        int start = 0, end = 0;

        while(end < n) {
            target -= nums[end];
            while(start <= end && target < 0) {
                target += nums[start];
                start++;
            }
            if(target == 0) {
                maxLen = Math.max(maxLen, end - start + 1);
            }
            end++;
        }

        return maxLen == -1 ? - 1 : (n - maxLen);
    }
}
