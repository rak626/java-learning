package DSA.binarysearch;

/**
 * Problem: Median of Two Sorted Arrays
 * <ul>
 *   <li>LeetCode: <a href="https://leetcode.com/problems/median-of-two-sorted-arrays/">
 *       Median of Two Sorted Arrays</a></li>
 *   <li>Difficulty: Hard</li>
 *   <li>Tags: binarysearch, divide-and-conquer, array</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * Given __2__ sorted arrays <code>nums1</code> and <code>nums2</code>, return the median
 * of the combined sorted array in O(log(min(m, n))) time.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Use binary search on the smaller array to find a partition:
 *     <ul>
 *       <li>Partition nums1 at i, nums2 at j such that:
 *           <code>i + j = (m + n + 1)/2</code> → left halves contain half of total elements.</li>
 *       <li>Check: maxLeft1 ≤ minRight2 and maxLeft2 ≤ minRight1 → valid partition.</li>
 *       <li>If not, move binary search in smaller array accordingly.</li>
 *     </ul>
 *   </li>
 *   <li>Median:
 *     <ul>
 *       <li>If total length odd → max(maxLeft1, maxLeft2)</li>
 *       <li>If even → average of max(maxLeft1, maxLeft2) and min(minRight1, minRight2)</li>
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
public class __BS21__MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        int half = (total + 1) / 2;

        int left = 0, right = m;

        while (left <= right) {
            int i = (left + right) / 2;  // cut in nums1
            int j = half - i;            // cut in nums2

            int aLeft = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int aRight = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int bLeft = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int bRight = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (aLeft <= bRight && bLeft <= aRight) {
                if (total % 2 == 1) {
                    return Math.max(aLeft, bLeft);
                } else {
                    return (Math.max(aLeft, bLeft) + Math.min(aRight, bRight)) / 2.0;
                }
            } else if (aLeft > bRight) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted correctly.");
    }
}
