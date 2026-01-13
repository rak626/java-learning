package DSA.binarysearch;


/**
 * Problem: Row with Maximum 1s in a Binary Matrix
 * <ul>
 *   <li>GFG: <a href="https://practice.geeksforgeeks.org/problems/row-with-max-1s0023/1">
 *       Row with Max 1s</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: binarysearch, matrix, array</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * Given a binary matrix where each row is sorted in non-decreasing order
 * (all 0s come before all 1s), return the index of the row that contains
 * the maximum number of 1s.
 * If no row contains any 1, return -1.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Each row is sorted, so all 1s appear after all 0s.</li>
 *   <li>If we find the index of the first 1 in a row, the number of 1s is:
 *     <pre>numberOf1s = totalColumns - firstIndexOf1</pre>
 *   </li>
 *   <li>The row with the leftmost 1 will always have the maximum number of 1s.</li>
 *   <li>Use Binary Search (Lower Bound) on each row to find the first occurrence of 1.</li>
 * </ul>
 *
 * <h2>Algorithm:</h2>
 * <ul>
 *   <li>Initialize <code>maxCount = 0</code> and <code>answerRow = -1</code>.</li>
 *   <li>For each row:
 *     <ul>
 *       <li>Find the first index where value is 1 using lower bound.</li>
 *       <li>If such an index exists:
 *         <ul>
 *           <li>Compute number of 1s.</li>
 *           <li>Update answer if this count is greater than current maximum.</li>
 *         </ul>
 *       </li>
 *     </ul>
 *   </li>
 *   <li>Return the row index with maximum 1s.</li>
 * </ul>
 *
 * <h2>Edge Cases:</h2>
 * <ul>
 *   <li>All rows contain only 0s → return -1</li>
 *   <li>All rows contain only 1s → return 0 (first row)</li>
 *   <li>Single row or single column matrix</li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(m log n), where m = number of rows, n = number of columns</li>
 *   <li><b>Space:</b> O(1)</li>
 * </ul>
 *
 * <p><b>Note:</b> This problem can be optimized to O(m + n) using a top-right
 * corner traversal approach.</p>
 */

public class __BS23__RowWithMaxNoOfOnes {
    public int rowWithMax1s(int arr[][]) {
        int m = arr.length;
        int n = arr[0].length;

        int maxCnt = 0;
        int row = -1;

        for (int i = 0; i < m; i++) {
            int firstOneIndex = lowerBound(arr[i]);

            if (firstOneIndex < n) {
                int countOnes = n - firstOneIndex;
                if (countOnes > maxCnt) {
                    maxCnt = countOnes;
                    row = i;
                }
            }
        }
        return row;
    }

    private int lowerBound(int[] a) {
        int l = 0, h = a.length - 1;
        int ans = a.length;

        while (l <= h) {
            int mid = l + (h - l) / 2;

            if (a[mid] >= 1) {
                ans = mid;
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
