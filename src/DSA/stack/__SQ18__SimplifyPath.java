package DSA.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem: Simplify Path
 * Link: <a href="https://leetcode.com/problems/simplify-path/description/">Click here</a>
 * Difficulty: Medium
 * Tags: stack
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __SQ18__SimplifyPath {
    public String simplifyPath(String path) {
        String[] words = path.split("/");
        Deque<String> st = new ArrayDeque<>();
        for (String word : words) {
            if (word.isBlank() || word.equals(".")) continue;
            if (word.equals("..")) {
                if (!st.isEmpty()) st.pop();
            } else {
                st.push(word);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.insert(0, "/" + st.pop());
        }
        return sb.isEmpty() ? "/" : sb.toString();
    }
}
