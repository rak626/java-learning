package DSA.binarysearch;

/**
 * Problem: Search in a Row-wise and Column-wise Sorted 2D Matrix
 * <ul>
 *   <li>LeetCode: <a href="https://leetcode.com/problems/search-a-2d-matrix-ii/">
 *       Search a 2D Matrix II</a></li>
 *   <li>GFG: <a href="https://www.geeksforgeeks.org/search-in-a-row-wise-and-column-wise-sorted-matrix/">
 *       Search in a row-wise and column-wise sorted matrix</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: binary-search, matrix</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * Given a 2D matrix where:
 * <ul>
 *   <li>Each row is sorted in ascending order (left → right)</li>
 *   <li>Each column is sorted in ascending order (top → bottom)</li>
 * </ul>
 * Determine whether a given target value exists in the matrix.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Start from the <b>top-right corner</b> of the matrix.</li>
 *   <li>If the current value equals the target → return true.</li>
 *   <li>If the current value is greater than the target → move left.</li>
 *   <li>If the current value is smaller than the target → move down.</li>
 *   <li>This works because:
 *     <ul>
 *       <li>Moving left decreases values</li>
 *       <li>Moving down increases values</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <h2>Algorithm:</h2>
 * <ul>
 *   <li>Initialize row = 0 and col = last column index.</li>
 *   <li>While row < number of rows and col ≥ 0:</li>
 *   <ul>
 *     <li>If matrix[row][col] == target → return true.</li>
 *     <li>If matrix[row][col] > target → col--.</li>
 *     <li>Else → row++.</li>
 *   </ul>
 *   <li>If traversal ends, target does not exist → return false.</li>
 * </ul>
 *
 * <h2>Edge Cases:</h2>
 * <ul>
 *   <li>Empty matrix</li>
 *   <li>Single row or single column</li>
 *   <li>Target smaller than minimum element</li>
 *   <li>Target larger than maximum element</li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(n + m)</li>
 *   <li><b>Space:</b> O(1)</li>
 * </ul>
 */
public class __BS25__SearchInA2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {

        // Defensive check
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int r = 0;
        int c = cols - 1;

        while (r < rows && c >= 0) {
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] > target) {
                c--;        // move left
            } else {
                r++;        // move down
            }
        }

        return false;
    }
}
