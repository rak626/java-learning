package DSA.graph;

import DSA.utils.DisjointSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Number of Islands II (Online Queries)
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/number-of-islands-ii/description/">Number of Islands II</a></li>
 * <li>Difficulty: Medium</li>
 * <li>Tags: graph, dsu, union-find</li>
 * </ul>
 *
 * <p>Approach:</p>
 * <ol>
 * <li>Use a Disjoint Set Union (DSU) to keep track of connected land cells.</li>
 * <li>Iterate over each position (r, c) added as land:</li>
 * <ul>
 *   <li>If the cell is already visited, append the current island count.</li>
 *   <li>Otherwise, mark it as visited and increment the island count.</li>
 *   <li>Check all 4 adjacent directions:</li>
 *       <ul>
 *           <li>If a neighbor is visited, union the current cell with the neighbor.</li>
 *           <li>If a union happens, decrement the island count (__2__ islands merged).</li>
 *       </ul>
 * </ul>
 * <li>Append the current island count to the answer list after processing each position.</li>
 * </ol>
 * <p>
 * Time Complexity: O(K * α(N*M)), where K = number of positions, α = Inverse Ackermann (almost constant)
 * Space Complexity: O(N*M) for DSU arrays and visited grid.
 * </p>
 *
 * <p>Notes:</p>
 * <ul>
 * <li>Flatten the 2D grid to 1D indices for DSU operations: node = r * m + c.</li>
 * <li>Union by size ensures efficient merging and almost constant time DSU operations.</li>
 * <li>Visited grid prevents double counting the same land cell.</li>
 * </ul>
 */
public class __G51__NumberOfIslandsOnlineQueries {

    public List<Integer> numOfIslands(int n, int m, int[][] positions) {
        DisjointSet ds = new DisjointSet(n * m);
        int[][] visited = new int[n][m];
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int count = 0;
        List<Integer> ans = new ArrayList<>();

        for (int[] pos : positions) {
            int r = pos[0];
            int c = pos[1];

            if (visited[r][c] == 1) {
                ans.add(count);
                continue;
            }

            visited[r][c] = 1;
            count++;
            int node = r * m + c;

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && visited[nr][nc] == 1) {
                    int neighbor = nr * m + nc;
                    if (ds.find(node) != ds.find(neighbor)) {
                        ds.unionBySize(node, neighbor);
                        count--; // merged __2__ islands
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }
}
