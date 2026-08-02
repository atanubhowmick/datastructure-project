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

            while (oddCount == k) {
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
     * @param nums - nums
     * @return count
     */
    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int start = 0, end = 0, result = 0;
        int distinct = set.size();

        Map<Integer, Integer> map = new HashMap<>();

        while (end < nums.length) {
            int num = nums[end];
            map.put(num, map.getOrDefault(num, 0) + 1);
            while (map.size() == distinct) {

                // As all distinct elements are here.
                // Rest of the array will also satisfy with duplicate elements
                result += nums.length - end;

                map.put(nums[start], map.get(nums[start]) - 1);
                if (map.get(nums[start]) == 0) {
                    map.remove(nums[start]);
                }
                start++;
            }
            end++;
        }
        return result;
    }


    /**
     * 992. Subarrays with K Different Integers
     * <p>
     * https://leetcode.com/problems/subarrays-with-k-different-integers/
     *
     * @param nums - nums
     * @param k    - k
     * @return count
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    private int atMostK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int start = 0, end = 0, result = 0;

        while (end < nums.length) {
            int num = nums[end];
            if (map.getOrDefault(num, 0) == 0) {
                k--;
            }
            map.put(num, map.getOrDefault(num, 0) + 1);

            while (k < 0) {
                map.put(nums[start], map.get(nums[start]) - 1);
                if (map.get(nums[start]) == 0) {
                    map.remove(nums[start]); // redundant
                    k++;
                }
                start++;
            }
            // If the subarray is [1, 2, 1], left subarrays are already taken, only add right subarrays which is same as length.
            // Subarray = [1] -> left subarrays = [1]
            // Subarray = [1, 2] -> left subarrays = [1], right subarray = [1, 2], [2]
            // Subarray = [1, 2, 1] -> left subarrays = [1], [1, 2], [2], right subarray = [1, 2, 1], [2, 1], [1]
            result += (end - start + 1);
            end++;
        }

        return result;
    }


    /**
     * https://leetcode.com/problems/binary-subarrays-with-sum/
     *
     * @param nums - nums
     * @param goal - goal
     * @return - int
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        // Better to use Prefix Sum and HashMap approach
        return atMostSubarraysWithSum(nums, goal) - atMostSubarraysWithSum(nums, goal - 1);
    }

    private int atMostSubarraysWithSum(int[] nums, int goal) {
        int start = 0, end = 0;
        int totalCount = 0;

        while (end < nums.length) {
            goal -= nums[end];
            while(goal < 0 && start <= end) {
                goal += nums[start];
                start++;
            }
            totalCount += end - start + 1;
            end++;
        }
        return totalCount;
    }


    /**
     * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
     *
     * @param s - s
     * @return - length
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int left = 0, right = 0, k = 2;
        int res = 0;

        // HashMp that hold the num and it's occurrence count
        Map<Character, Integer> map = new HashMap<>();

        while (right < s.length()) {
            char last = s.charAt(right);
            map.put(last, map.getOrDefault(last, 0) + 1);

            while (map.size() > k) {
                char first = s.charAt(left);
                map.put(first, map.get(first) - 1);
                if (map.get(first) == 0) {
                    map.remove(first);
                }
                left++;
            }

            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
     *
     * @param s - s
     * @param k - k
     * @return length
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0, right = 0;
        int res = 0;

        // HashMp that hold the num and it's occurrence count
        Map<Character, Integer> map = new HashMap<>();

        while (right < s.length()) {
            char last = s.charAt(right);
            map.put(last, map.getOrDefault(last, 0) + 1);

            while (map.size() > k) {
                char first = s.charAt(left);
                map.put(first, map.get(first) - 1);
                if (map.get(first) == 0) {
                    map.remove(first);
                }
                left++;
            }

            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    /**
     * 713. Subarray Product Less Than K
     * <p>
     * https://leetcode.com/problems/subarray-product-less-than-k/
     *
     * @param nums - nums
     * @param k - k
     * @return int
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int start = 0, end = 0;
        int totalCount = 0, product = 1;

        // Constraint: 1 <= nums[i] <= 1000
        if(k <= 1) {
            return 0;
        }

        // As this is less than k, we do not need [atMost(k) - atMost(k-1)]. It's only for == k
        while (end < nums.length) {
            product *= nums[end];
            while(product >= k && start <= end) {
                product /= nums[start];
                start++;
            }
            totalCount += end - start + 1;
            end++;
        }
        return totalCount;
    }


    /**
     * Take the right side instead of left.
     * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
     *
     * @param s - s
     * @return - int
     */
    public int numberOfSubstrings(String s) {
        int[] count = new int[3];
        int n = s.length();
        int start = 0, end = 0;
        int result = 0;

        while (end < n) {
            count[s.charAt(end) - 'a']++;

            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                // Right side will always have all 3 chars
                result += n - end;
                count[s.charAt(start) - 'a']--;
                start++;
            }
            end++;
        }

        return result;
    }

}
