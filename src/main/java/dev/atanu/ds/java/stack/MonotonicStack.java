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
 * <ul>
 *   <li> Next Greater → Decreasing Stack → Left to Right
 *   <li> Next Smaller → Increasing Stack → Left to Right
 *   <li> Previous Greater → Decreasing Stack → Right to Left
 *   <li> Previous Smaller → Increasing Stack → Right to Left
 * </ul>
 */
public class MonotonicStack {

    public static void main(String[] args) {
        MonotonicStack solution = new MonotonicStack();
        int[] nums1 = new int[]{3, 2, 6, 8};
        int[] nums2 = new int[]{6, 3, 7, 12, 8, 4, 2, 5};

        System.out.println(Arrays.toString(solution.nextGreaterElement(nums1, nums2)));
        System.out.println(Arrays.toString(solution.nextSmallerElement(nums1, nums2)));
        System.out.println(Arrays.toString(solution.previousGreaterElement(nums1, nums2)));
        System.out.println(Arrays.toString(solution.previousSmallerElement(nums1, nums2)));
    }

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
        int n = nums2.length;

        int[] result = new int[m];

        for(int i = 0; i < n; i++) {
            int current = nums2[i];
            while(!stack.empty() && current > nums2[stack.peek()]) {
                map.put(nums2[stack.pop()], current);
            }
            stack.push(i);
        }

        for(int j = 0; j < m; j++) {
            result[j] = map.getOrDefault(nums1[j], -1);
        }

