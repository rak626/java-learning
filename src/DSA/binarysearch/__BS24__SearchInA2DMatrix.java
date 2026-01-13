package DSA.binarysearch;

/**
 * Problem: Search in a 2D Matrix
 * <ul>
 *   <li>LeetCode: <a href="https://leetcode.com/problems/search-a-2d-matrix/">
 *       Search a 2D Matrix</a></li>
 *   <li>GFG Variant: <a href="https://www.geeksforgeeks.org/search-in-a-row-wise-and-column-wise-sorted-matrix/">
 *       Search in a 2D Matrix</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: binarysearch, matrix</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * Given a 2D matrix where:
 * <ul>
 *   <li>Each row is sorted in ascending order</li>
 *   <li>The first element of each row is greater than the last element of the previous row</li>
 * </ul>
 * determine whether a given target value exists in the matrix.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>The matrix can be treated as a single sorted 1D array.</li>
 *   <li>Index mapping:
 *     <pre>
 *     row = mid / numberOfColumns
 *     col = mid % numberOfColumns
 *     </pre>
 *   </li>
 *   <li>Apply standard binary search on the virtual 1D array.</li>
 *   <li>This avoids extra space and keeps the solution optimal.</li>
 * </ul>
 *
 * <h2>Algorithm:</h2>
 * <ul>
 *   <li>Let total elements = m × n.</li>
 *   <li>Initialize binary search range: <code>low = 0</code>, <code>high = m*n - 1</code>.</li>
 *   <li>While <code>low ≤ high</code>:
 *     <ul>
 *       <li>Compute mid index.</li>
 *       <li>Map mid to matrix indices using division and modulo.</li>
 *       <li>Compare matrix value with target and adjust search space.</li>
 *     </ul>
 *   </li>
 *   <li>If target is found, return <code>true</code>; otherwise return <code>false</code>.</li>
 * </ul>
 *
 * <h2>Edge Cases:</h2>
 * <ul>
 *   <li>Single row matrix</li>
 *   <li>Single column matrix</li>
 *   <li>Target smaller than the smallest element</li>
 *   <li>Target larger than the largest element</li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(log(m × n))</li>
 *   <li><b>Space:</b> O(1)</li>
 * </ul>
 */
public class __BS24__SearchInA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;

        int l = 0, h = m * n - 1;

        while (l <= h) {
            int mid = l + ((h - l) >> 1);

            int r = mid / n;
            int c = mid % n;

            if (matrix[r][c] == target) return true;
            else if (matrix[r][c] < target) l = mid + 1;
            else h = mid - 1;
        }
        return false;
    }
}
