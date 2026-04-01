package DSA.graph;

import java.util.PriorityQueue;

/**
 * <h2>Path With Minimum Effort</h2>
 *
 * <p>
 * Given a grid of heights, find a path from the top-left cell (0,0) to the
 * bottom-right cell (n-1,m-1) such that the maximum absolute difference in
 * heights between adjacent cells along the path is minimized.
 * </p>
 *
 * <p><b>Problem Link:</b>
 * <a href="https://leetcode.com/problems/path-with-minimum-effort/description/">
 * LeetCode 1631 - Path With Minimum Effort</a>
 * </p>
 *
 * <p><b>Difficulty:</b> Medium</p>
 * <p><b>Tags:</b> Graph, Dijkstra, Matrix</p>
 *
 * <h3>Approach</h3>
 * <ul>
 *   <li>Model the grid as a graph where each cell is a node.</li>
 *   <li>The cost (or "effort") to move between __2__ adjacent cells is the
 *       absolute difference of their heights.</li>
 *   <li>We want to minimize the maximum edge cost along the path.</li>
 *   <li>Use Dijkstra’s algorithm:
 *       <ul>
 *           <li>Maintain a distance matrix {@code dist[x][y]} representing
 *           the minimum effort required to reach cell (x,y).</li>
 *           <li>Use a priority queue (min-heap) to always expand the cell
 *           with the smallest current effort.</li>
 *           <li>When the destination cell is popped from the queue,
 *           its effort is the answer.</li>
 *       </ul>
 *   </li>
 * </ul>
 *
 * <h3>Complexity Analysis</h3>
 * <ul>
 *   <li>Time Complexity: O(N * M * log(N * M)), since each cell may be pushed
 *       into the priority queue and each push/pop costs log(N*M).</li>
 *   <li>Space Complexity: O(N * M), for the distance matrix and priority queue.</li>
 * </ul>
 */
public class __G36__PathWithMinimumEffort {

    /** Four possible movement directions (up, down, left, right). */
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * Represents a state in the priority queue:
     * the minimum effort required to reach cell (x, y).
     *
     * @param effort minimum effort to reach this cell
     * @param x      row index
     * @param y      column index
     */
    private record Node(int effort, int x, int y) {}

    /**
     * Computes the minimum effort required to travel from the top-left cell (0,0)
     * to the bottom-right cell (n-1,m-1) in the heights grid.
     *
     * <p>
     * The effort of a path is defined as the maximum absolute difference in heights
     * between adjacent cells along the path. This method returns the minimum
     * possible effort across all valid paths.
     * </p>
     *
     * @param heights 2D grid of integers where heights[i][j] represents the height of cell (i,j)
     * @return the minimum effort required to reach the destination cell
     */
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length, m = heights[0].length;

        // dist[x][y] = minimum effort required to reach (x,y)
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.effort - b.effort);
        pq.offer(new Node(0, 0, 0));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            // Destination reached -> answer found
            if (curr.x == n - 1 && curr.y == m - 1) {
                return curr.effort;
            }

            for (int[] dir : DIRS) {
                int nx = curr.x + dir[0];
                int ny = curr.y + dir[1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                int edgeWeight = Math.abs(heights[curr.x][curr.y] - heights[nx][ny]);
                int newEffort = Math.max(curr.effort, edgeWeight);

                if (newEffort < dist[nx][ny]) {
                    dist[nx][ny] = newEffort;
                    pq.offer(new Node(newEffort, nx, ny));
                }
            }
        }

        return 0; // fallback, should not happen for valid inputs
    }
}
