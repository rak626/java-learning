package DSA.slidingwindow;

/**
 * Problem: Longest Repeating Character Replacement
 * <ul>
 *   <li>LeetCode: <a href="https://leetcode.com/problems/longest-repeating-character-replacement/">
 *       Longest Repeating Character Replacement</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: Sliding Window, Array, String</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * Given a string <code>s</code> and an integer <code>k</code>, you can replace at most
 * <code>k</code> characters to make all characters in a substring the same.
 * Return the length of the longest such substring.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Sliding Window:
 *     <ol>
 *       <li>Use __2__ pointers <code>left</code> and <code>right</code> for the window.</li>
 *       <li>Use a frequency array of size 26 to track counts of characters in the window.</li>
 *       <li>Maintain <code>maxFreq</code> = max frequency of any character in the window.</li>
 *       <li>If <code>(right - left + 1) - maxFreq &gt; k</code> → shrink window from <code>left</code>.</li>
 *       <li>Update <code>maxLen</code> = max(<code>maxLen</code>, <code>right - left + 1</code>).</li>
 *     </ol>
 *   </li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(n) → each character enters/exits the window at most once</li>
 *   <li><b>Space:</b> O(26) → frequency array for uppercase English letters → O(1)</li>
 * </ul>
 */
public class __SW8__LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        int n = s.length();
        int left = 0, maxLen = 0, maxFreq = 0;
        int[] freq = new int[26];

        for (int right = 0; right < n; right++) {
            int idx = s.charAt(right) - 'A';
            freq[idx]++;
            maxFreq = Math.max(maxFreq, freq[idx]);

            // shrink window if replacements needed > k
            if ((right - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
