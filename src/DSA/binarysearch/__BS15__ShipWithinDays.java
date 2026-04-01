package DSA.binarysearch;

import java.util.Arrays;

/**
 * Problem: Capacity To Ship Packages Within D Days
 * <ul>
 *   <li>Link: <a href="https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/">
 *       LeetCode - Capacity To Ship Packages Within D Days</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: binarysearch, greedy</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * You have packages with given weights. A ship has a fixed capacity and can ship
 * packages in the given order. You must deliver all packages within <code>days</code>.
 * Find the minimum capacity of the ship to do so.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>We need the <b>minimum ship capacity</b> → Binary Search on Answer.</li>
 *   <li>Search space:
 *     <ul>
 *       <li><b>Low</b> = max(weights) (at least must fit heaviest package).</li>
 *       <li><b>High</b> = sum(weights) (__1__ trip for all).</li>
 *     </ul>
 *   </li>
 *   <li>For each capacity = mid:
 *     <ul>
 *       <li>Simulate shipping greedily:
 *         <ul>
 *           <li>Keep adding weights until overflow.</li>
 *           <li>If overflow → start new day.</li>
 *         </ul>
 *       </li>
 *       <li>If days used ≤ allowed days → feasible → try smaller capacity.</li>
 *       <li>Else → capacity too small → increase it.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(n log(sum(weights) - max(weights)))
 *       → log search space × O(n) simulation.</li>
 *   <li><b>Space:</b> O(1).</li>
 * </ul>
 */
public class __BS15__ShipWithinDays {

    public int shipWithinDays(int[] weights, int days) {
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canShip(weights, days, mid)) {
                ans = mid;       // feasible capacity
                right = mid - 1; // try smaller
            } else {
                left = mid + 1;  // increase capacity
            }
        }
        return ans;
    }

    /**
     * Check if we can ship within 'days' using given capacity 'cap'.
     */
    private boolean canShip(int[] weights, int days, int cap) {
        int total = 0, usedDays = 1;

        for (int w : weights) {
            if (total + w > cap) {
                usedDays++;
                total = w; // start new day with current package
            } else {
                total += w;
            }
        }
        return usedDays <= days;
    }
}
