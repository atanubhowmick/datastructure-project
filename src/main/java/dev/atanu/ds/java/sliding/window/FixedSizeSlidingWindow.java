package dev.atanu.ds.java.sliding.window;

/**
 * There are four categories in Sliding Window pattern
 * <p>
 * A. Fixed Size Window
 * B. Variable Size Window — Longest/Maximum
 * C. Variable Size Window — Smallest/Minimum
 * D. Sliding Window + HashMap/Frequency Count
 */
public class FixedSizeSlidingWindow {

    public static void main(String[] args) {
        FixedSizeSlidingWindow solution = new FixedSizeSlidingWindow();
        int[] arr = new int[] {5, 7, -4, 3, 12, -6, 10};
        System.out.println(solution.findMaxAverage(arr, 5));
    }


    /**
     * https://leetcode.com/problems/maximum-average-subarray-i/
     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        int left = 0, right = 0;
        long sum = 0;
        while(right < k) {
            sum += nums[right];
            right++;
        }
        long maxSum = sum;
        while(right < nums.length) {
            sum = sum - nums[left] + nums[right];
            maxSum = Math.max(sum, maxSum);
            left++;
            right++;
        }
        return (double) maxSum / k;
    }


    /**
     * https://leetcode.com/problems/defuse-the-bomb/
     *
     * @param code
     * @param k
     * @return
     */
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] res = new int[n];

        if (k == 0) {
            return res;
        }

        int sum = 0;
        int left = 1, right = k;

        if (k < 0) {
            // Considering the first element
            left = n + k;
            right = n - 1;
        }

        for (int i = left; i <= right; i++) {
            sum += code[i % n];
        }

        // Sliding window
        for (int i = 0; i < n; i++) {
            res[i] = sum;
            sum -= code[left % n];
            left++;
            right++;
            sum += code[right % n];
        }
        return res;
    }

}
