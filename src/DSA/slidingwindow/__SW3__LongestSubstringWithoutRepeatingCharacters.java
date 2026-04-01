package DSA.slidingwindow;

import java.util.Arrays;

/**
 * Problem: Longest Substring Without Repeating Characters
 * <ul>
 *   <li>LeetCode: <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">
 *       Longest Substring Without Repeating Characters</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: Sliding Window, Hashing, String</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * Given a string <code>s</code>, find the length of the longest substring without repeating characters.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Use Sliding Window + Hashing:
 *     <ol>
 *       <li>Maintain an array <code>lastSeen[256]</code> to store last seen index of each ASCII character.</li>
 *       <li>Use __2__ pointers: <code>start</code> (window start) and <code>end</code> (window end).</li>
 *       <li>If current character has been seen inside the window → move <code>start</code> to <code>lastSeen[currentChar] + 1</code>.</li>
 *       <li>Update <code>lastSeen[currentChar]</code> = <code>end</code>.</li>
 *       <li>Update <code>maxLength</code> = max(<code>maxLength</code>, <code>end - start + 1</code>).</li>
 *     </ol>
 *   </li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(n) → each character visited at most twice</li>
 *   <li><b>Space:</b> O(1) → fixed size array for ASCII characters</li>
 * </ul>
 */
public class __SW3__LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] lastSeen = new int[256];
        Arrays.fill(lastSeen, -1);

        int maxLength = 0;
        int start = 0;

        for (int end = 0; end < n; end++) {
            char currentChar = s.charAt(end);

            if (lastSeen[currentChar] >= start) {
                start = lastSeen[currentChar] + 1; // move start past repeated char
            }

            lastSeen[currentChar] = end;
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }
}
