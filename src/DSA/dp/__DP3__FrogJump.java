package DSA.dp;

import java.util.Arrays;

/**
 * Problem: Frog Jump
 * <ul>
 *   <li>Link: <a href="https://www.geeksforgeeks.org/frog-jump-dp-1/">Frog Jump</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: dp</li>
 * </ul>
 *
 * <p>
 * You are given n stones (numbered 0 to n-1) and an array height[] representing the height of each stone.
 * A frog is initially on stone 0 and wants to reach stone n-1.
 * The frog can jump either 1 or 2 stones forward. Each jump costs energy equal to the absolute difference of heights.
 * Goal: Find the minimum total energy to reach the last stone.
 * </p>
 *
 * <p>
 * <b>Intuition:</b>
 * <ol>
 *   <li>At each stone, consider the last jump:</li>
 *       <ul>
 *           <li>Jumped 1 step → came from i-1</li>
 *           <li>Jumped 2 steps → came from i-2</li>
 *       </ul>
 *   <li>Recurrence: <code>minEnergy(i) = min(minEnergy(i-1)+|h[i]-h[i-1]|, minEnergy(i-2)+|h[i]-h[i-2]|)</code></li>
 *   <li>Base case: minEnergy(0) = 0 → frog starts here, no energy spent.</li>
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
public class __DP3__FrogJump {

    /**
     * Pure recursive solution (exponential time).
     */
    private int frogJumpRecursive(int i, int[] height) {
        if (i == 0) return 0;

        int jump1 = frogJumpRecursive(i - 1, height) + Math.abs(height[i] - height[i - 1]);
        int jump2 = Integer.MAX_VALUE;
        if (i > 1) jump2 = frogJumpRecursive(i - 2, height) + Math.abs(height[i] - height[i - 2]);

        return Math.min(jump1, jump2);
    }

    /**
     * Recursion + Memoization (top-down DP).
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private int frogJumpMemo(int i, int[] height, int[] dp) {
        if (i == 0) return 0;
        if (dp[i] != -1) return dp[i];

        int jump1 = frogJumpMemo(i - 1, height, dp) + Math.abs(height[i] - height[i - 1]);
        int jump2 = Integer.MAX_VALUE;
        if (i > 1) jump2 = frogJumpMemo(i - 2, height, dp) + Math.abs(height[i] - height[i - 2]);

        return dp[i] = Math.min(jump1, jump2);
    }

    /**
     * Iterative bottom-up DP (tabulation).
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private int frogJumpIterative(int[] height) {
        int n = height.length;
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int jump1 = dp[i - 1] + Math.abs(height[i] - height[i - 1]);
            int jump2 = Integer.MAX_VALUE;
            if (i > 1) jump2 = dp[i - 2] + Math.abs(height[i] - height[i - 2]);
            dp[i] = Math.min(jump1, jump2);
        }

        return dp[n - 1];
    }

    /**
     * Space-optimized iterative DP.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    private int frogJumpSpaceOptimized(int[] height) {
        int n = height.length;
        int prev = 0;   // dp[i-1]
        int pprev = 0;  // dp[i-2]

        for (int i = 1; i < n; i++) {
            int jump1 = prev + Math.abs(height[i] - height[i - 1]);
            int jump2 = Integer.MAX_VALUE;
            if (i > 1) jump2 = pprev + Math.abs(height[i] - height[i - 2]);

            int curr = Math.min(jump1, jump2);
            pprev = prev;
            prev = curr;
        }

        return prev;
    }

    /**
     * Helper to call memoized solution easily.
     */
    public int frogJump(int[] height) {
        int n = height.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return frogJumpMemo(n - 1, height, dp);
    }
}
