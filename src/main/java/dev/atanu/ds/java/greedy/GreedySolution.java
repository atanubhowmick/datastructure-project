package dev.atanu.ds.java.greedy;

import java.util.Map;
import java.util.TreeMap;

public class GreedySolution {

    public static void main(String[] args) {
        GreedySolution solution = new GreedySolution();
        System.out.println(solution.canJump(new int[] {2, 3, 1, 2, 0, 2, 1}));
    }

    /**
     * https://leetcode.com/problems/jump-game/
     *
     * @param nums - nums
     * @return boolean
     */
    public boolean canJump(int[] nums) {
        int reachableIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reachableIdx) {
                return false;
            }
            // Greedy algorithm. Take the max jump from the position.
            reachableIdx = Math.max(reachableIdx, i + nums[i]);
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/jump-game-ii/
     *
     * @param nums - nums
     * @return int
     */
    public int jump(int[] nums) {
        int jumps = 0, curEnd = 0, reachableIdx = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            reachableIdx = Math.max(reachableIdx, i + nums[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = reachableIdx;
            }
        }
        return jumps;
    }


    /**
     * https://leetcode.com/problems/gas-station/
     *
     * @param gas - gas
     * @param cost - cost
     * @return position
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int totalGasAmount = 0;
        int gasAmount = 0;
        int start = 0;

        for(int i = 0; i < n; i++) {
            totalGasAmount += (gas[i] - cost[i]);
            gasAmount += (gas[i] - cost[i]);
            if(gasAmount < 0) {
                gasAmount = 0;
                start = i + 1;
            }
        }
        return (totalGasAmount < 0) ? -1 : start;
    }


    /**
     * https://leetcode.com/problems/hand-of-straights/
     *
     * @param hand - hand
     * @param groupSize - groupSize
     * @return boolean
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Map<Integer, Integer> freq = new TreeMap<>();
        for (int card : hand) {
            freq.put(card, freq.getOrDefault(card, 0) + 1);
        }

        for (int key : freq.keySet()) {
            if (freq.get(key) > 0) {
                for (int i = groupSize - 1; i >= 0; --i) {
                    if (freq.getOrDefault(key + i, 0) < freq.get(key)) {
                        return false;
                    }
                    freq.put(key + i, freq.get(key + i) - freq.get(key));
                }
            }
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/hand-of-straights/
     *
     * @param hand - hand
     * @param groupSize - groupSize
     * @return boolean
     */
    public boolean isNStraightHand1(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int curr : hand) {
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }

        while (!map.isEmpty()) {
            // This firstKey method is important
            int start = map.firstKey();
            for (int i = start; i < start + groupSize; i++) {
                if (!map.containsKey(i)) {
                    return false;
                }

                map.put(i, map.get(i) - 1);
                if (map.get(i) == 0) {
                    map.remove(i);
                }
            }
        }
        return true;
    }
}
