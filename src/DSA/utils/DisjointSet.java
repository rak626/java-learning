package DSA.utils;

/**
 * Disjoint Set Union (Union-Find) data structure.
 * <p>
 * Supports efficient operations to keep track of elements partitioned into disjoint (non-overlapping) sets.
 * <ul>
 *   <li><b>find(x):</b> Finds the representative (root) of the set containing element {@code x}.
 *       Uses <i>path compression</i> to flatten the structure for efficiency.</li>
 *   <li><b>unionByRank(x, y):</b> Merges the sets containing {@code x} and {@code y}
 *       using <i>union by rank</i> (based on tree depth).</li>
 *   <li><b>unionBySize(x, y):</b> Merges the sets containing {@code x} and {@code y}
 *       using <i>union by size</i> (based on number of elements).</li>
 * </ul>
 * <p>
 * Complexity (amortized): {@code O(α(n))}, where α is the inverse Ackermann function.
 * This grows so slowly it is effectively constant for all practical input sizes.
 * <p>
 * Common use cases:
 * <ul>
 *   <li>Detecting cycles in an undirected graph</li>
 *   <li>Kruskal’s Minimum Spanning Tree (MST) algorithm</li>
 *   <li>Dynamic connectivity queries</li>
 *   <li>Grouping elements into equivalence classes</li>
 * </ul>
 */
public class DisjointSet {
    private final int[] parent;
    private final int[] rank;   // for union by rank
    private final int[] size;   // for union by size

    /**
     * Initializes the Disjoint Set with {@code n} elements (0 to n-1).
     * Each element starts in its own set.
     *
     * @param n number of elements
     */
    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    /**
     * Finds the representative (ultimate parent) of the given node.
     * Applies path compression to flatten the structure, making future finds faster.
     *
     * @param node element whose set representative is to be found
     * @return the root/representative of the set containing {@code node}
     */
    public int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]); // path compression
        }
        return parent[node];
    }

    /**
     * Merges the sets containing {@code u} and {@code v} using union by rank.
     * <p>
     * The tree with smaller rank is attached under the __1__ with larger rank.
     * If both have the same rank, __1__ becomes the root and its rank increases by 1.
     *
     * @param u an element in the first set
     * @param v an element in the second set
     */
    public void unionByRank(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU == rootV) return;

        if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } else if (rank[rootV] < rank[rootU]) {
            parent[rootV] = rootU;
        } else {
            parent[rootV] = rootU;
            rank[rootU]++;
        }
    }

    /**
     * Merges the sets containing {@code u} and {@code v} using union by size.
     * <p>
     * The tree with fewer elements is attached under the __1__ with more elements.
     * The size of the resulting root is updated accordingly.
     *
     * @param u an element in the first set
     * @param v an element in the second set
     */
    public void unionBySize(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU == rootV) return;

        if (size[rootU] < size[rootV]) {
            parent[rootU] = rootV;
            size[rootV] += size[rootU];
        } else {
            parent[rootV] = rootU;
            size[rootU] += size[rootV];
        }
    }


    /**
     *  return current group size
     * @param node
     * @return
     */
    public int getSize(int node) {
        return size[this.find(node)];

    }
}
