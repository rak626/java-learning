package DSA.stack;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * Problem: Valid Parentheses
 * Link: <a href="https://leetcode.com/problems/valid-parentheses/description/">Click here</a>
 * Difficulty: Easy
 * Tags: Stack
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O(n)
 * Space: O(n)
 */
public class __SQ2__ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == ')' || c == '}' || c == ']') {
                if (st.isEmpty()) {
                    return false;
                }
                Character peeked = st.peek();
                if (!((c == ')' && peeked == '(') || (c == '}' && peeked == '{') || (c == ']' && peeked == '['))) {
                    return false;
                }
                st.pop();
            } else {
                st.push(c);
            }
        }
        return st.isEmpty();
    }

    /**
     * cleanest approach
     */
    public boolean isValid2(String s) {
        var stack = new ArrayDeque<Character>();

        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else {
                if (stack.isEmpty() || stack.pop() != c) return false;
            }
        }

        return stack.isEmpty();
    }
}
