package DSA.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem: Asteroid Collision
 * Link: <a href="https://leetcode.com/problems/asteroid-collision/description/">Click here</a>
 * Difficulty: Medium
 * Tags: stack
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __SQ11__AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int asteroid : asteroids) {
            boolean shouldAdd = true;

            while (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
                int top = stack.peek();
                if (top < -asteroid) {
                    stack.pop(); // Current asteroid is larger, continue checking
                } else if (top == -asteroid) {
                    stack.pop(); // Both explode
                    shouldAdd = false;
                    break;
                } else {
                    // Top is larger; current asteroid is destroyed
                    shouldAdd = false;
                    break;
                }
            }

            if (shouldAdd) {
                stack.push(asteroid);
            }
        }

        // Convert stack to array in reverse
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }
}
