package DSA.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem: Trapping Rain Water
 * Link: <a href="https://leetcode.com/problems/trapping-rain-water/description/">Click here</a>
 * Difficulty: Hard
 * Tags: stack
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __SQ8__TrappingRainWater {

    // brute force - O(N^2) solution
    public int trap_bruteForce(int[] height) {
        int n = height.length;
        int water = 0;

        for (int i = 0; i < n; i++) {
            int leftMax = 0, rightMax = 0;

            // find left max
            for (int l = 0; l <= i; l++) {
                leftMax = Math.max(leftMax, height[l]);
            }

            // find right max
            for (int r = i; r < n; r++) {
                rightMax = Math.max(rightMax, height[r]);
            }

            water += Math.min(leftMax, rightMax) - height[i];
        }

        return water;
    }

    // O(N) time but extra space required, O(2N)
    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int preMax = Integer.MIN_VALUE, postMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            preMax = Math.max(preMax, height[i]);
            leftMax[i] = preMax;
            postMax = Math.max(postMax, height[n - 1 - i]);
            rightMax[n - 1 - i] = postMax;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return result;
    }

    // O(N) time & O(N) space, monotonic stack solution
    public int trap2(int[] height) {

        int totalWaterTrapped = 0;

        // Stores indices of bars in monotonic decreasing height order
        Deque<Integer> stack = new ArrayDeque<>();

        for (int curIdx = 0; curIdx < height.length; curIdx++) {

            // If current bar is taller → we found right boundary
            while (!stack.isEmpty() &&
                    height[curIdx] > height[stack.peek()]) {

                // Valley (bottom of trapped water)
                int valley = stack.pop();

                // If no left boundary exists → cannot trap water
                if (stack.isEmpty()) break;

                // Left boundary index
                int left = stack.peek();

                // Distance between left boundary and current right boundary
                int dist = curIdx - left - 1;

                // Water height depends on smaller boundary minus valley height
                int waterHeight = Math.min(height[left], height[curIdx]) - height[valley];

                totalWaterTrapped += dist * waterHeight;
            }

            // Push current bar as future boundary candidate
            stack.push(curIdx);
        }

        return totalWaterTrapped;
    }


    // O(N) time with no extra space - O(1)
    public int trap3(int[] height) {
        int n = height.length;
        int leftMax = Integer.MIN_VALUE, rightMax = Integer.MIN_VALUE;
        int left = 0, right = n - 1, result = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                result += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                result += rightMax - height[right];
                right--;
            }
        }
        return result;
    }
}
