package DSA.binarysearch;

/**
 * Problem: Single Element in a Sorted Array
 * <ul>
 *   <li>Link: <a href="https://leetcode.com/problems/single-element-in-a-sorted-array/description/">
 *       Single Element in a Sorted Array</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: binarysearch</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * You are given a sorted array where every element appears exactly twice,
 * except for __1__ element which appears only once.
 * Find and return that single element in O(log n) time.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Because the array is sorted and elements appear in pairs:
 *     <ul>
 *       <li>Before the single element, pairs start at even indices.</li>
 *       <li>After the single element, pairs start at odd indices.</li>
 *     </ul>
 *   </li>
 *   <li>Use binary search:
 *     <ul>
 *       <li>Force <code>mid</code> to be even (if odd, subtract 1).</li>
 *       <li>If <code>nums[mid] == nums[mid+1]</code>, the single element lies on the right side → move <code>left = mid + 2</code>.</li>
 *       <li>Else, single element lies on the left side (including <code>mid</code>) → move <code>right = mid</code>.</li>
 *     </ul>
 *   </li>
 *   <li>Loop ends when <code>left == right</code>, that index holds the single element.</li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(log n) — binary search halving the range each time.</li>
 *   <li><b>Space:</b> O(1) — only pointers used.</li>
 * </ul>
 */
public class __BS8__SingleElementInSortedArray {

    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) >> 1;

            // ensure mid is even
            if ((mid & 1) == 1) {
                mid--;
            }

            // if pair is intact, answer lies on right
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                // single element lies on left half (including mid)
                right = mid;
            }
        }

        return nums[left];
    }
}
