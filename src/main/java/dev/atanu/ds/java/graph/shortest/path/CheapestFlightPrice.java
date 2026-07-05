package dev.atanu.ds.java.graph.shortest.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 787. Cheapest Flights Within K Stops
 * <br>
 *
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 */
public class CheapestFlightPrice {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] flight : flights) {
            int start = flight[0];
            int end = flight[1];
            int price = flight[2];
            graph.get(start).add(new int[] { end, price });
        }

        int minPrice = Integer.MAX_VALUE;

        // distance[node][stops]
        int[][] distance = new int[n][k + 2];

        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        distance[src][0] = 0;

        // Keep (price, node, stop)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] { 0, src, 0 });

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int cost = current[0];
            int u = current[1];
            int stops = current[2];

            // Reached destination
            if(u == dst) {
                return cost;
            }

            // Cannot take more stops
            if(stops > k) {
                continue;
            }

            List<int[]> neighbours = graph.get(u);
            for (int[] next : neighbours) {
                int v = next[0];
                int price = next[1];
                int newCost = cost + price;

                if(newCost < distance[v][stops + 1]) {
                    distance[v][stops + 1] = newCost;
                    pq.offer(new int[] {newCost, v, stops + 1});
                }
            }
        }

        return -1;
    }
}
