package DSA.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * <h2>🔴 Sliding Window Maximum</h2>
 *
 * <b>Problem Statement:</b>
 * Given an array of integers and a sliding window of size <code>k</code>,
 * return the maximum element in each window position.
 *
 * <hr>
 *
 * <h3>🔗 Reference</h3>
 * <ul>
 *   <li>
 *     LeetCode:
 *     <a href="https://leetcode.com/problems/sliding-window-maximum/description/">
 *     Sliding Window Maximum</a>
 *   </li>
 * </ul>
 *
 * <hr>
 *
 * <h3>🧠 Key Learning</h3>
 * <ul>
 *   <li>Sliding Window Pattern</li>
 *   <li>Heap Optimization</li>
 *   <li>Monotonic Deque (Very Important Interview Pattern)</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>📊 Solution Progression</h3>
 *
 * <table border="1">
 *   <tr>
 *     <th>Approach</th>
 *     <th>Time</th>
 *     <th>Space</th>
 *     <th>Interview Value</th>
 *   </tr>
 *   <tr>
 *     <td>Brute Force</td>
 *     <td>O(n * k)</td>
 *     <td>O(1)</td>
 *     <td>Basic Understanding</td>
 *   </tr>
 *   <tr>
 *     <td>Heap / PriorityQueue</td>
 *     <td>O(n log k)</td>
 *     <td>O(k)</td>
 *     <td>Optimization Step</td>
 *   </tr>
 *   <tr>
 *     <td><b>Monotonic Deque</b></td>
 *     <td><b>O(n)</b></td>
 *     <td>O(k)</td>
 *     <td><b>Expected Interview Answer</b></td>
 *   </tr>
 * </table>
 *
 * <hr>
 *
 * <h3>⭐ Interview Memory Trick</h3>
 * If question contains:
 * <ul>
 *   <li>Sliding window</li>
 *   <li>Max / Min query</li>
 *   <li>Contiguous range</li>
 * </ul>
 * 👉 Think <b>Monotonic Deque</b>
 *
 */
public class __SQ16__SlidingWindowMaximum {

    /**
     * <h3>🥉 Brute Force Approach</h3>
     *
     * <b>Idea:</b>
     * For every window → scan all k elements → compute max.
     *
     * <h4>📌 Intuition</h4>
     * Very straightforward implementation.
     * No extra data structures.
     *
     * <h4>⏱ Time Complexity</h4>
     * O(n * k)
     * <br>
     * Because for each starting index → we scan k elements.
     *
     * <h4>📦 Space Complexity</h4>
     * O(1) extra space (excluding output array)
     *
     * <h4>✅ When Useful</h4>
     * <ul>
     *   <li>Explaining baseline solution in interview</li>
     *   <li>Small constraints</li>
     * </ul>
     */
    public int[] maxSlidingWindow_brute(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            int max = nums[i];
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            ans[i] = max;
        }

        return ans;
    }

    /**
     * <h3>🥈 Better Approach — Max Heap (Priority Queue)</h3>
     *
     * <b>Idea:</b>
     * Maintain max heap storing:
     * <ul>
     *   <li>Value</li>
     *   <li>Index</li>
     * </ul>
     *
     * Remove elements that fall outside the window.
     *
     * <h4>📌 Why Store Index?</h4>
     * To know if element is still part of current window.
     *
     * <h4>⏱ Time Complexity</h4>
     * O(n log k)
     * <br>
     * Each insertion & deletion → log k
     *
     * <h4>📦 Space Complexity</h4>
     * O(k) → Heap stores window elements
     *
     * <h4>⚠️ Limitation</h4>
     * Slower than deque because heap operations are log(k).
     */
    public int[] maxSlidingWindow_better(int[] nums, int k) {
        int n = nums.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> b[0] - a[0] // Max heap by value
        );

        int[] ans = new int[n - k + 1];
        int idx = 0;

        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{nums[i], i});

            // Remove elements outside window
            while (!pq.isEmpty() && pq.peek()[1] <= i - k) {
                pq.poll();
            }

            if (i >= k - 1 && !pq.isEmpty()) {
                ans[idx++] = pq.peek()[0];
            }
        }

        return ans;
    }

    /**
     * <h3>🥇 Optimal Approach — Monotonic Decreasing Deque</h3>
     *
     * <b>Core Idea:</b>
     * Maintain deque of indices where values are in decreasing order.
     *
     * <h4>📌 Properties</h4>
     * <ul>
     *   <li>Front → Maximum of window</li>
     *   <li>Deque always decreasing by value</li>
     * </ul>
     *
     * <h4>📌 Steps</h4>
     * <ol>
     *   <li>Remove indices outside window (from front)</li>
     *   <li>Remove smaller values from back</li>
     *   <li>Add current index</li>
     *   <li>Record max when window formed</li>
     * </ol>
     *
     * <h4>⏱ Time Complexity</h4>
     * O(n)
     * <br>
     * Each element added once and removed once → amortized O(n)
     *
     * <h4>📦 Space Complexity</h4>
     * O(k)
     *
     * <h4>⭐ Why This Is Best</h4>
     * Achieves theoretical lower bound.
     */
    public int[] maxSlidingWindow_optimal(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> dq = new ArrayDeque<>();
        int[] ans = new int[n - k + 1];
        int idx = 0;

        for (int i = 0; i < n; i++) {

            // Remove indices outside window
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // Maintain decreasing order
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }

            dq.offerLast(i);

            // Window formed
            if (i >= k - 1 && !dq.isEmpty()) {
                ans[idx++] = nums[dq.peekFirst()];
            }
        }

        return ans;
    }
}