        return result;
    }


    /**
     * Next smaller element. Follow the template.
     *
     * @param nums1
     * @param nums2
     * @return Array Result
     */
    public int[] nextSmallerElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        int m = nums1.length;
        int n = nums2.length;

        int[] result = new int[m];

        for(int i = 0; i < n; i++) {
            int current = nums2[i];
            while(!stack.empty() && current < nums2[stack.peek()]) {
                map.put(nums2[stack.pop()], current);
            }
            stack.push(i);
        }

        for(int j = 0; j < m; j++) {
            result[j] = map.getOrDefault(nums1[j], -1);
        }

        return result;
    }


    /**
     * Previous Greater Element
     *
     * @param nums1
     * @param nums2
     * @return Array result
     */
    public int[] previousGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        int m = nums1.length;
        int n = nums2.length;

        int[] result = new int[m];

        for(int i = n-1; i >= 0; i--) {
            int current = nums2[i];
            while(!stack.empty() && current > nums2[stack.peek()]) {
                map.put(nums2[stack.pop()], current);
            }
            stack.push(i);
        }

        for(int j = 0; j < m; j++) {
            result[j] = map.getOrDefault(nums1[j], -1);
        }

        return result;
    }

    /**
     * Previous Smaller Element
     *
     * @param nums1
     * @param nums2
     * @return Array Result
     */
    public int[] previousSmallerElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        int m = nums1.length;
        int n = nums2.length;

        int[] result = new int[m];

        for(int i = n-1; i >= 0; i--) {
            int current = nums2[i];
            while(!stack.empty() && current < nums2[stack.peek()]) {
                map.put(nums2[stack.pop()], current);
            }
            stack.push(i);
        }

        for(int j = 0; j < m; j++) {
            result[j] = map.getOrDefault(nums1[j], -1);
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


    /**
     * 2454. Next Greater Element IV
     * https://leetcode.com/problems/next-greater-element-iv
     *
     * @param nums
     * @return
     */
    public int[] secondGreaterElement(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        // Waiting for first greater
        Stack<Integer> stack1 = new Stack<>();

        // Waiting for second greater
        Stack<Integer> stack2 = new Stack<>();

        // To move the elements from stack1 to stack2 while maintaining order.
        // Othersise stack1.pop --> stack2.push() will reverse the order.
        Stack<Integer> temp = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!stack2.isEmpty() && nums[i] > nums[stack2.peek()]) {
                result[stack2.pop()] = nums[i];
            }

            /*
            Pop indices from stack1 because the current element nums[i] is their first greater element.
            Those indices must now move to stack2. However, if we directly push them into stack2
            while popping from stack1, the order becomes reversed. To maintain the order, we need a temp stack.
            */
            while (!stack1.isEmpty() && nums[i] > nums[stack1.peek()]) {
                temp.push(stack1.pop());
            }

            while (!temp.isEmpty()) {
                stack2.push(temp.pop());
                // temp tsack is completely cleared here. Hence created the tempstack outside of the loop.
            }

            stack1.push(i);
        }

        return result;
    }


    /**
     * 456. 132 Pattern
     * https://leetcode.com/problems/132-pattern
     * https://leetcode.com/problems/132-pattern/solutions/94071/single-pass-c-on-space-and-time-solution-ssns/
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        int s3 = Integer.MIN_VALUE;
        // We do not need s1, s2

        Stack<Integer> stack = new Stack<>();
        for(int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] < s3) {
                return true;
            } else {
                while(!stack.empty() && nums[i] > stack.peek()) {
                    s3 = stack.pop();
                }
            }
            stack.push(nums[i]);
        }
        return false;
    }


    /**
     * Monotonic Increasing stack
     * https://leetcode.com/problems/remove-k-digits
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        int len = num.length();

        if(k == len) {
            return "0";
        }

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < num.length(); i++) {
            char current = num.charAt(i);
            // Monotonic Increase Stack
            while(k > 0 && !stack.empty() && current < stack.peek()) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }

        // If all the K elements are not removed, remove from the top as the stack is increasing stack.
        while(k > 0) {
            stack.pop();
            k--;
        }

        StringBuffer sb = new StringBuffer();
        while(!stack.empty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

        //Remove all the 0 from the left
        while(sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }


    /**
     * 84. Largest Rectangle in Histogram
     * https://leetcode.com/problems/largest-rectangle-in-histogram
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Nearest Smaller to Left
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // Reuse Same Stack
        stack.clear();

        // Nearest Smaller to Right
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;
            maxArea = Math.max(maxArea, heights[i] * width);
        }

        return maxArea;
    }


    /**
     * 316. Remove Duplicate Letters
     * https://leetcode.com/problems/remove-duplicate-letters
     *
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i; // track the lastIndex of character presence
        }

        boolean[] seen = new boolean[26]; // keep track seen
        Stack<Integer> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i) - 'a';
            if (seen[curr]) {
                continue; // if seen continue as we need to pick one char only
            }
            while (!stack.isEmpty() && stack.peek() > curr && i < lastIndex[stack.peek()]) {
                seen[stack.pop()] = false; // pop out and mark unseen
            }
            stack.push(curr); // add into stack
            seen[curr] = true; // mark seen
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append((char) (stack.pop() + 'a'));
        }
        return sb.reverse().toString();
    }


    /**
     * 1081. Smallest Subsequence of Distinct Characters
     * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters
     *
     * @param s
     * @return
     */
    public String smallestSubsequence(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            // track the lastIndex of character presence
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        // keep track of seen characters
        boolean[] seen = new boolean[26];
        Stack<Integer> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i) - 'a';
            if (seen[curr]) {
                // if seen continue as we need to pick one char only
                continue;
            }

            // Monotonic increasing stack
            while (!stack.isEmpty() && curr < stack.peek() && i < lastIndex[stack.peek()]) {
                // pop out and mark unseen
                seen[stack.pop()] = false;
            }
            stack.push(curr); // add into stack
            seen[curr] = true; // mark seen
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append((char) (stack.pop() + 'a'));
        }

        return sb.reverse().toString();
    }


    /**
     * 3523. Make Array Non-decreasing
     * https://leetcode.com/problems/make-array-non-decreasing/
     *
     * @param nums
     * @return
     */
    public int maximumPossibleSize(int[] nums) {
        int prev = -1;
        int result = 0;

        for(int i = 0; i < nums.length; i++) {
            if(prev <= nums[i]) {
                result++;
                prev = nums[i];
            }
        }

        return result;
    }


    /**
     * 1944. Number of Visible People in a Queue
     * https://leetcode.com/problems/number-of-visible-people-in-a-queue
     *
     * @param heights
     * @return
     */
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            // Remove shorter people on the right side
            while (!stack.empty() && heights[i] > stack.peek()) {
                stack.pop();
                result[i] += 1;
            }

            // If stack is not empty then i th person can see one more persont,
            // who is taller than him
            if (!stack.empty()) {
                result[i] += 1;
            }

            // Add the person into stack
            stack.push(heights[i]);
        }

        return result;
    }
}
