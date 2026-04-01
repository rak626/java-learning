package DSA.graph;

import java.util.*;

/**
 * Problem: Count Strongly Connected Components (SCCs) in a directed graph
 * <ul>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: Graph, DFS, Kosaraju's Algorithm, Strongly Connected Components</li>
 * </ul>
 *
 * <p>Approach:
 * <ol>
 *   <li>Perform a DFS on the original graph and push nodes to a stack in order of finishing time.</li>
 *   <li>Transpose the graph (reverse all edges).</li>
 *   <li>Perform DFS on the transposed graph in the order defined by the stack.</li>
 *   <li>Each DFS on the transposed graph gives __1__ strongly connected component.</li>
 * </ol>
 * </p>
 *
 * <p>Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges.</p>
 * <p>Space Complexity: O(V + E) for adjacency list + O(V) for stack and visited array.</p>
 */
public class __G54__KosarajuSCC {

    /**
     * Performs DFS on the original graph to fill the stack according to finishing times.
     *
     * @param adj adjacency list of the graph
     * @param vis visited array
     * @param st stack to store nodes in finishing order
     * @param node current node
     */
    private void dfs1(List<List<Integer>> adj, boolean[] vis, Deque<Integer> st, int node) {
        vis[node] = true;
        for (int nei : adj.get(node)) {
            if (!vis[nei]) {
                dfs1(adj, vis, st, nei);
            }
        }
        st.push(node);
    }

    /**
     * DFS on the transposed graph to mark all nodes of __1__ SCC.
     *
     * @param adjT adjacency list of the transposed graph
     * @param vis visited array
     * @param node current node
     */
    private void dfs2(List<List<Integer>> adjT, boolean[] vis, int node) {
        vis[node] = true;
        for (int nei : adjT.get(node)) {
            if (!vis[nei]) {
                dfs2(adjT, vis, nei);
            }
        }
    }

    /**
     * Returns the number of strongly connected components in a directed graph.
     *
     * @param adj adjacency list representation of the graph
     * @return number of strongly connected components
     */
    public int kosaraju(List<List<Integer>> adj) {
        int v = adj.size();
        boolean[] vis = new boolean[v];
        Deque<Integer> st = new ArrayDeque<>();

        // Step 1: Fill nodes in stack according to finishing times
        for (int i = 0; i < v; i++) {
            if (!vis[i]) {
                dfs1(adj, vis, st, i);
            }
        }

        // Step 2: Transpose the graph
        List<List<Integer>> adjT = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjT.add(new ArrayList<>());
        }
        for (int i = 0; i < v; i++) {
            for (int nei : adj.get(i)) {
                adjT.get(nei).add(i);
            }
        }

        // Step 3: Process all nodes in order defined by stack
        Arrays.fill(vis, false);
        int scc = 0;
        while (!st.isEmpty()) {
            int node = st.pop();
            if (!vis[node]) {
                scc++;
                dfs2(adjT, vis, node);
            }
        }

        return scc;
    }
}

