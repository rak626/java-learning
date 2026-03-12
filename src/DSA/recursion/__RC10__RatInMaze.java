package DSA.recursion;

import java.util.ArrayList;

/**
 * Problem: Rat In Maze
 * <ul>
 * <li>Link: <a href="https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1">Rat In Maze</a></li>
 * <li>Difficulty: Medium</li>
 * <li>Tags: recursion</li>
 * </ul>
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O(4^(n²))
 * Space: O(n²)
 */
public class __RC10__RatInMaze {

    private final int[] dx = {1, 0, 0, -1};   // D L R U
    private final int[] dy = {0, -1, 1, 0};
    private final char[] dir = {'D', 'L', 'R', 'U'};

    public ArrayList<String> ratInMaze(int[][] maze) {
        int n = maze.length;
        ArrayList<String> res = new ArrayList<>();
        if (maze[0][0] == 0) return res;
        boolean[][] vis = new boolean[n][n];
        backtrack(0, 0, maze, vis, new StringBuilder(), res, n);
        return res;
    }

    private void backtrack(int r, int c, int[][] maze, boolean[][] vis, StringBuilder path, ArrayList<String> res, int n) {
        if (r == n - 1 && c == n - 1) {
            res.add(path.toString());
            return;
        }
        vis[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];
            if (isValid(nr, nc, maze, vis, n)) {
                path.append(dir[i]);
                backtrack(nr, nc, maze, vis, path, res, n);
                path.deleteCharAt(path.length() - 1); // backtrack
            }
        }
        vis[r][c] = false;
    }

    private boolean isValid(int r, int c, int[][] maze, boolean[][] vis, int n) {
        return r >= 0 && c >= 0 && r < n && c < n && maze[r][c] == 1 && !vis[r][c];
    }
}