package DSA.binarysearch;

import java.util.Arrays;

/**
 * Problem: Split Array Largest Sum
 * <ul>
 *   <li>LeetCode: <a href="https://leetcode.com/problems/split-array-largest-sum/">
 *       Split Array Largest Sum</a></li>
 *   <li>Difficulty: Hard</li>
 *   <li>Tags: binarysearch, greedy, partition</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * Given an integer array <code>nums</code> and an integer <code>k</code>, split the array into
 * <b>k or fewer non-empty continuous subarrays</b> such that the <b>largest subarray sum</b> is minimized.
 * Return this minimized largest sum.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>This is a "partition + minimize max load" problem → solved with <b>Binary Search on Answer</b>.</li>
 *   <li>Search space:
 *     <ul>
 *       <li><b>Low</b> = max(nums) → at least __1__ subarray must hold the largest element.</li>
 *       <li><b>High</b> = sum(nums) → __1__ subarray takes the entire array.</li>
 *     </ul>
 *   </li>
 *   <li>Feasibility check (<code>mid</code> = candidate max sum):
 *     <ul>
 *       <li>Greedily group elements into subarrays.</li>
 *       <li>If adding an element exceeds <code>mid</code>, start a new subarray.</li>
 *       <li>Count subarrays required.</li>
 *     </ul>
 *   </li>
 *   <li>If subarrays ≤ k → feasible → shrink right.</li>
 *   <li>If subarrays > k → infeasible → expand left.</li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(n log(sum(nums)))</li>
 *   <li><b>Space:</b> O(1)</li>
 * </ul>
 */
public class __BS19__SplitArrayLargestSum {

    public int splitArray(int[] nums, int k) {
        int low = Arrays.stream(nums).max().getAsInt();
        int high = Arrays.stream(nums).sum();
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canSplit(nums, mid, k)) {
                ans = mid;
                high = mid - 1; // try smaller largest sum
            } else {
                low = mid + 1;  // need larger largest sum
            }
        }
        return ans;
    }

    /**
     * Greedy feasibility check:
     * Can we split nums into <= k subarrays
     * such that each subarray sum ≤ maxSum?
     */
    private boolean canSplit(int[] nums, int maxSum, int k) {
        int count = 1;
        int curSum = 0;

        for (int num : nums) {
            if (curSum + num > maxSum) {
                count++;
                curSum = 0;
            }
            curSum += num;
        }
        return count <= k;
    }
}
