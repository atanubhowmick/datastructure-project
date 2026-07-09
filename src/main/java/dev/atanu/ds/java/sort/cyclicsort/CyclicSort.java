package dev.atanu.ds.java.sort.cyclicsort;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic model for Cyclic sort
 *
 * for(i -> 0 to n-1)
 *      while(valid && current value not correct position)
 *          swap values to correct position
 *
 * for(i -> 0 to n-1)
 *    if(mismatch condition)
 *      problem specific logic
 */
public class CyclicSort {

    public static void main(String[] args) {
        int[] nums = {3, 0, 1};
        CyclicSort cyclicSort = new CyclicSort();
        System.out.println(cyclicSort.missingNumber(nums));
    }

    /**
     * https://leetcode.com/problems/missing-number/
     */
    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return 0;
    }

    /**
     * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }

        return result;
    }

    /**
     * https://leetcode.com/problems/first-missing-positive/
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }


    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
