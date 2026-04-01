package DSA.binarysearch;

/**
 * Problem: Nth Root of an Integer
 * <ul>
 *   <li>Link: <a href="https://www.geeksforgeeks.org/nth-root-number/">
 *       Nth Root of a Number</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: binarysearch, math</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * Given __2__ integers <code>n</code> and <code>m</code>, find an integer <code>x</code> such that:
 * <br>
 * <code>x<sup>n</sup> = m</code>.
 * <br>
 * If no such integer exists, return <code>-1</code>.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Use binary search over the range [1, m].</li>
 *   <li>For each mid:
 *     <ul>
 *       <li>Compute <code>mid^n</code> using fast multiplication (to avoid overflow).</li>
 *       <li>If <code>mid^n == m</code> → return mid (found root).</li>
 *       <li>If <code>mid^n &lt; m</code> → root must be larger → move <code>l = mid + 1</code>.</li>
 *       <li>Else → root must be smaller → move <code>h = mid - 1</code>.</li>
 *     </ul>
 *   </li>
 *   <li>Return -1 if no exact root exists.</li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(n log m) in worst case (since each power check takes O(n)).</li>
 *   <li><b>Space:</b> O(1).</li>
 * </ul>
 */
public class __BS11__NthRootOfNumber {

    public int nthRoot(int n, int m) {
        if (m == 0 || m == 1) return m;

        int l = 1, h = m;

        while (l <= h) {
            int mid = l + ((h - l) >> 1);

            int val = fastMul(mid, n, m);

            if (val == m) {
                return mid;
            } else if (val < m) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        return -1; // no integer nth root exists
    }

    /**
     * Fast multiplication: computes base^exp but stops if result exceeds limit.
     * Prevents unnecessary overflow.
     */
    private int fastMul(int base, int exp, int limit) {
        long result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
            if (result > limit) return (int) result;
        }
        return (int) result;
    }
}
