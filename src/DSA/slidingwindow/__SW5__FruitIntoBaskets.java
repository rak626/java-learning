package DSA.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Fruit Into Baskets
 * <ul>
 *   <li>LeetCode: <a href="https://leetcode.com/problems/fruit-into-baskets/">
 *       Fruit Into Baskets</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: Sliding Window, HashMap, Array</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * You are given an array <code>fruits</code> where each integer represents a type of fruit.
 * You can pick fruits in a contiguous subarray with at most 2 different types.
 * Return the maximum number of fruits you can pick.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Sliding Window + HashMap:
 *     <ol>
 *       <li>Use __2__ pointers <code>left</code> and <code>right</code> for the window.</li>
 *       <li>Use a map to track the count of each fruit type in the window.</li>
 *       <li>If map size exceeds 2 → shrink window from <code>left</code> until map size ≤ 2.</li>
 *       <li>At each step, update <code>maxFruits</code> = max(<code>maxFruits</code>, <code>right - left + 1</code>).</li>
 *     </ol>
 *   </li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(n) → each element enters and exits window at most once</li>
 *   <li><b>Space:</b> O(N) → HashMap stores counts, but effectively size ≤ 2</li>
 * </ul>
 */
public class __SW5__FruitIntoBaskets {

    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0, max = 0;

        for (int right = 0; right < fruits.length; right++) {
            count.put(fruits[right], count.getOrDefault(fruits[right], 0) + 1);

            while (count.size() > 2) {
                int f = fruits[left++];
                int newCount = count.get(f) - 1;
                if (newCount == 0) count.remove(f);
                else count.put(f, newCount);
            }

            max = Math.max(max, right - left + 1);
        }
        return max;
    }

}
