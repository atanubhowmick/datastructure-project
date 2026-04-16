package dev.atanu.ds.java.stack;

import java.util.*;

/**
 * 901. Online Stock Span
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


    /**
     * 853. Car Fleet
     * Can be solved without using Stack
     * https://leetcode.com/problems/car-fleet/
     *
     * @param target
     * @param position
     * @param speed
     * @return
     */
    public int carFleet(int target, int[] position, int[] speed) {

        // TreeMap contains current position and time to reach destination
        // The tree would be sorted based on the position (Key)
        Map<Integer, Double> map = new TreeMap<>(Collections.reverseOrder());

        // Insert into tree with calculated time
        for (int i = 0; i < position.length; ++i) {
            map.put(position[i], (double)(target - position[i]) / speed[i]);
        }

        int result = 0;
        double frontCarTime = 0.0;

        // If current car's time is greate than front/near car, it will create a new fleet.
        // If current car's time is snaller than front/near car, it will join the fleet.
        for (double currentCarTime : map.values()) {
            if (currentCarTime > frontCarTime) {
                frontCarTime = currentCarTime;
                result++;
            }
        }
        return result;
    }
}
