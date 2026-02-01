package DSA.stack;

import java.util.ArrayDeque;

public class __SQ6__NextSmallerElement {
    public static int[] nextSmallerElement(int[] nums) {
        var stack = new ArrayDeque<Integer>();
        var result = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return result;
    }
}
