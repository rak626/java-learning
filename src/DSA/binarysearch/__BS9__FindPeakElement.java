package DSA.binarysearch;

/**
 * Problem: Find Peak Element
 * <ul>
 *   <li>Link: <a href="https://leetcode.com/problems/find-peak-element/description/">
 *       Find Peak Element</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: binarysearch, array</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * A peak element is __1__ that is strictly greater than its neighbors.
 * Given an array <code>nums</code>, return the index of any peak element.
 * You may assume <code>nums[-1] = nums[n] = -∞</code>.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Check simple boundary cases:
 *     <ul>
 *       <li>If array has __1__ element → return index 0.</li>
 *       <li>If first element is greater than second → return index 0.</li>
 *       <li>If last element is greater than second last → return index n-1.</li>
 *     </ul>
 *   </li>
 *   <li>Use binary search on range [1, n-2]:
 *     <ul>
 *       <li>If <code>nums[mid]</code> is greater than both neighbors → it's a peak.</li>
 *       <li>If <code>nums[mid] &gt; nums[mid-1]</code>, peak lies on the right side → move <code>l = mid + 1</code>.</li>
 *       <li>Else, peak lies on the left side → move <code>h = mid - 1</code>.</li>
 *     </ul>
 *   </li>
 *   <li>Loop guarantees finding a peak since array has at least __1__.</li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(log n) — binary search halves the range each step.</li>
 *   <li><b>Space:</b> O(1) — only pointers used.</li>
 * </ul>
 */
public class __BS9__FindPeakElement {

    public int findPeakElement(int[] nums) {
        int n = nums.length;

        // boundary checks
        if (n == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[n - 1] > nums[n - 2]) return n - 1;

        int l = 1, h = n - 2;

        while (l <= h) {
            int mid = (l + h) >> 1;

            // check if mid is a peak
            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            // if rising slope, move right
            else if (nums[mid - 1] < nums[mid]) {
                l = mid + 1;
            }
            // else move left
            else {
                h = mid - 1;
            }
        }

        return -1; // shouldn't reach here (guaranteed peak exists)
    }
}
