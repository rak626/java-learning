package DSA.graph;

import java.util.*;

/**
 * <h2>Find The City With The Smallest Number Of Neighbors At A Threshold Distance</h2>
 *
 * <p>
 * You are given a weighted, undirected graph with {@code n} cities and a list of edges.
 * Each edge consists of __3__ integers {@code [u, v, w]} meaning there is a bidirectional
 * road between cities {@code u} and {@code v} with distance {@code w}.
 * </p>
 *
 * <p>
 * A city is said to be a neighbor of another city if the shortest path between them
 * is less than or equal to the given {@code distanceThreshold}.
 * The task is to find the city with the smallest number of neighbors that are reachable
 * within {@code distanceThreshold}. In case of a tie, return the city with the largest index.
 * </p>
 *
 * <p><b>Problem Link:</b>
 * <a href="https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/">
 * Find The City With The Smallest Number Of Neighbors At A Threshold Distance</a>
 * </p>
 *
 * <p><b>Approach:</b></p>
 * <ul>
 *   <li>Use the Floyd–Warshall algorithm to compute shortest paths between all pairs of cities.</li>
 *   <li>Initialize an adjacency matrix with large values (representing infinity).</li>
 *   <li>Update the matrix with given edge weights (graph is undirected).</li>
 *   <li>Run the Floyd–Warshall update in {@code O(n^3)} to compute all-pairs shortest paths.</li>
 *   <li>For each city, count how many cities are reachable within {@code distanceThreshold}.</li>
 *   <li>Choose the city with the minimum reachable count. Break ties by choosing the larger index.</li>
 * </ul>
 *
 * <p><b>Complexity:</b></p>
 * <ul>
 *   <li>Time Complexity: {@code O(n^3)} (Floyd–Warshall algorithm).</li>
 *   <li>Space Complexity: {@code O(n^2)} (distance matrix).</li>
 * </ul>
 */
public class __G43__FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {

    private static final int INF = (int) 1e8; // sufficiently large to represent "infinity"

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // Step 1: Initialize distance matrix
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // Step 2: Fill edges (graph is undirected)
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            dist[u][v] = dist[v][u] = w;
        }

        // Step 3: Floyd–Warshall to compute all-pairs shortest paths
        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][via] != INF && dist[via][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][via] + dist[via][j]);
                    }
                }
            }
        }

        // Step 4: Find the city with smallest number of neighbors (ties → larger index)
        int minReachable = INF;
        int resultCity = -1;

        for (int city = n - 1; city >= 0; city--) {
            int reachable = 0;
            for (int neighbor = 0; neighbor < n; neighbor++) {
                if (city != neighbor && dist[city][neighbor] <= distanceThreshold) {
                    reachable++;
                }
            }
            if (reachable < minReachable) {
                minReachable = reachable;
                resultCity = city;
            }
        }

        return resultCity;
    }

    /**
     * Dijkstra version
     * @param n
     * @param edges
     * @param distanceThreshold
     * T.C -> O(n * (n * Log(V)))
     * S.C -> O(n * n) can be done it in O(n) as well;
     */
    public int findTheCity_Dijkstra(int n, int[][] edges, int distanceThreshold) {
        // Step 1: Build adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});
        }

        // Step 2: Run Dijkstra from every node
        int[][] dist = new int[n][];
        for (int i = 0; i < n; i++) {
            dist[i] = dijkstra(n, adj, i);
        }

        // Step 3: Find city with smallest reachable neighbors (ties → larger index)
        int minReachable = INF, city = -1;
        for (int i = 0; i < n; i++) {
            int reachable = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= distanceThreshold) {
                    reachable++;
                }
            }
            if (reachable <= minReachable) {
                minReachable = reachable;
                city = i;
            }
        }

        return city;
    }

    private static int[] dijkstra(int n, List<List<int[]>> adj, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0], u = curr[1];

            if (d > dist[u]) continue;

            for (int[] nei : adj.get(u)) {
                int v = nei[0], w = nei[1];
                int nd = d + w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new int[]{nd, v});
                }
            }
        }
        return dist;
    }
}


