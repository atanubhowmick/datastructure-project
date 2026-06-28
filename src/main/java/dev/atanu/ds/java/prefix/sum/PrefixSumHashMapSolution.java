package dev.atanu.ds.java.prefix.sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/continuous-subarray-sum/solutions/5276981/prefix-sum-hashmap-patterns-7-problems-b-6794/
 * <p>
 * Pattern: Prefix sum with HashMap
 */
public class PrefixSumHashMapSolution {

    public static void main(String[] args) {
        PrefixSumHashMapSolution solution = new PrefixSumHashMapSolution();
        int[] arr = new int[] {1, 2, 3};
        System.out.println(solution.subarraySum(arr, 3));
    }

    /**
     * https://leetcode.com/problems/subarray-sum-equals-k/
     *
     * @param nums - nums
     * @param k - k
     * @return count
     */
    public int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // Note here map key is (sum - k) not the (k - sum)
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }

        return result;
    }


    /**
     * https://leetcode.com/problems/binary-subarrays-with-sum/
     *
     * @param nums - nums
     * @param goal - goal
     * @return count
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - goal)) {
                result += preSum.get(sum - goal);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }

        return result;
    }


    /**
     * https://leetcode.com/problems/continuous-subarray-sum/
     *
     * @param nums - nums
     * @param k - k
     * @return count
     */
    public int subarraysDivByK(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for(int i = 0; i < nums.length; i++) {
            sum = (sum + nums[i]) % k;

            // To handle negative number
            if (sum < 0) {
                sum = sum + k;
            }

            if(preSum.containsKey(sum)) {
                result += preSum.get(sum);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return result;
    }


    /**
     * https://leetcode.com/problems/contiguous-array/
     *
     * @param nums - array
     * @return max len
     */
    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0, maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return maxLen;
    }


    /**
     * https://leetcode.com/problems/continuous-subarray-sum/
     * https://leetcode.com/problems/continuous-subarray-sum/solutions/5276981/prefix-sum-hashmap-patterns-7-problems-b-6794/
     * @param nums - nums
     * @param k - k
     * @return boolean
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int prefixMod = 0;
        HashMap<Integer, Integer> modSeen = new HashMap<>();
        modSeen.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            prefixMod = (prefixMod + nums[i]) % k;

            if (modSeen.containsKey(prefixMod)) {
                // ensures that the size of subarray is at least 2
                if (i - modSeen.get(prefixMod) > 1) {
                    return true;
                }
            } else {
                // mark the value of prefixMod with the current index.
                modSeen.put(prefixMod, i);
            }
        }
        return false;
    }


    /**
     * https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
     *
     * @param nums - nums
     * @param x - x
     * @return int
     */
    public int minOperations(int[] nums, int x) {
        int target = -x;
        for (int num : nums) {
            target += num;
        }

        if (target == 0) {
            // since all elements are positive, we have to take all of them
            return nums.length;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; ++i) {

            sum += nums[i];
            if (map.containsKey(sum - target)) {
                res = Math.max(res, i - map.get(sum - target));
            }

            // no need to check containsKey since sum is unique
            map.put(sum, i);
        }

        return res == Integer.MIN_VALUE ? -1 : nums.length - res;
    }
}
