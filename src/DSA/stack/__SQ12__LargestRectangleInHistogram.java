package DSA.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem: Largest Rectangle In Histogram
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/largest-rectangle-in-histogram/description/">Largest Rectangle In Histogram</a></li>
 * <li>Difficulty: Hard</li>
 * <li>Tags: stack</li>
 * </ul>
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __SQ12__LargestRectangleInHistogram {

    /**
     * O(n2) time
     */
    public int largestRectangleArea_Brute(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            int left = i;
            int right = i;

            while (left - 1 >= 0 && heights[left - 1] >= heights[i]) left--;
            while (right + 1 < n && heights[right + 1] >= heights[i]) right++;

            int width = right - left + 1;
            maxArea = Math.max(maxArea, width * heights[i]);
        }

        return maxArea;
    }

    /**
     *  pre-computing NSE, PSE
     *  using 3 pass, for pse, nse, & calculating area
     *  O(N) time, O(N) space
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] PSE = new int[n];
        int[] NSE = new int[n];

        var st = new ArrayDeque<Integer>();

        // PSE
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            PSE[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        st.clear();

        // NSE
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            NSE[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = NSE[i] - PSE[i] - 1;
            int area = width * heights[i];
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }


    /**
     *  most optimized
     *  <p>
     *  Stack keeps increasing height indices.
     * <p>
     * <h3>When a smaller height comes:</h3>
     * <ul>
     * <li>➡ Means rectangles of taller heights must end here</li>
     * <li>➡ Pop and compute area immediately</li>
     * </ul>
     * <p>
     * The extra i == n → height = 0 is a flush trick to empty stack.
     *  using one pass, O(n)
     */
    public int largestRectangleArea_Optimized(int[] heights) {
        int n = heights.length;
        Deque<Integer> st = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int curHeight = (i == n) ? 0 : heights[i];

            while (!st.isEmpty() && heights[st.peek()] > curHeight) {
                int height = heights[st.pop()];
                int leftBoundary = st.isEmpty() ? -1 : st.peek();

                int width = i - leftBoundary - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            st.push(i);
        }

        return maxArea;
    }
}
