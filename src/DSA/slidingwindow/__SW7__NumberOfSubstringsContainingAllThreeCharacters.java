package DSA.slidingwindow;

/**
 * Problem: Number Of Substrings Containing All Three Characters
 * <ul>
 *   <li>LeetCode: <a href="https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/">
 *       Number Of Substrings Containing All Three Characters</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: Sliding Window, HashMap, String</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * Given a string <code>s</code> containing only 'a', 'b', and 'c',
 * return the number of substrings that contain at least __1__ occurrence of all __3__ characters.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Sliding Window + HashMap:
 *     <ol>
 *       <li>Use __2__ pointers <code>left</code> and <code>right</code> to maintain a window.</li>
 *       <li>Use a map to track the count of 'a', 'b', and 'c' in the current window.</li>
 *       <li>If the window contains all __3__ characters → all substrings starting at <code>left</code> and ending ≥ <code>right</code> are valid.</li>
 *       <li>Increment <code>cnt</code> by <code>n - right</code> and shrink the window from <code>left</code> until it no longer contains all __3__.</li>
 *     </ol>
 *   </li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(n) → each character enters/exits the window at most once</li>
 *   <li><b>Space:</b> O(1) → fixed size map for 3 characters</li>
 * </ul>
 */
public class __SW7__NumberOfSubstringsContainingAllThreeCharacters {

    public int numberOfSubstrings(String s) {
        int[] freq = new int[3];
        int left = 0, ans = 0, distinct = 0;

        for (int right = 0; right < s.length(); right++) {
            int r = s.charAt(right) - 'a';
            if (freq[r] == 0)
                distinct++;
            freq[r]++;

            while (distinct == 3) {
                ans += s.length() - right;

                int l = s.charAt(left++) - 'a';
                freq[l]--;
                if (freq[l] == 0)
                    distinct--;
            }
        }

        return ans;
    }
}
