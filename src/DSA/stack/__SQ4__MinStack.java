package DSA.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem: Min Stack
 * Link: <a href="https://leetcode.com/problems/min-stack/description/">Click here</a>
 * Difficulty: Medium
 * Tags: stack
 * <p>
 * Approach:
 * - Use some container to track the min value for each value in stack
 * <p>
 * Time: O(1)
 * Space: O(N)
 */

public class __SQ4__MinStack {
    private final Deque<int[]> st;

    public __SQ4__MinStack() {
        st = new ArrayDeque<>();
    }

    public static void main(String[] args) {
        __SQ4__MinStack SQ4MinStack = new __SQ4__MinStack();
        SQ4MinStack.push(-2);
        SQ4MinStack.push(0);
        SQ4MinStack.push(-3);
        System.out.println(SQ4MinStack.getMin()); // return -3
        SQ4MinStack.pop();
        SQ4MinStack.top();    // return 0
        System.out.println(SQ4MinStack.getMin()); // return -2
    }

    public void push(int val) {
        int minVal = st.isEmpty() ? Integer.MAX_VALUE : st.peek()[1];
        if (val < minVal) {
            minVal = val;
        }
        st.push(new int[]{val, minVal});
    }

    public void pop() {
        if (st.isEmpty()) {
            return;
        }
        st.pop();
    }

    public int top() {
        return st.isEmpty() ? -1 : st.peek()[0];
    }

    public int getMin() {
        return st.isEmpty() ? -1 : st.peek()[0];
    }
}


