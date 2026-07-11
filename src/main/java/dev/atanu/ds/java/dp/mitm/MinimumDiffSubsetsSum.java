package dev.atanu.ds.java.dp.mitm;

import java.util.*;

/**
 * https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/
 *
 * This is a special pattern - 'Meet In the Middle' similar to Knapsack Problem with Sequence - 7
 *  But it can contain negative numbers.
 */
public class MinimumDiffSubsetsSum {

    public int minimumDifference(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        Map<Integer, List<Integer>> left = new HashMap<>();
        Map<Integer, List<Integer>> right = new HashMap<>();

        int n = nums.length / 2;
        generate(nums, 0, n, 0, 0, left);
        generate(nums, n, nums.length, 0, 0, right);

        // Sort all right-side lists
        for (List<Integer> list : right.values()) {
            Collections.sort(list);
        }

        int ans = Integer.MAX_VALUE;

        for (int leftCount = 0; leftCount <= n; leftCount++) {
            List<Integer> leftSums = left.getOrDefault(leftCount, new ArrayList<>());
            List<Integer> rightSums = right.getOrDefault(n - leftCount, new ArrayList<>());

            for (int leftSum : leftSums) {
                int target = sum / 2 - leftSum;
                int idx = Collections.binarySearch(rightSums, target);

                if (idx < 0) {
                    idx = -idx - 1;
                }
                if (idx < rightSums.size()) {
                    int subsetSum = leftSum + rightSums.get(idx);
                    ans = Math.min(ans, Math.abs(sum - 2 * subsetSum));
                }
                if (idx > 0) {
                    int subsetSum = leftSum + rightSums.get(idx - 1);
                    ans = Math.min(ans, Math.abs(sum - 2 * subsetSum));
                }
            }
        }
        return ans;
    }

    private void generate(int[] nums, int start, int end, int count, int sum,
                          Map<Integer, List<Integer>> map) {
        if (start == end) {
            map.computeIfAbsent(count, k -> new ArrayList<>()).add(sum);
            return;
        }

        // Don't take current element
        generate(nums, start + 1, end, count, sum, map);

        // Take current element
        generate(nums, start + 1, end, count + 1, sum + nums[start], map);
    }
}
