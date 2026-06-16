package dev.atanu.ds.java.stack;

import java.util.Stack;

public class StackSolution {

    /**
     * https://leetcode.com/problems/longest-valid-parentheses/
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }

        if (stack.isEmpty()) {
            return n;
        }

        // Now the stack contain the indices of characters which cannot be matched

        int endIndex = n - 1;
        int maxLen = 0;
        while (!stack.isEmpty()) {
            int startIndex = stack.pop();
            maxLen = Math.max(maxLen, endIndex - startIndex);
            endIndex = startIndex;
        }

        // check the last index as well if it is longest
        maxLen = Math.max(maxLen, endIndex);

        return maxLen;
    }
}
