/**
 * 
 */
package dev.atanu.ds.java.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Atanu Bhowmick
 *
 */
public class PriorityQueueSolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}


    /**
     * https://leetcode.com/problems/top-k-frequent-elements/
     *
     * @param nums - nums
     * @param k - k
     * @return array
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for(int key : freq.keySet()) {
            queue.offer(new int[]{key, freq.get(key)});
            if(queue.size() > k) {
                queue.poll();
            }
        }

        int[] result = new int[queue.size()];
        int i = 0;
        while(!queue.isEmpty()) {
            result[i++] = queue.poll()[0];
        }
        return result;
    }


    /**
     * https://leetcode.com/problems/k-closest-points-to-origin/
     *
     * @param points - points
     * @param k - k
     * @return points
     */
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<double[]> queue = new PriorityQueue<>(
                (p1, p2) -> Double.compare(p2[0], p1[0]));
        for(int[] point : points) {
            int x = point[0];
            int y = point[1];
            double distance = Math.sqrt((double) point[0]*point[0] + (double) point[1]*point[1]);
            queue.offer(new double[]{distance, point[0], point[1]});
            if(queue.size() > k) {
                queue.poll();
            }
        }

        int[][] result = new int[k][2];
        int i = 0;
        while(!queue.isEmpty()) {
            double[] arr = queue.poll();
            result[i++] = new int[] {(int)arr[1], (int)arr[2]};
        }
        return result;
    }


    /**
     * https://leetcode.com/problems/kth-largest-element-in-an-array/
     *
     * @param nums - nums
     * @param k - k
     * @return int
     */
    public int findKthLargest(int[] nums, int k) {
        if(nums.length < k) {
            return 0;
        }
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for(int num : nums) {
            que.offer(num);
            if(que.size() > k) {
                que.poll();
            }
        }
        return que.poll();
    }

    /**
     * https://leetcode.com/problems/kth-largest-element-in-an-array/
     *
     * @param nums - nums
     * @param k - k
     * @return largest
     */
    public int findKthLargestQuickSelect(int[] nums, int k) {
        int searchIdx = nums.length - k;
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int pivotIdx = partition(nums, start, end);
            if (pivotIdx == searchIdx) {
                return nums[pivotIdx];
            } else if (pivotIdx > searchIdx) {
                end = pivotIdx - 1;
            } else {
                start = pivotIdx + 1;
            }
        }
        return nums[start];
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start], left = start, right = end;
        while (start < end) {
            while (start < right && pivot >= nums[start]) {
                start++;
            }
            while (left < end && pivot <= nums[end]) {
                end--;
            }
            if (start < end) {
                swap(nums, start, end);
            }
        }
        swap(nums, left, end);
        return end;
    }

    /**
     * https://leetcode.com/problems/kth-largest-element-in-an-array/
     *
     * @param nums - nums
     * @param k - k
     * @return kth smallest
     */
    public int findKthSmallest(int[] nums, int k) {
        if(nums.length < k) {
            return 0;
        }
        PriorityQueue<Integer> que = new PriorityQueue<>((a, b) -> b - a);
        for(int num : nums) {
            que.offer(num);
            if(que.size() > k) {
                que.poll();
            }
        }
        return que.poll();
    }

}
