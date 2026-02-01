package DSA.stack;

import java.util.ArrayDeque;

/**
 * Problem: Sum Of Subarray Maximums
 * Link: <a href="https://leetcode.com/problems/sum-of-subarray-maximums/description/">Click here</a>
 * Difficulty: Medium
 * Tags: stack
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __SQ9__SumOfSubarrayMaximums {
    private static final int MOD = (int) 1e9 + 7;

    // Brute force approach - Generating all possible subarrays
    public int sumSubarrayMaxsBruteForce(int[] arr) {
        int n = arr.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int max = arr[i];
            for (int j = i; j < n; j++) {
                max = Math.max(max, arr[j]);
                result = (result + max) % MOD;
            }
        }
        return result;
    }

    // by using PGE, NGE
    public int sumSubarrayMaxs2(int[] arr) {
        int n = arr.length;
        int[] PGE = new int[n];
        int[] NGE = new int[n];

        var stack = new ArrayDeque<Integer>();

        // PGE
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) stack.pop();
            PGE[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        // NGE
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) stack.pop();
            NGE[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            long count = (long) (i - PGE[i]) * (NGE[i] - i) % MOD;
            result = (result + count * arr[i]) % MOD;
        }

        return (int) result;
    }
}
