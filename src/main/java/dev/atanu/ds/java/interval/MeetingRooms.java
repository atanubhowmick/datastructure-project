package dev.atanu.ds.java.interval;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MeetingRooms {

    public static void main(String[] args) {
        MeetingRooms solution = new MeetingRooms();
        int[][] intervals = new int[][]{{1,3}, {2,6}, {8,10},{15,18}};
        System.out.println(solution.canAttendMeetings(intervals));
    }


    /**
     * https://neetcode.io/problems/meeting-schedule/question
     *
     * @param intervals - intervals
     * @return boolean
     */
    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals == null || intervals.length <= 1) {
            return true;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for(int[] interval : intervals) {
            queue.offer(interval);
        }

        int[] prev = queue.poll();
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            if(prev[1] > current[0]) {
                return false;
            }
            prev = current;
        }

        return true;
    }


    /**
     * Using Priority Queue
     * https://neetcode.io/problems/meeting-schedule-ii/
     *
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0]- b[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int[] interval : intervals) {
            if (!minHeap.isEmpty() && minHeap.peek() <= interval[0]) {
                minHeap.poll();
            }
            minHeap.offer(interval[1]);
        }
        return minHeap.size();
    }


    /**
     * Using Sweep Line Algorithm [The Best]
     * https://neetcode.io/problems/meeting-schedule-ii/
     *
     * @param intervals - intervals
     * @return int
     */
    public int minMeetingRooms1(int[][] intervals) {
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        for (int[] interval : intervals) {
            mp.put(interval[0], mp.getOrDefault(interval[0], 0) + 1);
            mp.put(interval[1], mp.getOrDefault(interval[1], 0) - 1);
        }
        int running = 0;
        int result = 0;
        for (int key : mp.keySet()) {
            running += mp.get(key);
            result = Math.max(result, running);
        }
        return result;
    }
}
