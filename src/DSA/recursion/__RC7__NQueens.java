package DSA.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: N Queens
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/n-queens/description/">N Queens</a></li>
 * <li>Difficulty: Hard</li>
 * <li>Tags: recursion</li>
 * </ul>
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __RC7__NQueens {
    public List<List<String>> solveNQueens(int n) {
        char[][] mat = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = '.';
            }
        }

        List<List<String>> res = new ArrayList<>();
        solve(0, n, mat, res);
        return res;
    }

    private void solve(int row, int n, char[][] mat, List<List<String>> res) {

        if (row == n) {
            res.add(buildBoard(mat));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isPossible(row, col, n, mat)) {
                mat[row][col] = 'Q';
                solve(row + 1, n, mat, res);
                mat[row][col] = '.';
            }
        }
    }

    private boolean isPossible(int row, int col, int n, char[][] mat) {
        // from cur row, to upwards
        for (int i = 0; i < row; i++) {
            if (mat[i][col] == 'Q')
                return false;
        }

        // left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (mat[i][j] == 'Q')
                return false;
        }

        // right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (mat[i][j] == 'Q') return false;
        }
        return true;
    }

    private List<String> buildBoard(char[][] mat) {
        List<String> cur = new ArrayList<>();

        for (var m : mat) {
            cur.add(new String(m));
        }
        return cur;
    }
}

