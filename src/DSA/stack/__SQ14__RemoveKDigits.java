package DSA.stack;

import java.util.ArrayDeque;

/**
 * Problem: Remove K Digits
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/remove-k-digits/description/">Remove K Digits</a></li>
 * <li>Difficulty: Medium</li>
 * <li>Tags: stack</li>
 * </ul>
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __SQ14__RemoveKDigits {
    /**
     * O(n^2) time & O(n) space
     */
    public String removeKdigits__brute(String num, int k) {
        StringBuilder sb = new StringBuilder(num);
        while (k-- > 0) {
            int i = 0;

            while (i < sb.length() - 1 && sb.charAt(i) <= sb.charAt(i + 1)) {
                i++;
            }

            sb.deleteCharAt(i);
        }

        // Remove leading zeros
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.isEmpty() ? "0" : sb.toString();
    }

    public String removeKdigits(String num, int k) {
        var st = new ArrayDeque<Character>();

        for (int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            while (!st.isEmpty() && k > 0 && st.peek() > ch) {
                st.pop();
                k--;
            }
            st.push(ch);
        }

        while (k > 0 && !st.isEmpty()) {
            st.pop();
            k--;
        }

        var result = new StringBuilder();
        while (!st.isEmpty()) {
            result.append(st.pop());
        }

        result.reverse();

        // Remove leading zeros
        int idx = 0;
        while (idx < result.length() && result.charAt(idx) == '0') {
            idx++;
        }

        return idx == result.length() ? "0" : result.substring(idx);
    }
}
