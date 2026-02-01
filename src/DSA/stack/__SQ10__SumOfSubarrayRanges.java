package DSA.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem: Sum Of Subarray Ranges
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/sum-of-subarray-ranges/description/">Sum Of Subarray Ranges</a></li>
 * <li>Difficulty: Medium</li>
 * <li>Tags: stack</li>
 * </ul>
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __SQ10__SumOfSubarrayRanges {

    public long subArrayRanges(int[] nums) {
        // range = max - min;
        // sum(range) = sum(max - min) = sum(max) - sum(min)
        return sumSubarrayMaxs(nums) - sumSubarrayMins(nums);
    }

    public long sumSubarrayMaxs(int[] arr) {
        int n = arr.length;
        int[] PGE = new int[n];
        int[] NGE = new int[n];

        var stack = new ArrayDeque<Integer>();

        // PGE
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i])
                stack.pop();
            PGE[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        // NGE
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i])
                stack.pop();
            NGE[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            long count = (long) (i - PGE[i]) * (NGE[i] - i);
            result = (result + count * arr[i]);
        }

        return result;
    }

    public long sumSubarrayMins(int[] arr) {
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
            long count = (long) (i - PSE[i]) * (NSE[i] - i);
            result = (result + count * arr[i]);
        }

        return result;
    }
}
