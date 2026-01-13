package DSA.binarysearch;

/**
 * Problem: K-th Element of Two Sorted Arrays
 * <ul>
 *   <li>LeetCode variant: <a href="https://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/">
 *       GFG - K-th Element of Two Sorted Arrays</a></li>
 *   <li>Difficulty: Hard</li>
 *   <li>Tags: binarysearch, partition, array</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * Given two sorted arrays <code>nums1</code> and <code>nums2</code> and an integer <code>k</code>,
 * return the k-th smallest element in the combined sorted array.
 * Do this in O(log(min(m, n))) time without merging the arrays.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Binary search on the smaller array (like Median of Two Sorted Arrays).</li>
 *   <li>Partition nums1 at i, nums2 at j such that <code>i + j = k</code>.</li>
 *   <li>Check partition validity:
 *     <ul>
 *       <li>nums1[i-1] ≤ nums2[j] and nums2[j-1] ≤ nums1[i]</li>
 *       <li>If valid → return max(nums1[i-1], nums2[j-1])</li>
 *     </ul>
 *   </li>
 *   <li>If invalid → move search space accordingly:
 *     <ul>
 *       <li>nums1[i-1] > nums2[j] → decrease i (right = i-1)</li>
 *       <li>nums2[j-1] > nums1[i] → increase i (left = i+1)</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(log(min(m, n)))</li>
 *   <li><b>Space:</b> O(1)</li>
 * </ul>
 */
public class __BS22__KthElementTwoSortedArrays {

    public int kthElement(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        // Always binary search on smaller array
        if (m > n) return kthElement(nums2, nums1, k);

        /** Theory
         * we know i (mid) will be in between 0 <= i <= m;
         * & we know j means k - i & j will be always 0 <= j <= n;
         * just use k - i instead of j,
         * so, 0 <= k - i <= n
         * k -i >= 0 , so i <= k and, k - i <= n, so i >= k;
         * so k - n <= i <= k,
         * combining with 0 <= i <= m
         * max(0, k -n) <= i <= max(k , m)
         */
        int left = Math.max(0, k - n);
        int right = Math.min(k, m);

        while (left <= right) {
            int i = left + (right - left) / 2;
            int j = k - i;

            int aLeft = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int aRight = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int bLeft = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int bRight = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (aLeft <= bRight && bLeft <= aRight) {
                return Math.max(aLeft, bLeft);
            } else if (aLeft > bRight) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }

        throw new IllegalArgumentException("k is out of bounds");
    }
}
