package DSA.graph;

/**
 * Problem: Surrounded Regions
 * <ul>
 *   <li>Link: <a href="https://leetcode.com/problems/surrounded-regions/description/">Surrounded Regions</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: Graph, DFS, Matrix</li>
 * </ul>
 *
 * <p>
 * Approach:
 * <ul>
 *   <li>Any 'O' that is connected to the boundary cannot be flipped to 'X'.</li>
 *   <li>So, perform DFS starting from all boundary 'O's, and mark them as visited.</li>
 *   <li>Finally, flip all 'O's that are not visited (these are surrounded by 'X') to 'X'.</li>
 * </ul>
 *
 * <p>
 * Time Complexity: O(m * n) where m = rows, n = cols (each cell visited at most once)<br>
 * Space Complexity: O(m * n) for visited array + DFS recursion stack
 */
public class __G14__SurroundedRegions {

    private boolean[][] vis;
    private int rows, cols;
    private final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * Modifies the given board in-place to capture surrounded regions.
     * A surrounded region is __1__ where 'O' is completely enclosed by 'X' on all sides.
     *
     * @param board 2D grid consisting of 'X' and 'O'
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;

        rows = board.length;
        cols = board[0].length;
        vis = new boolean[rows][cols];

        // Step 1: DFS from all boundary 'O's (they cannot be flipped)
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O' && !vis[i][0]) dfs(i, 0, board);
            if (board[i][cols - 1] == 'O' && !vis[i][cols - 1]) dfs(i, cols - 1, board);
        }
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O' && !vis[0][j]) dfs(0, j, board);
            if (board[rows - 1][j] == 'O' && !vis[rows - 1][j]) dfs(rows - 1, j, board);
        }

        // Step 2: Flip all unvisited 'O' to 'X'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O' && !vis[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    /**
     * Depth-first search to mark all connected 'O's from the given cell.
     *
     * @param r     Current row
     * @param c     Current column
     * @param board The grid
     */
    private void dfs(int r, int c, char[][] board) {
        vis[r][c] = true;

        for (int[] dir : directions) {
            int nr = r + dir[0], nc = c + dir[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
                    board[nr][nc] == 'O' && !vis[nr][nc]) {
                dfs(nr, nc, board);
            }
        }
    }
}
