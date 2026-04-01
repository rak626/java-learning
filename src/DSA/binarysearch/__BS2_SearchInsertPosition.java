package DSA.binarysearch;

/**
 * Problem: Search Insert Position
 * <ul>
 *   <li>LeetCode: <a href="https://leetcode.com/problems/search-insert-position/description/">
 *       Search Insert Position</a></li>
 *   <li>Difficulty: Easy</li>
 *   <li>Tags: Binary Search</li>
 * </ul>
 *
 * <p><b>Objective:</b></p>
 * <p>
 * Given a sorted integer array and a target value, return the index if the target
 * is found. If not, return the index where it should be inserted while maintaining
 * sorted order.
 * </p>
 *
 * <p><b>Key Insight:</b></p>
 * <p>
 * Since the array is sorted, binary search provides an optimal O(log n) solution.
 * Upon termination, the left pointer <code>l</code> automatically represents the
 * correct insertion index for the target.
 * </p>
 *
 * <p><b>Approach:</b></p>
 * <ol>
 *   <li>Initialize __2__ pointers: <code>l = 0</code>, <code>h = nums.length - 1</code>.</li>
 *   <li>Loop while <code>l <= h</code>:</li>
 *   <ul>
 *       <li>Compute <code>mid = l + (h - l) / 2</code> (overflow-safe form).</li>
 *       <li>If <code>nums[mid] == target</code>, return <code>mid</code>.</li>
 *       <li>If <code>nums[mid] &lt; target</code>, move right: <code>l = mid + 1</code>.</li>
 *       <li>Else move left: <code>h = mid - 1</code>.</li>
 *   </ul>
 *   <li>When the loop exits, <code>l</code> is the correct insertion position.</li>
 * </ol>
 *
 * <p><b>Why return <code>l</code>?</b></p>
 * <p>
 * Binary search narrows down the search space until <code>l</code> overtakes <code>h</code>.
 * At that point, <code>l</code> points to the smallest index where the target can be placed
 * without violating the sorted order.
 * </p>
 *
 * <p><b>Time Complexity:</b> O(log n) — halves the search space at each step.</p>
 * <p><b>Space Complexity:</b> O(1) — constant auxiliary memory.</p>
 */
public class __BS2_SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        int l = 0, h = nums.length - 1;

        while (l <= h) {
            int mid = l + ((h - l) >> 1); // overflow-safe midpoint

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        return l; // correct insertion index
    }
}
