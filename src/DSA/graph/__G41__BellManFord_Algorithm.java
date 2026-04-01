package DSA.graph;

import java.util.Arrays;

/**
 * <h2>Bellman-Ford Algorithm</h2>
 *
 * <p>
 * Finds shortest distances from a source node to all other nodes in a graph
 * that may contain negative edge weights. Also detects negative weight cycles.
 * </p>
 *
 * <p><b>Problem Link:</b>
 * <a href="https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/">
 * LeetCode 1976 (related concept)</a></p>
 *
 * <p><b>Approach:</b></p>
 * <ul>
 *   <li>Initialize all distances as INF except source.</li>
 *   <li>Relax all edges V-1 times.</li>
 *   <li>Check for negative cycles by doing __1__ more relaxation.</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(V * E)</p>
 * <p><b>Space Complexity:</b> O(V)</p>
 */
public class __G41__BellManFord_Algorithm {
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int INF = (int) 1e8;           // Large value representing "infinity"
        int[] dist = new int[V];       // Distance array
        Arrays.fill(dist, INF);        // Initialize all distances to INF
        dist[src] = 0;                 // Distance to source is 0

        // Relax all edges V-1 times
        for (int i = 0; i < V - 1; i++) {
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];

                // Only relax if current node is reachable
                if (dist[u] != INF && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // Check for negative weight cycle
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (dist[u] != INF && dist[u] + w < dist[v]) {
                return new int[]{-1}; // Negative cycle detected
            }
        }

        return dist;
    }
}
