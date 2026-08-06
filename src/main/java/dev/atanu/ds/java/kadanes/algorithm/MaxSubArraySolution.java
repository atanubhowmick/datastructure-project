package dev.atanu.ds.java.kadanes.algorithm;

public class MaxSubArraySolution {

    /**
     * https://leetcode.com/problems/maximum-subarray/
     *
     * @param nums - nums
     * @return max
     */
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int maxSum = nums[0];
        for(int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], sum + nums[i]);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    /**
    * https://leetcode.com/problems/maximum-sum-circular-subarray/
    */
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0;
        for (int n : nums) {
            total += n;
        }

        int currMin = nums[0];
        int min = nums[0];

        int currMax = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Kadane's for maximum subarray
            currMin = Math.min(nums[i], currMin + nums[i]);
            min = Math.min(min, currMin);

            // Kadane's for minimum subarray
            currMax = Math.max(nums[i], currMax + nums[i]);
            max = Math.max(max, currMax);
        }

        if (max < 0) {
            return max;
        }
        return Math.max(max, total - min);
    }

    /**
     * https://leetcode.com/problems/maximum-product-subarray/
     */
    public int maxProduct(int[] nums) {
        int result = Integer.MIN_VALUE;
        int prefix = 0;
        int suffix = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // Reset to 1 when prefix or suffix hits 0
            prefix = (prefix == 0 ? 1 : prefix) * nums[i];
            suffix = (suffix == 0 ? 1 : suffix) * nums[n - i - 1];

            result = Math.max(result, Math.max(prefix, suffix));
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/
     */
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int oneDelete = 0;
        int noDelete = arr[0];
        int maxSum = arr[0];

        for (int i = 1; i < n; i++) {
            /*For oneDelete, either delete arr[i] i.e take previous noDelete
            or use the current element and explan subarray*/
            oneDelete = Math.max(noDelete, oneDelete + arr[i]);

            //This is existing Kadane's
            noDelete = Math.max(arr[i], noDelete + arr[i]);
            maxSum = Math.max(maxSum, Math.max(oneDelete, noDelete));
        }
        return maxSum;
    }

    /**
     * https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/
     */
    public int maxAbsoluteSum(int[] nums) {
        int currMax = nums[0];
        int max = nums[0];
        int currMin = nums[0];
        int min = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currMax = Math.max(nums[i], currMax + nums[i]);
            currMin = Math.min(nums[i], currMin + nums[i]);

            max = Math.max(max, currMax);
            min = Math.min(min, currMin);
        }

        return Math.abs(max) > Math.abs(min) ? max : Math.abs(min);
    }
}
