package DSA.graph;

import java.util.*;

/**
 * Problem: Find Eventual Safe States
 * <ul>
 *   <li>Link: <a href="https://leetcode.com/problems/find-eventual-safe-states/">LeetCode 802. Find Eventual Safe States</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: Graph, DFS, Cycle Detection</li>
 * </ul>
 *
 * <p>
 * A node is considered "safe" if starting from that node,
 * every path eventually leads to a terminal node (a node with no outgoing edges),
 * and never enters a cycle.
 * </p>
 *
 * <p>
 * Approach (DFS + Cycle Detection):
 * <ul>
 *   <li>We perform DFS from each node to check if it eventually reaches a terminal node.</li>
 *   <li>Use __3__ arrays:
 *     <ul>
 *       <li><b>visited[]</b>: Marks if a node has been processed at least once.</li>
 *       <li><b>onPath[]</b>: Tracks nodes in the current DFS recursion stack (detects cycles).</li>
 *       <li><b>isSafe[]</b>: Marks nodes that are confirmed safe (not part of any cycle).</li>
 *     </ul>
 *   </li>
 *   <li>If DFS detects a cycle from a node, that node is unsafe.</li>
 *   <li>If DFS completes without hitting a cycle, mark the node as safe.</li>
 *   <li>Collect all safe nodes in ascending order as the final answer.</li>
 * </ul>
 *
 * <p>
 * Example:
 * <pre>
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Explanation:
 * - Node 0 leads into a cycle (0 → 1 → 2 → 3 → 0).
 * - Node 1 and 3 are also part of this cycle.
 * - Nodes 2, 4, 5, 6 are safe.
 * </pre>
 *
 * <p>
 * Time Complexity: O(V + E) — each node and edge visited at most once.<br>
 * Space Complexity: O(V) — visited arrays + recursion stack.
 */
public class __G20__FindEventualSafeStates_DFS {
    private boolean[] visited;
    private boolean[] onPath;
    private boolean[] isSafe;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        visited = new boolean[n];
        onPath = new boolean[n];
        isSafe = new boolean[n];

        // Run DFS for each unvisited node
        for (int node = 0; node < n; node++) {
            if (!visited[node]) {
                dfs(node, graph);
            }
        }

        // Collect all safe nodes
        List<Integer> result = new ArrayList<>();
        for (int node = 0; node < n; node++) {
            if (isSafe[node]) {
                result.add(node);
            }
        }
        return result;
    }

    private boolean dfs(int node, int[][] graph) {
        visited[node] = true;
        onPath[node] = true;

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, graph)) {
                    return true; // cycle detected
                }
            } else if (onPath[neighbor]) {
                return true; // back edge found → cycle
            }
        }

        // Backtrack: remove from recursion stack
        onPath[node] = false;

        // If no cycle detected in this path → node is safe
        isSafe[node] = true;
        return false;
    }
}
