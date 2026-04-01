package DSA.slidingwindow;

/**
 * Problem: Max Consecutive Ones III
 * <ul>
 *   <li>LeetCode: <a href="https://leetcode.com/problems/max-consecutive-ones-iii/">
 *       Max Consecutive Ones III</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: Sliding Window, Array</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * Given a binary array <code>nums</code> and an integer <code>k</code>, you can flip at most <code>k</code> 0s to 1s.
 * Return the length of the longest contiguous subarray containing only 1s after flipping.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Use sliding window:
 *     <ol>
 *       <li>Maintain __2__ pointers <code>left</code> and <code>right</code> representing the window.</li>
 *       <li>Keep a count of zeros in the current window (<code>zeroCount</code>).</li>
 *       <li>If <code>zeroCount &gt; k</code>, move <code>left</code> forward until <code>zeroCount ≤ k</code>.</li>
 *       <li>Update <code>maxLength</code> = max(<code>maxLength</code>, <code>right - left + 1</code>).</li>
 *     </ol>
 *   </li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(n) → each element visited at most twice</li>
 *   <li><b>Space:</b> O(1) → only a few variables used</li>
 * </ul>
 */
public class __SW4__MaxConsecutiveOnesIii {

    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        int maxLength = 0;
        int zeroCount = 0;

        while (right < n) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            // shrink window if zeroCount exceeds k
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }
}
