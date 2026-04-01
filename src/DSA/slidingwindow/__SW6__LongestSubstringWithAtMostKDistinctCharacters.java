package DSA.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Longest Substring With At Most K Distinct Characters
 * <ul>
 *   <li>LeetCode: <a href="https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/">
 *       Longest Substring With At Most K Distinct Characters</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: Sliding Window, HashMap, String</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * Given a string <code>str</code> and an integer <code>k</code>, find the length of the longest
 * substring that contains at most <code>k</code> distinct characters.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Sliding Window + HashMap:
 *     <ol>
 *       <li>Use __2__ pointers <code>left</code> and <code>right</code> to maintain a window.</li>
 *       <li>Use a map to store character counts in the current window.</li>
 *       <li>If map size exceeds <code>k</code>, shrink the window from the left until size ≤ <code>k</code>.</li>
 *       <li>At each step, update <code>maxLen</code> = max(<code>maxLen</code>, <code>right - left + 1</code>).</li>
 *     </ol>
 *   </li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(n) → each character enters and exits window at most once</li>
 *   <li><b>Space:</b> O(min(n, k)) → HashMap stores counts of distinct characters in window</li>
 * </ul>
 */
public class __SW6__LongestSubstringWithAtMostKDistinctCharacters {

    public static int kDistinctChars(int k, String str) {
        Map<Character, Integer> count = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < str.length(); right++) {
            char r = str.charAt(right);
            count.put(r, count.getOrDefault(r, 0) + 1);

            // shrink until only k distinct chars remain
            while (count.size() > k) {
                char l = str.charAt(left++);
                int newCount = count.get(l) - 1;
                if (newCount == 0) count.remove(l);
                else count.put(l, newCount);
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

}
