package DSA.dp;

import java.util.Arrays;

/**
 * Problem: Frog Jump with k jumps
 * <ul>
 *   <li>Link: <a href="https://www.geeksforgeeks.org/frog-jump-dp-1/">Frog Jump</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: dp</li>
 * </ul>
 *
 * <p>
 * You are given n stones (numbered 0 to n-1) and an array height[] representing the height of each stone.
 * A frog is initially on stone 0 and wants to reach stone n-1.
 * The frog can jump 1 to k stones forward. Each jump costs energy equal to the absolute difference of heights.
 * Goal: Find the minimum total energy to reach the last stone.
 * </p>
 *
 * <p>
 * <b>Intuition:</b>
 * <ol>
 *   <li>At each stone i, consider all possible previous jumps (1..k):</li>
 *       <ul>
 *           <li>Jumped j steps → came from i-j</li>
 *       </ul>
 *   <li>Recurrence: <code>minEnergy(i) = min(minEnergy(i-j) + |h[i]-h[i-j]|), for j=1..k</code></li>
 *   <li>Base case: minEnergy(0) = 0 → starting point, no energy spent.</li>
 * </ol>
 * </p>
 *
 * <p>
 * <b>Four approaches implemented:</b>
 * <ol>
 *   <li>Pure Recursion (Exponential time)</li>
 *   <li>Recursion + Memoization (Top-down DP, O(n*k) time and O(n) space)</li>
 *   <li>Iterative DP (Bottom-up DP, O(n*k) time and O(n) space)</li>
 *   <li>Space-Optimized Iterative DP (O(n*k) time, O(k) space)</li>
 * </ol>
 * </p>
 */
public class __DP4__FrogJumpK {

    /**
     * Pure recursive solution.
     */
    private int frogJumpKRecursive(int i, int[] height, int k) {
        if (i == 0) return 0;

        int minEnergy = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (i - j >= 0) {
                int energy = frogJumpKRecursive(i - j, height, k) + Math.abs(height[i] - height[i - j]);
                minEnergy = Math.min(minEnergy, energy);
            }
        }
        return minEnergy;
    }

    /**
     * Recursion + Memoization (top-down DP).
     */
    private int frogJumpKMemo(int i, int[] height, int k, int[] dp) {
        if (i == 0) return 0;
        if (dp[i] != -1) return dp[i];

        int minEnergy = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (i - j >= 0) {
                int energy = frogJumpKMemo(i - j, height, k, dp) + Math.abs(height[i] - height[i - j]);
                minEnergy = Math.min(minEnergy, energy);
            }
        }
        return dp[i] = minEnergy;
    }

    /**
     * Iterative bottom-up DP (tabulation).
     */
    private int frogJumpKIterative(int[] height, int k) {
        int n = height.length;
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int minEnergy = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int energy = dp[i - j] + Math.abs(height[i] - height[i - j]);
                    minEnergy = Math.min(minEnergy, energy);
                }
            }
            dp[i] = minEnergy;
        }

        return dp[n - 1];
    }

    /**
     * Space-optimized iterative DP.
     * Stores only last k dp values to save space.
     */
    private int frogJumpKSpaceOptimized(int[] height, int k) {
        int n = height.length;
        int[] lastK = new int[k];  // store last k dp values
        lastK[0] = 0;

        for (int i = 1; i < n; i++) {
            int minEnergy = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int energy = lastK[(i - j) % k] + Math.abs(height[i] - height[i - j]);
                    minEnergy = Math.min(minEnergy, energy);
                }
            }
            lastK[i % k] = minEnergy;
        }

        return lastK[(n - 1) % k];
    }

    /**
     * Helper to call memoized solution easily.
     */
    public int frogJumpK(int[] height, int k) {
        int n = height.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return frogJumpKMemo(n - 1, height, k, dp);
    }
}
