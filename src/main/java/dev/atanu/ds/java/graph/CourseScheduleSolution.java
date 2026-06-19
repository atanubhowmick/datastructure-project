package dev.atanu.ds.java.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseScheduleSolution {

    /**
     * https://leetcode.com/problems/course-schedule/
     *
     * @param numCourses - numCourses
     * @param prerequisites - prerequisites
     * @return boolean
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Key = pre-requisite, value = courses can be taken;
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // Courses are from 0 to (numCourses - 1)
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] p : prerequisites) {
            int preReq = p[1];
            List<Integer> coursesCanBeTaken = graph.get(preReq);
            coursesCanBeTaken.add(p[0]);
        }

        int[] state = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, graph, state)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int course, Map<Integer, List<Integer>> graph, int[] state) {
        // Cycle found
        if (state[course] == 1) {
            return false;
        }

        // Already visited and no cycle found
        if (state[course] == 2) {
            return true;
        }

        // Currently exploring this node
        state[course] = 1;

        for (int next : graph.get(course)) {
            if (!dfs(next, graph, state)) {
                return false;
            }
        }

        //  Finished exploring safely. No cycle from this node.
        state[course] = 2;

        return true;
    }

}
