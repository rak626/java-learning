package DSA.binarysearch;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Median in a Row-wise Sorted Matrix
 * <ul>
 *   <li>GeeksForGeeks:
 *     <a href="https://www.geeksforgeeks.org/problems/median-in-a-row-wise-sorted-matrix1527/1">
 *       Median in a Row-wise Sorted Matrix</a>
 *   </li>
 *   <li>Difficulty: Hard</li>
 *   <li>Tags: binary-search, matrix</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * Given a row-wise sorted matrix <code>mat</code> of size <code>m × n</code>,
 * find the median of all elements present in the matrix.
 * </p>
 *
 * <p>
 * The median is defined as the element such that exactly
 * <code>(m × n) / 2</code> elements are less than or equal to it.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Apply <b>binary search on the value range</b> instead of indices.</li>
 *   <li>The minimum possible value is the smallest element of any row.</li>
 *   <li>The maximum possible value is the largest element of any row.</li>
 *   <li>
 *     For a guessed value <code>mid</code>, count how many elements in the matrix
 *     are ≤ <code>mid</code> using <b>upper bound</b> on each row.
 *   </li>
 *   <li>
 *     If the count ≤ <code>(m × n) / 2</code>, search on the right half,
 *     otherwise search on the left half.
 *   </li>
 * </ul>
 *
 * <h2>Algorithm:</h2>
 * <ul>
 *   <li>Initialize <code>low</code> and <code>high</code> using matrix boundaries.</li>
 *   <li>While <code>low ≤ high</code>:
 *     <ul>
 *       <li>Compute <code>mid</code>.</li>
 *       <li>Count elements ≤ <code>mid</code>.</li>
 *       <li>Adjust search space accordingly.</li>
 *     </ul>
 *   </li>
 *   <li>Return <code>low</code> as the median.</li>
 * </ul>
 *
 * <h2>Edge Cases:</h2>
 * <ul>
 *   <li>Single row matrix</li>
 *   <li>Single column matrix</li>
 *   <li>Matrix with duplicate elements</li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(m · log n · log(max − min))</li>
 *   <li><b>Space:</b> O(1)</li>
 * </ul>
 */
public class __BS27__MedianInARowWiseSortedMatrix {

    public int median(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // Find minimum and maximum elements in the matrix
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        for (int[] row : mat) {
            low = Math.min(low, row[0]);
            high = Math.max(high, row[n - 1]);
        }

        int requiredCount = (m * n) / 2;

        // Binary search on answer space
        while (low <= high) {
            int mid = low + (high - low) / 2;

            int count = countLessEqual(mat, mid);

            if (count <= requiredCount) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    // Counts elements <= target using upper bound on each row
    private int countLessEqual(int[][] mat, int target) {
        int count = 0;
        for (int[] row : mat) {
            count += upperBound(row, target);
        }
        return count;
    }

    // Standard upper bound: first index with value > target
    private int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
