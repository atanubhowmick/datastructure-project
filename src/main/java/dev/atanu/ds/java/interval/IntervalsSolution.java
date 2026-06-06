package dev.atanu.ds.java.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class IntervalsSolution {

    public static void main(String[] args) {
        IntervalsSolution solution = new IntervalsSolution();
        int[][] intervals = new int[][]{{1,3}, {2,6}, {8,10},{15,18}};
        System.out.println(Arrays.deepToString(solution.merge(intervals)));
    }

    /**
     * https://leetcode.com/problems/merge-intervals/
     *
     * @param intervals - intervals
     * @return merged intervals
     */
    public int[][] merge(int[][] intervals) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (a1, a2) -> Integer.compare(a1[0], a2[0]));

        for(int[] interval : intervals) {
            queue.offer(interval);
        }

        List<int[]> result = new ArrayList<>();
        int[] prevInterval = queue.poll();
        result.add(prevInterval);

        while(!queue.isEmpty()) {
            int[] interval = queue.poll();
            if(interval[0] <= prevInterval[1]) {
                prevInterval[1] = Math.max(prevInterval[1], interval[1]);
            } else {
                result.add(interval);
                prevInterval = interval;
            }
        }
        return result.toArray(new int[result.size()][]);
    }


    /**
     * https://leetcode.com/problems/insert-interval/
     *
     * @param intervals - intervals
     * @param newInterval - new Interval
     * @return inserted intervals
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0 , n = intervals.length;

        // Add the intervals before the newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Merge all overlapping intervals to one considering newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        // Add the merged interval
        result.add(newInterval);

        // Add the remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }
}
