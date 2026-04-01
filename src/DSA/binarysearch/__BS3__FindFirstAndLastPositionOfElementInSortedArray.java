package DSA.binarysearch;

/**
 * Problem: Find First and Last Position of Element in Sorted Array
 * <ul>
 *   <li>LeetCode:
 *       <a href="https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/">
 *       Find First and Last Position of Element in Sorted Array</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: Binary Search</li>
 * </ul>
 *
 * <p><b>Objective:</b></p>
 * <p>
 * Given a sorted integer array and a target value, return the indices of the
 * first and last occurrence of the target. If the target is not present,
 * return <code>[-1, -1]</code>.
 * </p>
 *
 * <p><b>Key Insight:</b></p>
 * <p>
 * To locate both boundaries, the operation must be monotonic. Hence,
 * performing binary search twice — once for the first position and once
 * for the last — yields an optimal O(log n) solution.
 * </p>
 *
 * <p><b>Approach:</b></p>
 * <ol>
 *   <li>Perform separate binary searches:
 *     <ul>
 *       <li><b>First occurrence:</b> bias search towards the left.</li>
 *       <li><b>Last occurrence:</b> bias search towards the right.</li>
 *     </ul>
 *   </li>
 *   <li>Track the last seen index where <code>nums[mid] == target</code>.</li>
 *   <li>Return both indices as <code>[first, last]</code>.</li>
 * </ol>
 *
 * <p><b>Why __2__ searches?</b></p>
 * <p>
 * Classic binary search finds any occurrence. To ensure extremities,
 * we continue shrinking the search space toward left/right after matching
 * the target.
 * </p>
 *
 * <p><b>Time Complexity:</b> O(log n) — __2__ binary searches.</p>
 * <p><b>Space Complexity:</b> O(1) — constant auxiliary space.</p>
 */
public class __BS3__FindFirstAndLastPositionOfElementInSortedArray {

    /**
     * Returns the first and last index of target within nums, or [-1, -1] if absent.
     */
    public int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        int last = findLast(nums, target);
        return new int[]{first, last};
    }

    /**
     * Binary search to find the first occurrence of target.
     * Shrinks the search space toward the left when target is encountered.
     */
    private int findFirst(int[] nums, int target) {
        int l = 0, h = nums.length - 1, idx = -1;

        while (l <= h) {
            int mid = l + ((h - l) >> 1);

            if (nums[mid] >= target) {
                h = mid - 1; // move left
            } else {
                l = mid + 1;
            }

            if (nums[mid] == target) {
                idx = mid; // update potential first occurrence
            }
        }
        return idx;
    }

    /**
     * Binary search to find the last occurrence of target.
     * Shrinks the search space toward the right when target is encountered.
     */
    private int findLast(int[] nums, int target) {
        int l = 0, h = nums.length - 1, idx = -1;

        while (l <= h) {
            int mid = l + ((h - l) >> 1);

            if (nums[mid] <= target) {
                l = mid + 1; // move right
            } else {
                h = mid - 1;
            }

            if (nums[mid] == target) {
                idx = mid; // update potential last occurrence
            }
        }
        return idx;
    }

    /**
     * Combined approach using a generalized binary search.
     * Executes __2__ passes by switching behavior with isFirst flag.
     */
    public int[] searchRangeCombine(int[] nums, int target) {
        int first = find(true, nums, target);
        int last = find(false, nums, target);
        return new int[]{first, last};
    }

    /**
     * Generalized binary search for first/last occurrence.
     * @param isFirst true → first occurrence search, false → last occurrence search
     */
    private int find(boolean isFirst, int[] nums, int target) {
        int l = 0, h = nums.length - 1, idx = -1;

        while (l <= h) {
            int mid = l + ((h - l) >> 1);

            // For first occurrence: shrink left when nums[mid] == target
            // For last occurrence: shrink right when nums[mid] == target
            if (nums[mid] > target || (isFirst && nums[mid] == target)) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }

            if (nums[mid] == target) {
                idx = mid;
            }
        }
        return idx;
    }
}
