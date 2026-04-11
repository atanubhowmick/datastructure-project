package dev.atanu.ds.java.sliding.window;

import java.util.HashMap;
import java.util.Map;

public class KDistinctElementSlidingWindow {

    public static void main(String[] args) {
        KDistinctElementSlidingWindow solution = new KDistinctElementSlidingWindow();
        System.out.println(solution.totalFruit(new int[]{1, 0, 1, 4, 1, 4, 1, 2, 3}));
    }

    /**
     * Common framework to solve k distinct element problems
     * <p>Questions:
     * <ul>
     *     <li>https://leetcode.com/problems/fruit-into-baskets/
     *     <li>https://leetcode.com/problems/count-number-of-nice-subarrays/
     *     <li>https://leetcode.com/problems/fruit-into-baskets/
     *     <li>https://leetcode.com/problems/fruit-into-baskets/
     *     <li>https://leetcode.com/problems/fruit-into-baskets/
     *     <li>https://leetcode.com/problems/fruit-into-baskets/
     * </ul>
     *
     *
     * @param nums
     * @return
     */
    public int kDistinctElement(int[] nums, int k) {
        int start = 0, end = 0;
        int res = 0;

        // HashMp that hold the num and it's occurrence count
        Map<Integer, Integer> map = new HashMap<>();

        while (end < nums.length) {
            int right = nums[end];
            map.put(right, map.getOrDefault(right, 0) + 1);

            while (map.size() > k) {
                int left = nums[start];
                map.put(left, map.get(left) - 1);
                if (map.get(left) == 0) {
                    map.remove(left);
                }
                start++;
            }

            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }

    /**
     * Using two variable as number of distinct element is 2.
     * https://leetcode.com/problems/fruit-into-baskets/
     *
     * @param fruits
     * @return
     */
    public int totalFruit(int[] fruits) {
        int lastFruit = -1;
        int secondLastFruit = -1;

        int lastFruitCount = 0;

        int currMax = 0;
        int max = 0;

        for (int i = 0; i < fruits.length; i++) {
            int fruit = fruits[i];
            if (fruit == lastFruit) {
                lastFruitCount++;
                currMax++;
            } else if (fruit == secondLastFruit) {
                currMax++;
            } else {
                currMax = lastFruitCount + 1;
                lastFruitCount = 1;

                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }

            max = Math.max(max, currMax);
        }

        return max;
    }

    /**
     * Using HashMap
     * https://leetcode.com/problems/fruit-into-baskets/
     *
     * @param fruits
     * @return
     */
    public int totalFruit1(int[] fruits) {
        int start = 0, end = 0, k = 2;
        int res = 0;

        // HashMp that hold the num and it's occurrence count
        Map<Integer, Integer> map = new HashMap<>();

        while (end < fruits.length) {
            int right = fruits[end];
            map.put(right, map.getOrDefault(right, 0) + 1);

            while (map.size() > k) {
                int left = fruits[start];
                map.put(left, map.get(left) - 1);
                if (map.get(left) == 0) {
                    map.remove(left);
                }
                start++;
            }

            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }

}
