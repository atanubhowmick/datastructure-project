package dev.atanu.ds.java.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Step 1 — Identify the Relationship
 * <br> Am I looking for the next element or the previous element?
 * <>br
 * | Problem Type     | Direction                 |
 * | ---------------- | ------------------------- |
 * | Next element     | Traverse  Left → Right    |
 * | Previous element | Traverse  Right → Left    |
 * <p>
 *
 * Step 2 — Identify Greater or Smaller
 * <br> Am I searching for a greater element or a smaller element?
 * <br>
 * | Target           | Stack Type                     |
 * | ---------------- | ------------------------------ |
 * | Next Greater     | **Monotonic Decreasing Stack** |
 * | Next Smaller     | **Monotonic Increasing Stack** |
 * | Previous Greater | **Monotonic Decreasing Stack** |
 * | Previous Smaller | **Monotonic Increasing Stack** |
 * <p>
 *
 * Step 3 — Decide What the Stack Stores
 * Stack stores: indices
 * Value used: nums[index]
 * <p>
 *
 * Step 4 — Write the Core Loop Pattern
 * for each index i: {
 *     while stack not empty AND condition satisfied: {
 *         idx = stack.pop()
 *         resolve result for idx
 *     }
 *     stack.push(i)
 * }
 * <p>
 *
 *
 * Step 5 — Initialize Default Results
 * result[i] = -1 (if needed)
 *<p>
 *
 *
 * Step 6 — Handle Circular Arrays (if required)
 * for i = 0 → 2n - 1 {
 *     index = i % n;
 *     ...
 *     push to stack only when i < n
 * }
 * <p>
 *
 * Clarification - Why Storing Index is Better Than Storing the Element?
 * Reason 1 — Duplicates Exist
 * Reason 2 — We Need the Position to Write Result
 * Reason 3 — Accessing Values Is Still Easy using index
 * <p>
 *
 * Most Important patterns:
 * - Next Greater → Decreasing Stack → Left to Right
 * - Next Smaller → Increasing Stack → Left to Right
 * - Previous Greater → Decreasing Stack → Right to Left
 * - Previous Smaller → Increasing Stack → Right to Left
 */
public class MonotonicStack {

    /**
     * 496. Next Greater Element I
     * <p>
     * https://leetcode.com/problems/next-greater-element-i/description
     *
     * @param nums1
     * @param nums2
     * @return array
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        int m = nums1.length;
        int[] result = new int[m];

        for(int num: nums2) {
            while(!stack.empty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }

        for(int i = 0; i < m; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }

        return result;
    }


    /**
     * 503. Next Greater Element II (Circular Traversing)
     * <p>
     * https://leetcode.com/problems/next-greater-element-ii/
     *
     * @param nums array
     * @return array
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;

        int[] result = new int[n];
        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                result[stack.pop()] = num;
            }

            if (i < n) {
                stack.push(i);
            }
        }
        return result;
    }
}
