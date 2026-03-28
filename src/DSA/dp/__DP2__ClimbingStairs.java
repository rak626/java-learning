package DSA.dp;

import java.util.Arrays;

/**
 * Problem: Climbing Stairs
 * <ul>
 *   <li>Link: <a href="https://leetcode.com/problems/climbing-stairs/description/">Climbing Stairs</a></li>
 *   <li>Difficulty: Easy</li>
 *   <li>Tags: dp</li>
 * </ul>
 *
 * <p>
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * </p>
 *
 * <p>
 * <b>Intuition:</b>
 * <ol>
 *   <li>Focus on the last step you take:</li>
 *       <ul>
 *           <li>If the last step was 1 stair → you were at step n-1</li>
 *           <li>If the last step was 2 stairs → you were at step n-2</li>
 *       </ul>
 *   <li>This gives the recurrence: <code>ways(n) = ways(n-1) + ways(n-2)</code></li>
 *   <li>Base cases:
 *       <ul>
 *           <li>ways(0) = 1 → “do nothing” is counted as 1 way</li>
 *           <li>ways(1) = 1 → only 1 way to climb 1 step</li>
 *       </ul>
 *   </li>
 *   <li>This recurrence is exactly the Fibonacci pattern.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <b>Four approaches implemented:</b>
 * <ol>
 *   <li>Pure Recursion (Exponential time)</li>
 *   <li>Recursion + Memoization (Top-down DP, O(n) time and space)</li>
 *   <li>Iterative DP (Bottom-up DP, O(n) time and space)</li>
 *   <li>Space-Optimized Iterative DP (O(n) time, O(1) space)</li>
 * </ol>
 * </p>
 */
public class __DP2__ClimbingStairs {

    /**
     * Main method to compute the number of ways to climb stairs using memoization.
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return findWaysDP(n, dp);
    }

    /**
     * Pure recursive solution (exponential time complexity).
     * <p>
     * Idea: Directly implement the recurrence:
     * ways(n) = ways(n-1) + ways(n-2)
     * </p>
     */
    private int findWays(int ind) {
        if (ind == 0) return 1;  // Base case
        int ways1 = findWays(ind - 1);
        int ways2 = 0;
        if (ind > 1) ways2 = findWays(ind - 2);
        return ways1 + ways2;
    }

    /**
     * Recursion with memoization (top-down DP).
     * <p>
     * Idea: Store already computed results to avoid recomputation.
     * Each subproblem ways(k) is solved only once.
     * </p>
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private int findWaysDP(int ind, int[] dp) {
        if (ind == 0) return 1;  // Base case
        if (dp[ind] != -1) return dp[ind];

        int ways1 = findWaysDP(ind - 1, dp);
        int ways2 = 0;
        if (ind > 1) ways2 = findWaysDP(ind - 2, dp);

        return dp[ind] = ways1 + ways2;
    }

    /**
     * Iterative bottom-up DP (tabulation) solution.
     * <p>
     * Idea: Build solution from dp[0] up to dp[n] using the recurrence:
     * dp[i] = dp[i-1] + dp[i-2]
     * </p>
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private int findWaysDPIterative(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 0);
        dp[0] = 1;  // Base case

        for (int i = 1; i <= n; i++) {
            int ways1 = dp[i - 1];
            int ways2 = 0;
            if (i > 1) ways2 = dp[i - 2];
            dp[i] = ways1 + ways2;
        }
        return dp[n];
    }

    /**
     * Space-optimized iterative DP solution.
     * <p>
     * Idea: Only keep track of the last two values (Fibonacci pattern)
     * to reduce space complexity from O(n) to O(1).
     * </p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    private int findWaysSpaceOptimized(int n) {
        int prev = 1, pprev = 0;

        for (int i = 1; i <= n; i++) {
            int ways1 = prev;
            int ways2 = 0;
            if (i > 1) ways2 = pprev;
            pprev = prev;
            prev = ways1 + ways2;
        }
        return prev;
    }
}
