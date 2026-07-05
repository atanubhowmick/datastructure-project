package dev.atanu.ds.java.graph.shortest.path;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 743. Network Delay Time
 * https://leetcode.com/problems/network-delay-time/
 *
 */
public class NetworkDelayTime {

    public static void main(String[] args) {
        NetworkDelayTime networkDelayTime = new NetworkDelayTime();
        int[][] network = new int[][] {{1,2,1},{2,3,7},{1,3,4},{2,1,2}};
        System.out.println(networkDelayTime.networkDelayTime(network, 4, 1));
    }

    /**
     * Using Dijkstra  Algorithm
     *
     * @param times - times
     * @param n - n
     * @param k - k
     * @return min delay
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] time : times) {
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }

        // Put distance and node into PriorityQueue
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        queue.offer(new int[]{0, k});

        // Can use set also here
        boolean[] visited = new boolean[n + 1];
        int res = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int currentDist = cur[0];
            int currentNode = cur[1];

            if (visited[currentNode]) {
                continue;
            }
            visited[currentNode] = true;
            res = currentDist;
            n--;
            if (map.containsKey(currentNode)) {
                for (int next : map.get(currentNode).keySet()) {
                    queue.offer(new int[]{currentDist + map.get(currentNode).get(next), next});
                }
            }
        }
        return n == 0 ? res : -1;
    }

}
