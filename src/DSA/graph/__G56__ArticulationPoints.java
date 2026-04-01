package DSA.graph;

import java.util.*;

 /**
 * Problem: Articulation Points in a Graph (Cut Vertices)
 * <ul>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: Graph, DFS, Tarjan's Algorithm, Articulation Points</li>
 * </ul>
 *
 * <p>Problem Intuition:
 * <br>
 * An articulation point (or cut vertex) in a graph is a vertex that, if removed,
 * increases the number of connected components. Identifying such nodes is crucial
 * for network reliability and connectivity analysis.
 * </p>
 *
 * <p>Algorithm Overview (Tarjan's Algorithm for Articulation Points):
 * <ol>
 *   <li>Perform a DFS traversal to compute discovery times (tin) and low-link values (low) for each node.</li>
 *   <li>For each node:
 *       <ul>
 *           <li>low[node] = earliest discovered node reachable from node (via DFS or back edges)</li>
 *           <li>If low[neighbor] >= tin[node], the current node is an articulation point.</li>
 *       </ul>
 *   </li>
 *   <li>Special case: If the root of DFS has more than __1__ child, it is also an articulation point.</li>
 * </ol>
 * </p>
 *
 * <p>Why It Works:
 * <ul>
 *   <li>low-link value represents the earliest ancestor reachable from a subtree.</li>
 *   <li>If no neighbor in the DFS subtree can reach an ancestor of current node, removing
 *       the current node disconnects the subtree → articulation point.</li>
 * </ul>
 * </p>
 *
 * <p>Time Complexity: O(V + E) — DFS visits every vertex and edge once.</p>
 * <p>Space Complexity: O(V + E) — adjacency list + O(V) arrays + recursion stack.</p>
 *
 * <p>Revision Tips:
 * <ol>
 *   <li>Remember the difference between articulation points and bridges.</li>
 *   <li>Root with multiple children → articulation point.</li>
 *   <li>low[neighbor] >= tin[current] → cut vertex.</li>
 *   <li>Parent check avoids trivial back edges to immediate parent.</li>
 * </ol>
 * </p>
 */
public class __G56__ArticulationPoints {

    private int timer;

    /**
     * Finds all articulation points (cut vertices) in an undirected graph.
     *
     * @param V number of vertices
     * @param adj adjacency list representation of the graph
     * @return list of articulation points; returns [-1] if none exist
     */
    public List<Integer> articulationPoints(int V, List<List<Integer>> adj) {
        boolean[] vis = new boolean[V];
        int[] tin = new int[V];   // discovery time
        int[] low = new int[V];   // lowest reachable discovery time
        Set<Integer> arp = new HashSet<>();
        timer = 1;

        // DFS for each unvisited node
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i, -1, adj, vis, tin, low, arp);
            }
        }

        if (arp.isEmpty()) return Collections.singletonList(-1);

        List<Integer> result = new ArrayList<>(arp);
        Collections.sort(result);
        return result;
    }

    /**
     * DFS helper to compute tin, low values and detect articulation points.
     *
     * @param node current node
     * @param parent parent node in DFS tree
     * @param adj adjacency list
     * @param vis visited array
     * @param tin discovery time array
     * @param low lowest reachable time array
     * @param arp set to store articulation points
     */
    private void dfs(int node, int parent, List<List<Integer>> adj, boolean[] vis,
                     int[] tin, int[] low, Set<Integer> arp) {
        vis[node] = true;
        tin[node] = low[node] = timer++;
        int children = 0;

        for (int nei : adj.get(node)) {
            if (nei == parent) continue;

            if (!vis[nei]) {
                children++;
                dfs(nei, node, adj, vis, tin, low, arp);
                low[node] = Math.min(low[node], low[nei]);

                // Articulation point condition
                if (low[nei] >= tin[node] && parent != -1) {
                    arp.add(node);
                }
            } else {
                // Update low value for back-edge
                low[node] = Math.min(low[node], tin[nei]);
            }
        }

        // Special case: root node
        if (parent == -1 && children > 1) {
            arp.add(node);
        }
    }
}
