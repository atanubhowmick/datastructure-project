package dev.atanu.ds.java.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/online-stock-span/
 *
 * @author Atanu Bhowmick
 */
public class StockSpanner {

    private final Stack<Integer> stack;
    private final Map<Integer, Integer> map;

    public StockSpanner() {
        stack = new Stack<>();
        map = new HashMap<>();
    }

    public int next(int price) {
        int span = 1;
        while(!stack.empty() && price >= stack.peek()) {
            int prev = stack.pop();
            span += map.getOrDefault(prev, 0);
        }

        stack.push(price);
        map.put(price, span);
        return span;
    }
}
