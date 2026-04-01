package DSA.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Problem: Is Graph Bipartite
 * <ul>
 *     <li>Link: <a href="https://leetcode.com/problems/is-graph-bipartite/description/">LeetCode - Is Graph Bipartite</a></li>
 *     <li>Difficulty: Medium</li>
 *     <li>Tags: Graph, BFS, DFS</li>
 * </ul>
 *
 * Approach (BFS):
 * <ul>
 *     <li>We try to color the graph using __2__ colors (0 and 1).</li>
 *     <li>Start BFS from each unvisited node and color it with 0.</li>
 *     <li>For each neighbor:
 *         <ul>
 *             <li>If uncolored, assign the opposite color and push to the queue.</li>
 *             <li>If already colored and has the same color as current node → not bipartite.</li>
 *         </ul>
 *     </li>
 *     <li>If all connected components pass the check, the graph is bipartite.</li>
 * </ul>
 *
 * Approach (DFS):
 * <ul>
 *     <li>Similar logic, but we recursively assign opposite colors to neighbors.</li>
 *     <li>If we find a neighbor with the same color → return false.</li>
 * </ul>
 *
 * Time Complexity:
 * <ul>
 *     <li>O(V + E) — Each vertex and edge is visited once.</li>
 * </ul>
 *
 * Space Complexity:
 * <ul>
 *     <li>O(V) — Color array + recursion stack (DFS) or queue (BFS).</li>
 * </ul>
 */
public class __G17_G18__Bipartite_Graph {

    /**
     * Checks if the graph is bipartite using BFS.
     *
     * @param graph adjacency list representation of the graph
     * @return true if bipartite, false otherwise
     */
    public boolean isBipartiteBFS(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1); // -1 means uncolored

        for (int start = 0; start < n; start++) {
            if (color[start] != -1) continue; // already visited

            Deque<Integer> queue = new ArrayDeque<>();
            queue.offer(start);
            color[start] = 0;

            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int nei : graph[node]) {
                    if (color[nei] == -1) {
                        color[nei] = 1 - color[node];
                        queue.offer(nei);
                    } else if (color[nei] == color[node]) {
                        return false; // conflict
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks if the graph is bipartite using DFS.
     *
     * @param graph adjacency list representation of the graph
     * @return true if bipartite, false otherwise
     */
    public boolean isBipartiteDFS(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);

        for (int i = 0; i < n; i++) {
            if (color[i] == -1 && dfs(i, 0, color, graph)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int node, int c, int[] color, int[][] graph) {
        color[node] = c;
        for (int nei : graph[node]) {
            if (color[nei] == -1) {
                if (!dfs(nei, 1 - c, color, graph)) return false;
            } else if (color[nei] == c) {
                return true; // conflict
            }
        }
        return false;
    }
}
