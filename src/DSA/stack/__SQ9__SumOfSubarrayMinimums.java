package DSA.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem: Sum Of Subarray Minimums
 * Link: <a href="https://leetcode.com/problems/sum-of-subarray-minimums/description/">Click here</a>
 * Difficulty: Medium
 * Tags: stack
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __SQ9__SumOfSubarrayMinimums {
    private static final int MOD = (int) 1e9 + 7;

    // Brute force approach - Generating all possible subarrays
    public int sumSubarrayMinsBruteForce(int[] arr) {
        int n = arr.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int min = arr[i];
            for (int j = i; j < n; j++) {
                min = Math.min(min, arr[j]);
                result = (result + min) % MOD;
            }
        }
        return result;
    }

    // by using PSE, NSE
    public int sumSubarrayMins2(int[] arr) {
        int n = arr.length;
        int[] PSE = new int[n];
        int[] NSE = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        // Previous Smaller Element
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            PSE[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Next Smaller Element (greater than or equal: >=)
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                // not strictly greater because of duplicate handling.
                stack.pop();
            }
            NSE[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            // count = cnt of left side ele * cnt of right side ele where nums[i] is the smallest
            long count = (long) (i - PSE[i]) * (NSE[i] - i) % MOD;
            result = (result + count * arr[i]) % MOD;
        }

        return (int) result;
    }
}
