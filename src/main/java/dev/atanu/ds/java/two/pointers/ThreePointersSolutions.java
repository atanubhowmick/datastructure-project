package dev.atanu.ds.java.two.pointers;

public class ThreePointersSolutions {


    /**
     * https://leetcode.com/problems/sort-colors/
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int start = 0, mid = 0, end = nums.length - 1;
        while(mid <= end) {
            if(nums[mid] == 0) {
                swap(nums, start, mid);
                start++;
                mid++;
            } else if(nums[mid] == 2) {
                swap(nums, mid, end);
                end--;
            } else {
                mid++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
