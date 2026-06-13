package dev.atanu.ds.java.interval;

import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-i/
 *
 */
public class MyCalendar {

    private final TreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new TreeMap<>();
    }

    public boolean book(int startTime, int endTime) {
        Integer prev = calendar.floorKey(startTime);
        if(prev != null && calendar.get(prev) > startTime) {
            return false;
        }

        Integer next = calendar.ceilingKey(startTime);
        if (next != null && next < endTime) {
            return false;
        }
        calendar.put(startTime, endTime);

        return true;
    }
}
