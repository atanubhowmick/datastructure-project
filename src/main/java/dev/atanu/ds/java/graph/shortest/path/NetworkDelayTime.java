package dev.atanu.ds.java.graph.shortest.path;

import java.util.*;

/**
 * 743. Network Delay Time
 * https://leetcode.com/problems/network-delay-time/
 *
 */
public class NetworkDelayTime {

    public static void main(String[] args) {
        NetworkDelayTime networkDelayTime = new NetworkDelayTime();
        int[][] network = new int[][] {
                {1, 2, 1},
                {2, 3, 7},
                {1, 3, 4},
                {2, 1, 2}};
        System.out.println(networkDelayTime.networkDelayTime(network, 4, 1));
    }

    /**
     * Using Dijkstra Algorithm
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

        // Can also use set here
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


    /**
     * Using visited set
     *
     * @param times - times
     * @param n - n
     * @param k - k
     * @return min delay
     */
    public int networkDelayTimeUsingSet(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] time : times) {
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }

        // Put distance and node into PriorityQueue
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        queue.offer(new int[]{0, k});

        Set<Integer> visited = new HashSet<>();
        int res = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int currentDist = cur[0];
            int currentNode = cur[1];

            if (visited.contains(currentNode)) {
                continue;
            }
            visited.add(currentNode);
            res = currentDist;
            if (map.containsKey(currentNode)) {
                for (int next : map.get(currentNode).keySet()) {
                    queue.offer(new int[]{currentDist + map.get(currentNode).get(next), next});
                }
            }
        }
        return visited.size() == n ? res : -1;
    }



    /**
     * Using common framework of Dijkstra Algorithm
     *
     * @param times - times
     * @param n - n
     * @param k - k
     * @return min delay
     */
    public int networkDelayTime1(int[][] times, int n, int k) {
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : times) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            graph.get(u).add(new int[]{v, wt});
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Min Heap -> {distance, node}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, k});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currDist = curr[0];
            int node = curr[1];

            if (currDist > dist[node]) {
                continue;
            }

            for (int[] neighbour : graph.get(node)) {
                int nextNode = neighbour[0];
                int weight = neighbour[1];

                if (currDist + weight < dist[nextNode]) {
                    dist[nextNode] = currDist + weight;
                    pq.offer(new int[]{dist[nextNode], nextNode});
                }
            }
        }

        int result = 0;

        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE){
                return -1;
            }
            result = Math.max(result, dist[i]);
        }

        return result;
    }
}
