package dev.atanu.ds.java.stack;

import java.util.Arrays;
import java.util.Stack;

public class SubArrayMonotonicStack {


    /**
     * https://leetcode.com/problems/sum-of-subarray-minimums/
     *
     * @param arr - arr
     * @return sum
     */
    public int sumSubarrayMins(int[] arr) {
        int length = arr.length;
        int[] left = new int[length];
        int[] right = new int[length];

        Arrays.fill(left, -1);
        Arrays.fill(right, length);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < length; ++i) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                left[i] = stack.peek();
            }
            stack.push(i);
        }

        stack.clear();

        for (int i = length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                right[i] = stack.peek();
            }
            stack.push(i);
        }

        int mod = (int) 1e9 + 7;
        long result = 0;

        for (int i = 0; i < length; ++i) {
            result = (result + (long) (i - left[i]) * (right[i] - i) * arr[i]) % mod;
        }

        return (int) result;
    }


    /**
     * https://leetcode.com/problems/sum-of-subarray-ranges/
     *
     * @param nums
     * @return
     */
    public long subArrayRanges(int[] nums) {
        return 0;
    }
}
