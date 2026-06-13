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


    /**
     * https://leetcode.com/problems/non-overlapping-intervals/
     *
     * @param intervals - intervals
     * @return count
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;

        // Most important - sort based on end of the intervals.
        // This will result minimum number of element to remove.
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for(int[] interval : intervals) {
            queue.offer(interval);
        }

        int[] prev = queue.poll();
        int count = 1;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            if(prev[1] <= current[0]) {
                count++;
                prev = current;
            }
        }
        return n - count;
    }


    /**
     * https://leetcode.com/problems/determine-if-two-events-have-conflict/
     * @param event1 - event1
     * @param event2 - event2
     * @return boolean
     */
    public boolean haveConflict(String[] event1, String[] event2) {
        return event2[0].compareTo(event1[1]) <= 0
                && event1[0].compareTo(event2[1]) <= 0;
    }


    /**
     * https://leetcode.com/problems/remove-covered-intervals/
     *
     * @param intervals
     * @return
     */
    public int removeCoveredIntervals(int[][] intervals) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (a, b) -> a[0] - b[0]);

        for(int[] interval : intervals) {
            queue.offer(interval);
        }

        List<int[]> result = new ArrayList<>();
        int[] prev = queue.poll();
        result.add(prev);

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            if(current[0] >= prev[0] && current[1] <= prev[1]) {
                continue;
            } else if(current[0] <= prev[0] && current[1] >= prev[1]) {
                prev[0] = current[0];
                prev[1] = current[1];
                continue;
            }
            result.add(current);
            prev = current;
        }

        return result.size();
    }


    /**
     * https://leetcode.com/problems/interval-list-intersections/
     *
     * @param firstList
     * @param secondList
     * @return
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < firstList.length && j < secondList.length) {
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);
            if (start <= end)
                res.add(new int[]{start, end});
            if (firstList[i][1] < secondList[j][1]) {
                ++i;
            } else {
                ++j;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
