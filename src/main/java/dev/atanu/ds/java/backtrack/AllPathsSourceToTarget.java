package dev.atanu.ds.java.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * AllPathsSourceToTarget
 *
 * https://leetcode.com/problems/all-paths-from-source-to-target/
 *
 */
public class AllPathsSourceToTarget {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        tempList.add(0); // Add the start node
        backtracking(graph, result, tempList, 0);
        return result;
    }

    private void backtracking(int[][] graph, List<List<Integer>> result,
                              List<Integer> tempList, int start) {
        if (start == graph.length - 1) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int nextNode : graph[start]) {
            tempList.add(nextNode);
            backtracking(graph, result, tempList, nextNode);
            tempList.remove(tempList.size() - 1);
        }
    }
}
