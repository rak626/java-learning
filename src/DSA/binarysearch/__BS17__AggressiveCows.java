package DSA.binarysearch;

import java.util.Arrays;

/**
 * Problem: Aggressive Cows
 * <ul>
 *   <li>Link: <a href="https://www.spoj.com/problems/AGGRCOW/">
 *       SPOJ - Aggressive Cows</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: binarysearch, greedy, placement</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * You are given positions of stalls (sorted or unsorted) and an integer <code>k</code> (number of cows).
 * Place the cows in the stalls such that the <b>minimum distance</b> between any __2__ cows is maximized.
 * Return this maximum possible minimum distance.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>This is a classic "maximize minimum distance" → <b>Binary Search on Answer</b>.</li>
 *   <li>Search space:
 *     <ul>
 *       <li><b>Low</b> = 0 (minimum distance).</li>
 *       <li><b>High</b> = stalls[n-1] - stalls[0] (max possible distance).</li>
 *     </ul>
 *   </li>
 *   <li>For each candidate distance = mid:
 *     <ul>
 *       <li>Try to place cows greedily:
 *         <ul>
 *           <li>Place first cow at first stall.</li>
 *           <li>Place next cow in the first stall that is at least <code>mid</code> away from last placed cow.</li>
 *         </ul>
 *       </li>
 *       <li>If we can place ≥ k cows → feasible → try larger distance (left = mid + 1).</li>
 *       <li>Else → too large distance → shrink (right = mid - 1).</li>
 *     </ul>
 *   </li>
 *   <li>Keep track of the largest feasible distance.</li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(n log(maxDistance))
 *       → binary search × greedy placement.</li>
 *   <li><b>Space:</b> O(1).</li>
 * </ul>
 */
public class __BS17__AggressiveCows {

    public int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls); // ensure sorted

        int left = 0, right = stalls[stalls.length - 1] - stalls[0];
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canPlace(stalls, mid, k)) {
                ans = mid;      // feasible → record answer
                left = mid + 1; // try bigger distance
            } else {
                right = mid - 1; // shrink distance
            }
        }
        return ans;
    }

    /**
     * Greedy check: can we place k cows such that
     * min distance between any __2__ is at least 'dist'?
     */
    private boolean canPlace(int[] stalls, int dist, int k) {
        int count = 1; // place first cow at first stall
        int lastPos = stalls[0];

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPos >= dist) {
                count++;
                lastPos = stalls[i];
            }
            if (count >= k) return true; // placed all cows
        }
        return false;
    }
}
