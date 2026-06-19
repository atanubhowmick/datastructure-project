package dev.atanu.ds.java.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {

    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    private boolean isEven = true;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        if (isEven) {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
        isEven = !isEven;
    }

    public double findMedian() {
        double median = 0d;
        if (isEven) {
            median = ((double) (maxHeap.peek() + minHeap.peek())) / 2;
        } else {
            median = (double) maxHeap.peek();
        }
        return median;
    }
}
