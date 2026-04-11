package dev.atanu.ds.java.sliding.window;

public class AllSubArrayCountSlidingWindow {

    public static void main(String[] args) {
        AllSubArrayCountSlidingWindow solution = new AllSubArrayCountSlidingWindow();
        System.out.println(solution.numberOfSubarrays1(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));
    }

    /**
     * https://leetcode.com/problems/count-number-of-nice-subarrays/
     *
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int start = 0, end = 0;
        int count = 0, res = 0;

        while (end < nums.length) {
            if (nums[end] % 2 == 1) {
                k--;
                count = 0;
            }

            while (k == 0) {
                if (nums[start] % 2 == 1) {
                    k++;
                }
                count++;
                start++;
            }
            res += count;
            end++;
        }
        return res;
    }

    public int numberOfSubarrays1(int[] nums, int k) {
        int start = 0, end = 0;
        int oddCount = 0, count = 0, res = 0;

        while (end < nums.length) {
            if (nums[end] % 2 == 1) {
                oddCount++;
                count = 0;
            }

            while(oddCount == k) {
                count++;
                if (nums[start] % 2 == 1) {
                    oddCount--;
                }
                start++;
            }
            res += count;
            end++;
        }
        return res;
    }
}
