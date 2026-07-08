package dev.atanu.ds.java.dp.houserobber;

/**
 * https://leetcode.com/problems/house-robber-ii/
 *
 * @author Atanu Bhowmick
 */
public class HouseRobber2 {

    public static void main(String[] args) {
        HouseRobber2 houseRobber = new HouseRobber2();
        int[] nums = new int[] {2, 7, 9, 3, 1};
        System.out.println(houseRobber.rob(nums));
    }

    /**
     * Using recursion
     *
     * @param nums - represents the amount present in the array
     * @return max rob amount
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        return Math.max(rob(nums, 0, n - 1), rob(nums, 1, n));
    }

    public int rob(int[] nums, int start, int end)  {
        int preMax = 0, curMax = 0;
        for(int i = start; i < end; i++){
            int temp = curMax;
            curMax = Math.max(preMax + nums[i], curMax);
            preMax = temp;
        }
        return curMax;
    }


}
