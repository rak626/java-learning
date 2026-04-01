package DSA.dp;

import java.util.Arrays;

/**
 * Problem: House Robber II
 * <ul>
 *   <li>Link: <a href="https://leetcode.com/problems/house-robber-ii/">House Robber II</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: dp, circular array</li>
 * </ul>
 *
 * <p>
 * You are a robber planning to rob houses along a street arranged in a circle.
 * Adjacent houses cannot be robbed on the same night. First and last houses are adjacent.
 * Given an array nums representing money in each house, return the maximum money you can rob.
 * </p>
 *
 * <p>
 * <b>Intuition:</b>
 * <ol>
 *   <li>This is a circular variant of House Robber 1.</li>
 *   <li>Key constraint: first and last houses cannot both be robbed.</li>
 *   <li>Strategy: split the problem into two linear House Robber 1 problems:</li>
 *       <ul>
 *           <li>Case 1: Rob houses 0 to n-2 (exclude last)</li>
 *           <li>Case 2: Rob houses 1 to n-1 (exclude first)</li>
 *       </ul>
 *   <li>Compute the maximum for each linear case, then take the maximum of the two.</li>
 *   <li>This approach preserves the “rob or skip” choice at each house and handles circular adjacency elegantly.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <b>Four approaches implemented for each linear case:</b>
 * <ol>
 *   <li>Pure Recursion: simplest, but exponential time → only useful for understanding recurrence</li>
 *   <li>Recursion + Memoization: Top-down DP, O(n) time and space → avoids recomputation</li>
 *   <li>Iterative DP: Bottom-up DP, O(n) time and space → classic tabulation</li>
 *   <li>Space-Optimized DP: O(n) time, O(1) space → best for interview if asked for optimized version</li>
 * </ol>
 * </p>
 */
public class __DP6__HouseRobber2 {

    /**
     * 1. Pure recursive solution for linear array
     * <p>
     * For house i, we can either:
     * <ul>
     *   <li>Rob it → add nums[i] + max from i-2</li>
     *   <li>Skip it → take max from i-1</li>
     * </ul>
     * Base case: if end < start → return 0
     * </p>
     *
     * <p>
     * Time Complexity: O(2^n) → exponential recursion tree
     * Space Complexity: O(n) recursion stack
     * </p>
     */
    private int robLinearRecursive(int[] nums, int start, int end) {
        if (end < start) return 0;
        int robCurrent = nums[end] + robLinearRecursive(nums, start, end - 2);
        int skipCurrent = robLinearRecursive(nums, start, end - 1);
        return Math.max(robCurrent, skipCurrent);
    }

    /**
     * 2. Recursion + Memoization (Top-down DP) for linear
     * <p>
     * Same logic as recursion, but store results in dp array to avoid repeated calculations.
     * This converts exponential recursion into O(n) time.
     * </p>
     *
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n) → dp array + recursion stack
     * </p>
     */
    private int robLinearMemo(int[] nums, int start, int end, int[] dp) {
        if (end < start) return 0;
        if (dp[end] != -1) return dp[end];

        int robCurrent = nums[end] + robLinearMemo(nums, start, end - 2, dp);
        int skipCurrent = robLinearMemo(nums, start, end - 1, dp);

        return dp[end] = Math.max(robCurrent, skipCurrent);
    }

    /**
     * 3. Iterative Bottom-up DP for linear array
     * <p>
     * Build dp array from start to end iteratively.
     * dp[i] = max(rob house i + dp[i-2], skip house i → dp[i-1])
     * </p>
     *
     * <p>
     * Time Complexity: O(n) → single pass
     * Space Complexity: O(n) → dp array
     * </p>
     */
    private int robLinearIterative(int[] nums, int start, int end) {
        int[] dp = new int[nums.length];
        dp[start] = nums[start]; // base case

        for (int i = start + 1; i <= end; i++) {
            int rob = nums[i];
            if (i - 2 >= start) {
                rob += dp[i - 2];
            }
            int skip = dp[i - 1];
            dp[i] = Math.max(rob, skip);
        }
        return dp[end];
    }

    /**
     * 4. Space-Optimized DP for linear array
     * <p>
     * Only two variables needed: prev1 (dp[i-1]), prev2 (dp[i-2])
     * Iteratively update max values to save space.
     * </p>
     *
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * </p>
     */
    private int robLinearSpaceOptimized(int[] nums, int start, int end) {
        int prev2 = 0;
        int prev1 = 0;

        for (int i = start; i <= end; i++) {
            int rob = nums[i] + prev2;
            int skip = prev1;
            prev2 = prev1;
            prev1 = Math.max(rob, skip);
        }

        return prev1;
    }

    /**
     * Main function for House Robber II
     * <p>
     * Handles circular adjacency by solving two linear cases:
     * <ol>
     *   <li>Rob houses 0 to n-2</li>
     *   <li>Rob houses 1 to n-1</li>
     * </ol>
     * Take maximum of the two.
     * </p>
     *
     * <p>
     * Time Complexity: O(n) → two linear passes
     * Space Complexity: O(1) → using space-optimized DP
     * </p>
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int case1 = robLinearSpaceOptimized(nums, 0, n - 2);
        int case2 = robLinearSpaceOptimized(nums, 1, n - 1);

        return Math.max(case1, case2);
    }

    /**
     * Helper to call memoized solution easily
     * <p>
     * Useful for revising top-down DP approach
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * </p>
     */
    public int robMemo(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int[] dp1 = new int[n];
        Arrays.fill(dp1, -1);
        int case1 = robLinearMemo(nums, 0, n - 2, dp1);

        int[] dp2 = new int[n];
        Arrays.fill(dp2, -1);
        int case2 = robLinearMemo(nums, 1, n - 1, dp2);

        return Math.max(case1, case2);
    }
}
