package DSA.binarysearch;

import java.util.Arrays;

/**
 * Problem: Find a Peak Element II
 * <ul>
 *   <li>LeetCode:
 *     <a href="https://leetcode.com/problems/find-a-peak-element-ii/">
 *       Find a Peak Element II</a>
 *   </li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: binary-search, matrix</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * Given a 2D matrix <code>mat</code> where:
 * <ul>
 *   <li>All adjacent cells are distinct</li>
 *   <li>A cell has at most four neighbors (up, down, left, right)</li>
 * </ul>
 * A position <code>(i, j)</code> is called a <b>peak</b> if its value is
 * strictly greater than all of its adjacent neighbors.
 * </p>
 *
 * <p>
 * You must return the position of <b>any one peak</b>.
 * It is guaranteed that at least one peak exists.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Apply <b>binary search on columns</b>.</li>
 *   <li>For a chosen middle column:
 *     <ul>
 *       <li>Find the row containing the maximum element in that column.</li>
 *       <li>This element is the best candidate for a peak in that column.</li>
 *     </ul>
 *   </li>
 *   <li>
 *     Compare this element with its left and right neighbors:
 *     <ul>
 *       <li>If it is greater than both → it is a peak.</li>
 *       <li>If the left neighbor is larger → move search to the left columns.</li>
 *       <li>Otherwise → move search to the right columns.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <h2>Algorithm:</h2>
 * <ul>
 *   <li>Initialize <code>left = 0</code> and <code>right = number of columns - 1</code>.</li>
 *   <li>While <code>left ≤ right</code>:
 *     <ul>
 *       <li>Compute <code>midCol</code>.</li>
 *       <li>Find the row index of the maximum element in <code>midCol</code>.</li>
 *       <li>Compare it with left and right neighbors.</li>
 *       <li>Move the binary search accordingly.</li>
 *     </ul>
 *   </li>
 *   <li>Return the peak position when found.</li>
 * </ul>
 *
 * <h2>Edge Cases:</h2>
 * <ul>
 *   <li>Single row matrix</li>
 *   <li>Single column matrix</li>
 *   <li>Peak at boundary (first or last column)</li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(m log n)</li>
 *   <li><b>Space:</b> O(1)</li>
 * </ul>
 */
public class __BS26__FindPeakElementII {

    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int left = 0, right = n - 1;

        while (left <= right) {
            int midCol = left + (right - left) / 2;

            // Find the row index of the maximum element in mid-column
            int maxRow = 0;
            for (int i = 1; i < m; i++) {
                if (mat[i][midCol] > mat[maxRow][midCol]) {
                    maxRow = i;
                }
            }

            int leftVal = midCol > 0 ? mat[maxRow][midCol - 1] : -1;
            int rightVal = midCol < n - 1 ? mat[maxRow][midCol + 1] : -1;

            // Check peak condition
            if (mat[maxRow][midCol] > leftVal &&
                    mat[maxRow][midCol] > rightVal) {
                return new int[]{maxRow, midCol};
            }

            // Move towards the larger neighbor
            if (leftVal > mat[maxRow][midCol]) {
                right = midCol - 1;
            } else {
                left = midCol + 1;
            }
        }

        // Guaranteed not to reach here as a peak always exists
        return new int[]{-1, -1};
    }
}
