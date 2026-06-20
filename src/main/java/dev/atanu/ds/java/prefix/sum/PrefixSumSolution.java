package dev.atanu.ds.java.prefix.sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PrefixSumSolution {

    public static void main(String[] args) {
        PrefixSumSolution solution = new PrefixSumSolution();
        int[] arr = new int[] {10, 4, 8, 3};
        System.out.println(Arrays.toString(solution.leftRightDifference(arr)));
    }

    /**
     * https://leetcode.com/problems/left-and-right-sum-differences/
     *
     * @param nums - array
     * @return diff
     */
    public int[] leftRightDifference(int[] nums) {
        int sum = 0;
        for(int num: nums) {
            sum += num;
        }

        int n = nums.length, leftSum = 0;
        int[] result = new int[n];
        for(int i = 0; i < n; i++) {
            sum -= nums[i];
            result[i] = Math.abs(sum - leftSum);
            // or
            // result[i] = Math.abs(sum - nums[i] - 2 * leftSum);
            leftSum += nums[i];
        }

        return result;
    }


    /**
     * https://leetcode.com/problems/find-pivot-index/
     *
     * @param nums - array
     * @return pivot index
     */
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }

        int leftSum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum -= nums[i];
            if(leftSum == sum) {
                return i;
            }
            leftSum += nums[i];
        }

        return -1;
    }


    /**
     * https://leetcode.com/problems/minimum-value-to-get-positive-step-by-step-sum/
     *
     * @param nums - array
     * @return min start
     */
    public int minStartValue(int[] nums) {
        int min = nums[0];
        for(int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
            min = Math.min(min, nums[i]);
        }

        return min <= 0 ? 1 - min : 1;
    }


    /**
     * https://leetcode.com/problems/find-the-middle-index-in-array/
     *
     * @param nums - array
     * @return middle index
     */
    public int findMiddleIndex(int[] nums) {
        int totalSum = 0, leftSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        for (int i = 0; i < nums.length; i++) {
            if (leftSum * 2 == totalSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }


    /**
     * https://leetcode.com/problems/find-the-pivot-integer/
     *
     * @param n - n
     * @return pivot integer
     */
    public int pivotInteger(int n) {
        if(n == 1) {
            return 1;
        }

        int start = 1, end = n;
        int leftSum = 0, rightSum = 0;
        while(start < end) {
            if(leftSum <= rightSum) {
                leftSum += start;
                start++;
            } else {
                rightSum += end;
                end--;
            }
        }

        return leftSum == rightSum ? end : -1;
    }


    /**
     * https://leetcode.com/problems/equal-score-substrings/
     *
     * @param s - s
     * @return boolean
     */
    public boolean scoreBalance(String s) {
        int sum = 0;
        for (char ch : s.toCharArray()) {
            sum += (ch - 'a') + 1;
        }
        int leftSum = 0;
        for (char ch : s.toCharArray()) {
            leftSum += ch - 'a' + 1;
            if (2 * leftSum == sum) {
                return true;
            }
        }
        return false;
    }


    /**
     * https://leetcode.com/problems/smallest-stable-index-i/
     *
     * @param nums - array
     * @param k - k
     * @return stable index
     */
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int leftMax = nums[0];

        Stack<Integer> stack = new Stack<>();

        for(int i = n - 1; i >= 0; i--) {
            if(stack.isEmpty()) {
                stack.push(nums[i]);
            } else {
                if(nums[i] <= stack.peek()) {
                    stack.push(nums[i]);
                }
            }
        }

        for(int i = 0; i < n; i++) {
            leftMax = Math.max(leftMax, nums[i]);
            int rightMin = stack.peek();
            if(Math.abs(leftMax-rightMin) <= k) {
                return i;
            }
            if(nums[i] == rightMin) {
                stack.pop();
            }
        }
        return -1;
    }


    /**
     * https://leetcode.com/problems/contiguous-array/
     *
     * @param nums - array
     * @return max len
     */
    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0, maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return maxLen;
    }


    /**
     * https://leetcode.com/problems/subarray-sum-equals-k/
     *
     * @param nums - nums
     * @param k - k
     * @return count
     */
    public int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            System.out.println(preSum);
            System.out.println("Sum: " + sum);
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }

        return result;
    }
}
