package DSA.recursion;

/**
 * Problem: Sudoku Solver
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/sudoku-solver/description/">Sudoku Solver</a></li>
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
public class __RC8__SudokuSolver {

    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    for (char ch = '1'; ch <= '9'; ch++) {
                        if (isValid(board, row, col, ch)) {
                            board[row][col] = ch;
                            if (solve(board)) {
                                return true;
                            }
                            board[row][col] = '.'; // backtrack
                        }
                    }
                    return false; // no valid number found
                }
            }
        }
        return true; // board completely filled
    }

    private boolean isValid(char[][] board, int row, int col, char ch) {

        for (int i = 0; i < 9; i++) {
            // row check
            if (board[row][i] == ch) return false;
            // column check
            if (board[i][col] == ch) return false;
            // 3x3 subgrid check
            int r = 3 * (row / 3) + i / 3; // i/3 give the row pos in 3/3 grid, 3*(row/3) give the which subgrid to pick
            int c = 3 * (col / 3) + i % 3;

            if (board[r][c] == ch) return false;
        }

        return true;
    }
}

