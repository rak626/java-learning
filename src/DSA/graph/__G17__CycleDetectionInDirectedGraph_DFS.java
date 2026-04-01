package DSA.graph;

import java.util.*;

/**
 * Problem: Cycle Detection in Directed Graph (DFS)
 * <ul>
 *   <li>Link: <a href="https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1">Detect cycle in a directed graph</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: Graph, DFS, Cycle Detection</li>
 * </ul>
 *
 * <p>
 * Approach (DFS + Recursion Stack):
 * <ul>
 *   <li>We need to detect if a directed graph contains a cycle.</li>
 *   <li>Use __2__ arrays:
 *       <ul>
 *           <li><b>vis[]</b>: Marks whether a node has been visited overall.</li>
 *           <li><b>pathVis[]</b>: Marks whether a node is part of the current DFS recursion stack (current path).</li>
 *       </ul>
 *   </li>
 *   <li>Start DFS from every unvisited node:
 *       <ul>
 *           <li>If we encounter a node that is already in the current recursion stack (<code>pathVis[]</code>),
 *           then a cycle exists (back edge).</li>
 *           <li>After DFS completes for a node, remove it from the recursion stack by marking <code>pathVis[node] = false</code>.</li>
 *       </ul>
 *   </li>
 *   <li>If no cycle is found in any component, return false.</li>
 * </ul>
 *
 * <p>
 * Example:
 * <pre>
 * Input: V = 4, Edges = [[0,1],[1,2],[2,0],[2,3]]
 * Output: true
 * Explanation: The path 0 → 1 → 2 → 0 forms a cycle.
 * </pre>
 *
 * <p>
 * Time Complexity: O(V + E) &nbsp;&nbsp; (each node and edge visited once)<br>
 * Space Complexity: O(V) &nbsp;&nbsp; (visited arrays + recursion stack)
 */
public class __G17__CycleDetectionInDirectedGraph_DFS {

    private boolean[] vis;
    private boolean[] pathVis;

    public boolean isCyclic(int V, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        // Build adjacency list
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }

        vis = new boolean[V];
        pathVis = new boolean[V];

        // Run DFS for every unvisited node
        for (int node = 0; node < V; node++) {
            if (!vis[node]) {
                boolean isCycle = dfs(adjList, node);
                if (isCycle) return true;   // cycle found
            }
        }
        return false;   // no cycle in the graph
    }

    private boolean dfs(Map<Integer, List<Integer>> adjList, int node) {
        vis[node] = true;       // Mark node as visited
        pathVis[node] = true;   // Mark as part of current recursion stack

        for (int neighbour : adjList.getOrDefault(node, Collections.emptyList())) {
            if (!vis[neighbour]) {
                if (dfs(adjList, neighbour)) {
                    return true;   // cycle found deeper
                }
            } else if (pathVis[neighbour]) {
                return true;   // back edge found (cycle detected)
            }
        }

        pathVis[node] = false;  // remove from recursion stack before returning
        return false;
    }
}


// for undirected Graph no need path vis