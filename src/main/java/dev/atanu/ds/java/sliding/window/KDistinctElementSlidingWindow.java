package dev.atanu.ds.java.sliding.window;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class KDistinctElementSlidingWindow {

    public static void main(String[] args) {
        KDistinctElementSlidingWindow solution = new KDistinctElementSlidingWindow();
        System.out.println(solution.numberOfSubarrays(new int[]{2,2,2,1,2,2,1,2,2,2}, 2));
    }

    /**
     * Common framework to solve k distinct element problems
     * <p>Questions:
     * <ul>
     *     <li>https://leetcode.com/problems/fruit-into-baskets/
     *     <li>https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
     *     <li>https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
     *     <li>https://leetcode.com/problems/count-number-of-nice-subarrays/
     *     <li>https://leetcode.com/problems/fruit-into-baskets/
     *     <li>https://leetcode.com/problems/fruit-into-baskets/
     * </ul>
     *
     *
     * @param nums
     * @return
     */
    public int kDistinctElement(int[] nums, int k) {
        int left = 0, right = 0;
        int res = 0;

        // HashMp that hold the num and it's occurrence count
        Map<Integer, Integer> map = new HashMap<>();

        while (right < nums.length) {
            int last = nums[right];
            map.put(last, map.getOrDefault(last, 0) + 1);

            while (map.size() > k) {
                int first = nums[left];
                map.put(first, map.get(first) - 1);
                if (map.get(first) == 0) {
                    map.remove(first);
                }
                left++;
            }

            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }


    /**
     * Using HashMap
     * https://leetcode.com/problems/fruit-into-baskets/
     *
     * @param fruits
     * @return
     */
    public int totalFruit(int[] fruits) {
        int left = 0, right = 0, k = 2;
        int res = 0;

        // HashMp that hold the num and it's occurrence count
        Map<Integer, Integer> map = new HashMap<>();

        while (right < fruits.length) {
            int last = fruits[right];
            map.put(last, map.getOrDefault(last, 0) + 1);

            while (map.size() > k) {
                int first = fruits[left];
                map.put(first, map.get(first) - 1);
                if (map.get(first) == 0) {
                    map.remove(first);
                }
                left++;
            }

            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/count-number-of-nice-subarrays/
     *
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int left = 0, right = 0;
        int oddCount = 0, count = 0, res = 0;

        while (right < nums.length) {
            if (nums[right] % 2 == 1) {
                oddCount++;
                count = 0;
            }

            while(oddCount == k) {
                count++;
                if (nums[left] % 2 == 1) {
                    oddCount--;
                }
                left++;
            }
            res += count;
            right++;
        }
        return res;
    }


}
