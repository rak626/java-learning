package DSA.stack;

import java.util.ArrayDeque;

/**
 * Problem: Maximal Rectangle
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/maximal-rectangle/description/">Maximal Rectangle</a></li>
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
public class __SQ13__MaximalRectangle {
    // optimal solution with O(n^2) time & O(n^2) space
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] prefixMat = new int[m][n];

        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int i = 0; i < m; i++) {
                if (matrix[i][j] == '0') sum = 0;
                else sum += 1;
                prefixMat[i][j] = sum;
            }
        }

        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            int area = findLargestHistogram(prefixMat[i]);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    // space optimize approach, O(n^2) time & O(n) space
    public int maximalRectangle2(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int maxArea = 0;
        int[] heights = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, findLargestHistogram(heights));
        }

        return maxArea;
    }


    private int findLargestHistogram(int[] nums) {
        var st = new ArrayDeque<Integer>();
        int maxArea = 0;

        for (int i = 0; i <= nums.length; i++) {
            int curHeight = i == nums.length ? 0 : nums[i];

            while (!st.isEmpty() && nums[st.peek()] >= curHeight) {
                int height = nums[st.pop()];
                int left = st.isEmpty() ? -1 : st.peek();
                int width = i - left - 1;
                int area = width * height;
                maxArea = Math.max(maxArea, area);
            }
            st.push(i);
        }

        return maxArea;
    }
}
