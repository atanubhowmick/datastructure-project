package dev.atanu.ds.java.sliding.window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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


    /**
     * https://leetcode.com/problems/count-complete-subarrays-in-an-array/
     *
     * @param nums
     * @return
     */
    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num: nums) {
            set.add(num);
        }

        int start = 0, end = 0, result = 0;
        int distinct = set.size();

        Map<Integer, Integer> map = new HashMap<>();

        while(end < nums.length) {
            int num = nums[end];
            map.put(num, map.getOrDefault(num, 0) + 1);
            while(map.size() == distinct) {

                // As all distinct elements are here.
                // Rest of the array will also satisfy with duplicate elements
                result += nums.length - end;

                map.put(nums[start], map.get(nums[start]) - 1);
                if(map.get(nums[start]) == 0) {
                    map.remove(nums[start]);
                }
                start++;
            }
            end++;
        }
        return result;
    }


}
