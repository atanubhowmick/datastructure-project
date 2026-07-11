package dev.atanu.ds.java.dp.mitm;

import java.util.*;

/**
 * https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/
 *
 * This is a special pattern - 'Meet In the Middle' similar to Knapsack Problem with Sequence - 8
 * But it can contain negative numbers.
 *
 */
public class ClosestSubsequenceSum {

    public int minAbsDifference(int[] nums, int goal) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        int n = nums.length;
        generate(nums, 0, n / 2, 0, left);
        generate(nums, n / 2, n, 0, right);

        Collections.sort(right);

        int ans = Integer.MAX_VALUE;

        for (int leftSum : left) {
            int target = goal - leftSum;
            int idx = Collections.binarySearch(right, target);

            if (idx < 0) {
                idx = -idx - 1;
            }
            if (idx < right.size()) {
                ans = Math.min(ans, Math.abs(goal - (leftSum + right.get(idx))));
            }
            if (idx > 0) {
                ans = Math.min(ans, Math.abs(goal - (leftSum + right.get(idx - 1))));
            }
        }

        return ans;
    }

    private void generate(int[] nums, int start, int end, int sum, List<Integer> list) {
        if (start == end) {
            list.add(sum);
            return;
        }

        generate(nums, start + 1, end, sum, list);
        generate(nums, start + 1, end, sum + nums[start], list);
    }
}
