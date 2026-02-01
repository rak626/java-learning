package DSA.stack;

import java.util.Stack;

/**
 * Problem: Previous Smallest Element
 * Link: <a href="https://leetcode.com/problems/previous-smallest-element/description/">Click here</a>
 * Difficulty: Medium
 * Tags: stack
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __SQ7__PreviousSmallerElement {
    public static int[] previousSmallerElement(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        return result;
    }
}
