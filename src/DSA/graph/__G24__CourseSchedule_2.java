package DSA.graph;

import java.util.*;

/**
 * Problem: Course Schedule II
 * <ul>
 *   <li>Link: <a href="https://leetcode.com/problems/course-schedule-ii/description/">LeetCode</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: Graph, DFS, Topological Sorting</li>
 * </ul>
 *
 * Approach (DFS + Topological Sort):
 * <ul>
 *   <li>We need to return a valid course ordering if possible.</li>
 *   <li>Build an adjacency list from prerequisites.</li>
 *   <li>Use DFS with cycle detection:
 *     <ul>
 *       <li>State array (0 = unvisited, 1 = visiting, 2 = visited).</li>
 *       <li>If we revisit a "visiting" node → cycle → no valid order.</li>
 *     </ul>
 *   </li>
 *   <li>After visiting all neighbors of a node, mark it as visited and add to result list.</li>
 *   <li>Reverse the result list at the end (since DFS gives postorder).</li>
 * </ul>
 *
 * Time Complexity:
 * <ul>
 *   <li>O(V + E) — DFS visits each vertex and edge once.</li>
 * </ul>
 *
 * Space Complexity:
 * <ul>
 *   <li>O(V + E) — Adjacency list + recursion stack + result list.</li>
 * </ul>
 */
public class __G24__CourseSchedule_2 {

    /**
     * Finds a valid course order if possible.
     *
     * @param n        Number of courses.
     * @param prereq   Prerequisites array where [a, b] means b → a.
     * @return An array representing __1__ valid course order, or empty array if not possible.
     */
    public int[] findOrder(int n, int[][] prereq) {
        // Step 1: Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : prereq) adj.get(e[1]).add(e[0]);

        // Step 2: State array (0 = unvisited, 1 = visiting, 2 = visited)
        int[] state = new int[n];
        List<Integer> order = new ArrayList<>();

        // Step 3: DFS on each node
        for (int i = 0; i < n; i++) {
            if (state[i] == 0 && dfs(i, adj, state, order)) {
                return new int[]{}; // Cycle found → no valid order
            }
        }

        // Step 4: Reverse postorder to get topo order
        Collections.reverse(order);

        // Step 5: Convert list to array
        int[] res = new int[order.size()];
        for (int i = 0; i < order.size(); i++) res[i] = order.get(i);

        return res;
    }

    /**
     * DFS helper with cycle detection.
     */
    private boolean dfs(int node, List<List<Integer>> adj, int[] state, List<Integer> order) {
        state[node] = 1; // Mark as visiting

        for (int nei : adj.get(node)) {
            if (state[nei] == 1) return true;          // Cycle found
            if (state[nei] == 0 && dfs(nei, adj, state, order)) return true;
        }

        state[node] = 2;   // Mark as fully visited
        order.add(node);   // Postorder add
        return false;
    }


    // bfs solution
    public int[] findOrder_bfs(int n, int[][] prereq) {
        // Step 1: Build adjacency list and indegree array
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        int[] indegree = new int[n];

        for (int[] e : prereq) {
            adj.get(e[1]).add(e[0]); // edge: b → a
            indegree[e[0]]++;
        }

        // Step 2: Initialize queue with indegree-0 nodes
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (indegree[i] == 0) q.offer(i);

        // Step 3: Process queue
        int[] order = new int[n];
        int idx = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            order[idx++] = node;

            for (int nei : adj.get(node)) {
                if (--indegree[nei] == 0) q.offer(nei);
            }
        }

        // Step 4: Check if topo sort was possible (no cycle)
        if (idx == n) return order;
        return new int[]{}; // Cycle detected → no valid order
    }
}
