package DSA.stack;

import java.util.Stack;

/**
 * Problem: Next Greater Element Ii
 * Link: <a href="https://leetcode.com/problems/next-greater-element-ii/description/">Click here</a>
 * Difficulty: Medium
 * Tags: stack
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __SQ5__NextGreaterElementIi {
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            int ind = i % n;
            while (!stack.isEmpty() && stack.peek() <= nums[ind]) {
                stack.pop();
            }
            if (i < n) {
                result[ind] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(nums[ind]);
        }
        return result;
    }
}
