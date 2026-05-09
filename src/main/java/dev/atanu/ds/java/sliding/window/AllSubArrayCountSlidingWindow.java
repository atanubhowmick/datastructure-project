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
        int left = 0, right = 0;
        int count = 0, res = 0;

        while (right < nums.length) {
            if (nums[right] % 2 == 1) {
                k--;
                count = 0;
            }

            while (k == 0) {
                if (nums[left] % 2 == 1) {
                    k++;
                }
                count++;
                left++;
            }
            res += count;
            right++;
        }
        return res;
    }

    public int numberOfSubarrays1(int[] nums, int k) {
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
