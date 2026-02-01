package DSA.stack;

import java.util.ArrayDeque;

public class __SQ6__PreviousGreaterElement {
    public static int[] previousGreaterElement(int[] nums) {
        var stack = new ArrayDeque<Integer>();
        var result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) stack.pop();
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        return result;
    }
}
