package DSA.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Problem: Next Greater Element I
 * Link: <a href="https://leetcode.com/problems/next-greater-element-i/description/">Click here</a>
 * Difficulty: Medium
 * Tags: stack
 * <p>
 * Approach:
 * - 1. use this monotonic stack approach if order is increasing/decreasing/any other order
 *
 * <p>
 * Time: O(2N) N for 'outside for' loop, N for 'inside while' loop
 * Space: O(N) worst case if all are in increasing order
 */
public class __SQ5__NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        // find the NGE of num2 array
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
}
