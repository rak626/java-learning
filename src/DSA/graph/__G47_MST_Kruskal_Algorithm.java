package DSA.graph;

import DSA.utils.DisjointSet;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <h2>Minimum Spanning Tree using Kruskal's Algorithm</h2>
 *
 * <p>
 * Given a weighted, undirected graph with {@code n} vertices and a list of edges,
 * this algorithm computes the Minimum Spanning Tree (MST), i.e., a subset of edges
 * that connects all vertices with the minimum possible total edge weight and no cycles.
 * </p>
 *
 * <p><b>Algorithm (Kruskal's):</b></p>
 * <ul>
 *   <li>Sort all edges in non-decreasing order of weight.</li>
 *   <li>Initialize a Disjoint Set Union (DSU) structure to keep track of connected components.</li>
 *   <li>Iterate through edges in sorted order:
 *     <ul>
 *       <li>If the current edge connects __2__ different components, include it in the MST.</li>
 *       <li>Merge the __2__ components in DSU.</li>
 *     </ul>
 *   </li>
 *   <li>Repeat until exactly {@code n-1} edges are added to the MST.</li>
 * </ul>
 *
 * <p><b>Input Format:</b></p>
 * <ul>
 *   <li>{@code n} → number of vertices (0-indexed).</li>
 *   <li>{@code edges} → array of edges, each represented as {@code {u, v, w}}:
 *     <ul>
 *       <li>{@code u, v} → vertices (0 ≤ u, v < n).</li>
 *       <li>{@code w} → weight of edge (integer).</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p><b>Output:</b> Total weight of the Minimum Spanning Tree.</p>
 *
 * <p><b>Complexity:</b></p>
 * <ul>
 *   <li>Time: O(E log E) ≈ O(E log V), due to edge sorting and DSU operations.</li>
 *   <li>Space: O(V), for the Disjoint Set Union structure.</li>
 * </ul>
 */
public class __G47_MST_Kruskal_Algorithm {

    /**
     * Computes the total weight of the Minimum Spanning Tree using Kruskal's Algorithm.
     *
     * @param n     number of vertices in the graph
     * @param edges list of edges in the form {u, v, w}
     * @return total weight of MST
     */
    public int kruskalMST(int n, int[][] edges) {
        int[][] sortedEdges = Arrays.copyOf(edges, edges.length);
        Arrays.sort(sortedEdges, Comparator.comparingInt(edge -> edge[2]));

        DisjointSet ds = new DisjointSet(n);
        int mstWt = 0;

        for (var e : sortedEdges) {
            int u = e[0], v = e[1], w = e[2];
            if (ds.find(u) != ds.find(v)) {
                mstWt += w;
                ds.unionBySize(u, v);
            }
        }
        return mstWt;
    }
}
