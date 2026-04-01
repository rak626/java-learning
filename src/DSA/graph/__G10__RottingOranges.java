package DSA.graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem: Rotting Oranges
 * <ul>
 *     <li>Link: <a href="https://leetcode.com/problems/rotting-oranges/">LeetCode 994</a></li>
 *     <li>Difficulty: Medium</li>
 *     <li>Tags: Graph, Breadth-First Search (BFS)</li>
 * </ul>
 *
 * Approach:
 * <ul>
 *     <li>Perform multi-source BFS from all initially rotten oranges (value = 2).</li>
 *     <li>Add all rotten oranges to a queue and count the total number of fresh oranges.</li>
 *     <li>For each minute (level in BFS), iterate over the queue and rot any adjacent fresh oranges (value = 1).</li>
 *     <li>If any fresh orange is rotted in this round, increment the minutes counter.</li>
 *     <li>At the end, if any fresh orange remains unrotted, return -1. Otherwise, return the total minutes.</li>
 * </ul>
 *
 * Time Complexity:
 * <ul>
 *     <li>O(m * n) — Each cell is visited at most once, where m = number of rows and n = number of columns.</li>
 * </ul>
 *
 * Space Complexity:
 * <ul>
 *     <li>O(m * n) — In the worst case, the queue can contain all the cells of the grid.</li>
 * </ul>
 */


public class __G10__RottingOranges {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;

        int rows = grid.length;
        int cols = grid[0].length;

        Deque<int[]> q = new ArrayDeque<>();

        int freshCnt = 0;

        // calculate the fresh count & initialize each rotten __1__ into the queue to start bfs

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    q.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshCnt++;
                }
            }
        }

        if (freshCnt == 0) return 0;

        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int minutes = 0;

        // run bfs here
        while (!q.isEmpty()) {
            int size = q.size();
            boolean rottenInThisRound = false;

            for (int i = 0; i < size; i++) {
                int[] pos = q.poll();
                int r = pos[0], c = pos[1];

                for (int[] d : direction) {
                    int newRow = r + d[0], newCol = c + d[1];

                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;
                        q.offer(new int[]{newRow, newCol});
                        freshCnt--;
                        rottenInThisRound = true;
                    }
                }
            }

            if (rottenInThisRound) {
                minutes++;
            }
        }

        return freshCnt == 0 ? minutes : -1;
    }
}
