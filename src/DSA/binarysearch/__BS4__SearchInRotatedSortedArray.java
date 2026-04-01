package DSA.binarysearch;

/**
 * Problem: Search in Rotated Sorted Array
 * <ul>
 *   <li>Link: <a href="https://leetcode.com/problems/search-in-rotated-sorted-array/description/">
 *       Search in Rotated Sorted Array</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: binarysearch</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * You are given an integer array <code>nums</code> that is sorted in ascending order
 * but rotated at an unknown pivot. Search for a given <code>target</code> in O(log n) time.
 * If found, return its index. Otherwise, return -1.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Since the array is rotated, at least __1__ half (left or right) of the current range will always be sorted.</li>
 *   <li>Perform a modified binary search:
 *     <ol>
 *       <li>Find <code>mid</code>.</li>
 *       <li>If <code>nums[mid] == target</code>, return <code>mid</code>.</li>
 *       <li>If left half (<code>nums[l]...nums[mid]</code>) is sorted:
 *         <ul>
 *           <li>If <code>target</code> lies in this range → search left (<code>h = mid - 1</code>).</li>
 *           <li>Else → search right (<code>l = mid + 1</code>).</li>
 *         </ul>
 *       </li>
 *       <li>Otherwise, right half (<code>nums[mid]...nums[h]</code>) is sorted:
 *         <ul>
 *           <li>If <code>target</code> lies in this range → search right (<code>l = mid + 1</code>).</li>
 *           <li>Else → search left (<code>h = mid - 1</code>).</li>
 *         </ul>
 *       </li>
 *     </ol>
 *   </li>
 *   <li>Continue until the search space is empty.</li>
 *   <li>If not found, return -1.</li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(log n) → Binary search on rotated array.</li>
 *   <li><b>Space:</b> O(1) → Constant extra space.</li>
 * </ul>
 */
public class __BS4__SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        int l = 0, h = nums.length - 1;

        while (l <= h) {
            int mid = l + ((h - l) >> 1); // prevents overflow

            if (nums[mid] == target) return mid;

            // Left half is sorted
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target <= nums[mid]) {
                    h = mid - 1; // search left
                } else {
                    l = mid + 1; // search right
                }
            }
            // Otherwise, right half is sorted
            else {
                if (nums[mid] <= target && target <= nums[h]) {
                    l = mid + 1; // search right
                } else {
                    h = mid - 1; // search left
                }
            }
        }

        return -1; // target not found
    }
}
