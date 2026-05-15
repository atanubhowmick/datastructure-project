package dev.atanu.ds.java.sliding.window;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

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
     * https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/
     *
     * @param blocks
     * @param k
     * @return
     */
    public int minimumRecolors(String blocks, int k) {
        int start = 0, end = 0;
        int white = 0;

        while(end < k) {
            if(blocks.charAt(end) == 'W') {
                white++;
            }
            end++;
        }

        int minSwap = white;

        while(end < blocks.length()) {
            if(blocks.charAt(end) == 'W') {
                white++;
            }
            int len = end - start + 1;
            if(len > k) {
                if(blocks.charAt(start) == 'W') {
                    white--;
                }
                start++;
            }

            minSwap = Math.min(minSwap, white);
            end++;
        }

        return minSwap;
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


    /**
     * https://leetcode.com/problems/sliding-window-maximum/
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n-k+1];

        // To store index
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            // remove indices that are out of bound
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.poll();
            }

            // remove indices whose corresponding values are less than nums[i]
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // add nums[i]
            deque.offer(i);

            // add to result
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peek()];
            }
        }
        return result;
    }


    /**
     * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int sum = 0;

        for(int i = 0; i < k; i++){
            sum += cardPoints[i];
        }

        int max = sum;
        for(int i = 0; i < k; i++) {
            sum = sum - cardPoints[k - i - 1] + cardPoints[n - i - 1];
            max = Math.max(max, sum);
        }
        return max;
    }


    /**
     * https://leetcode.com/problems/sliding-window-median/
     * [HARD Problem]
     * Similar to https://leetcode.com/problems/find-median-from-data-stream/
     *
     * @param nums
     * @param k
     * @return
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        int start = 0;

        TreeSet<Integer> low = new TreeSet<>((a, b) -> (nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b));
        TreeSet<Integer> high = new TreeSet<>((a, b) -> (nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b));

        for (int i = 0; i < nums.length; i++) {
            low.add(i);
            high.add(low.pollLast());

            if(high.size() > low.size()) {
                low.add(high.pollFirst());
            }
            if (low.size() + high.size() == k) {
                result[start] = (double) ((low.size() == high.size())
                        ? (nums[low.last()]/2.0 + nums[high.first()] / 2.0)
                        : nums[low.last()]);
                if (!low.remove(start)) {
                    high.remove(start);
                }
                start++;
            }
        }
        return result;
    }


    /**
     * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        return null;
    }
}
