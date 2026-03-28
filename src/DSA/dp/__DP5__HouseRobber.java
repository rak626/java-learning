package DSA.dp;

import java.util.Arrays;

/**
 * Problem: House Robber
 * <ul>
 *   <li>Link: <a href="https://leetcode.com/problems/house-robber/description/">House Robber</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: dp</li>
 * </ul>
 *
 * <p>
 * You are a robber planning to rob houses along a street. Each house has some money.
 * You cannot rob two adjacent houses on the same night.
 * Given an array nums representing money in each house, return the maximum money you can rob.
 * </p>
 *
 * <p>
 * <b>Intuition:</b>
 * <ol>
 *   <li>At house i, two options:</li>
 *       <ul>
 *           <li>Rob house i → cannot rob house i-1 → total = nums[i] + maxRob(i-2)</li>
 *           <li>Skip house i → take maxRob(i-1)</li>
 *       </ul>
 *   <li>Take the maximum of the two options for the optimal solution.</li>
 *   <li>Base case: maxRob(0) = nums[0]</li>
 * </ol>
 * </p>
 *
 * <p>
 * <b>Four approaches implemented:</b>
 * <ol>
 *   <li>Pure Recursion (Exponential time)</li>
 *   <li>Recursion + Memoization (Top-down DP, O(n) time & space)</li>
 *   <li>Iterative DP (Bottom-up DP, O(n) time & space)</li>
 *   <li>Space-Optimized Iterative DP (O(n) time, O(1) space)</li>
 * </ol>
 * </p>
 */
public class __DP5__HouseRobber {

    /**
     * 1. Pure recursive solution (Exponential)
     * <p>
     * Time Complexity: O(2^n) → Two choices per house, recursion tree doubles each step
     * Space Complexity: O(n) → Recursion stack depth
     */
    private int robRecursive(int[] nums, int i) {
        if (i < 0) return 0;
        int robCurrent = nums[i] + robRecursive(nums, i - 2);
        int skipCurrent = robRecursive(nums, i - 1);
        return Math.max(robCurrent, skipCurrent);
    }

    /**
     * 2. Recursion + Memoization (Top-down DP)
     * <p>
     * Time Complexity: O(n) → Each house computed once
     * Space Complexity: O(n) → DP array + recursion stack
     */
    private int robMemo(int[] nums, int i, int[] dp) {
        if (i < 0) return 0;
        if (dp[i] != -1) return dp[i];

        int robCurrent = nums[i] + robMemo(nums, i - 2, dp);
        int skipCurrent = robMemo(nums, i - 1, dp);

        return dp[i] = Math.max(robCurrent, skipCurrent);
    }

    /**
     * 3. Iterative Bottom-up DP (Tabulation)
     * <p>
     * Time Complexity: O(n) → One pass through array
     * Space Complexity: O(n) → DP array
     */
    private int robIterative(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[n - 1];
    }

    /**
     * 4. Space-Optimized DP
     * <p>
     * Time Complexity: O(n) → One pass through array
     * Space Complexity: O(1) → Only two variables used
     */
    private int robSpaceOptimized(int[] nums) {
        int prev2 = 0;
        int prev1 = 0;

        for (int num : nums) {
            int current = Math.max(prev2 + num, prev1);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    /**
     * Helper to call memoized solution easily
     */
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return robMemo(nums, n - 1, dp);
    }
}
