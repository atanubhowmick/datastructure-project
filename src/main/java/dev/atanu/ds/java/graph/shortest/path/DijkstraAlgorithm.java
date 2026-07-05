package dev.atanu.ds.java.graph.shortest.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Shortest path using Dijkstra Algorithm
 * <br>
 *
 * https://www.geeksforgeeks.org/dsa/dijkstras-shortest-path-algorithm-greedy-algo-7/
 */
public class DijkstraAlgorithm {

    public static void main(String[] args) {
        int nodes = 5;
        int src = 0;

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
        }

        addEdge(graph, 0, 1, 4);
        addEdge(graph, 0, 2, 8);
        addEdge(graph, 1, 4, 6);
        addEdge(graph, 1, 2, 3);
        addEdge(graph, 2, 3, 2);
        addEdge(graph, 3, 4, 10);

        DijkstraAlgorithm solution = new DijkstraAlgorithm();
        List<Integer> result = solution.findShortestPath(graph, src);

        result.forEach(System.out::println);
    }

    private static void addEdge(List<List<int[]>> graph, int u, int v, int w) {
        graph.get(u).add(new int[]{v, w});
        graph.get(v).add(new int[]{u, w});
    }


    /**
     * @param graph - graph
     * @param src - source node
     * @return distance list
     */
    public List<Integer> findShortestPath(List<List<int[]>> graph, int src) {
        int nodes = graph.size();

        // Min-heap (priority queue) storing pairs of (distance, node)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Distance array: stores shortest distance from source
        int[] distance = new int[nodes];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // Distance from source to itself is 0
        distance[src] = 0;
        pq.offer(new int[]{0, src});

        // Process the queue until all reachable vertices are finalized
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int d = current[0];
            int u = current[1];

            // If this distance is not the latest shortest one, skip it
            // Can also take a set as 'visited'
            if (d > distance[u]) {
                continue;
            }

            // Get connected nodes from current node
            List<int[]> nextNodes = graph.get(u);

            // Explore all adjacent vertices
            for (int[] p : nextNodes) {
                int v = p[0];
                int w = p[1];

                // If we found a shorter path to v through u, update it
                if (distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    pq.offer(new int[]{distance[v], v});
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int d : distance) {
            result.add(d);
        }

        return result;
    }
}
