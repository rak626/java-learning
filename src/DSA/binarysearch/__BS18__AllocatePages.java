package DSA.binarysearch;

import java.util.Arrays;

/**
 * Problem: Allocate Minimum Number of Pages
 * <ul>
 *   <li>Link: <a href="https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1">
 *       GFG - Allocate Minimum Number of Pages</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: binarysearch, greedy, partition</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * You are given an array where <code>arr[i]</code> = pages in i-th book and an integer <code>k</code> (number of students).
 * Assign books to students such that:
 * <ul>
 *   <li>Each student gets a contiguous block of books.</li>
 *   <li>Each book is assigned to exactly __1__ student.</li>
 * </ul>
 * Minimize the maximum number of pages assigned to any student.
 * Return this minimum possible maximum.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>This is a partition problem → solved with <b>Binary Search on Answer</b>.</li>
 *   <li>Search space:
 *     <ul>
 *       <li><b>Low</b> = max(arr) → at least __1__ student must take the largest book.</li>
 *       <li><b>High</b> = sum(arr) → __1__ student takes all books.</li>
 *     </ul>
 *   </li>
 *   <li>Check feasibility (mid = candidate max load):
 *     <ul>
 *       <li>Greedily assign books in order to students.</li>
 *       <li>If current sum > mid → allocate to new student.</li>
 *       <li>Count total students needed.</li>
 *     </ul>
 *   </li>
 *   <li>If ≤ k students → feasible → shrink right.</li>
 *   <li>If > k students → infeasible → expand left.</li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(n log(sum(arr)))</li>
 *   <li><b>Space:</b> O(1)</li>
 * </ul>
 */
public class __BS18__AllocatePages {

    public int findPages(int[] arr, int k) {
        int n = arr.length;
        if (n < k) return -1; // not enough books

        int low = Arrays.stream(arr).max().getAsInt(); // max book size
        int high = Arrays.stream(arr).sum();           // sum of all pages
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canAllocate(arr, mid, k)) {
                ans = mid;
                high = mid - 1; // try smaller max load
            } else {
                low = mid + 1;  // need larger max load
            }
        }
        return ans;
    }

    /**
     * Greedy check: can we allocate books so that
     * no student gets > maxPages and students ≤ k?
     */
    private boolean canAllocate(int[] arr, int maxPages, int k) {
        int students = 1;
        int pages = 0;

        for (int val : arr) {
            if (pages + val > maxPages) {
                students++;
                pages = 0;
            }
            pages += val;
        }
        return students <= k;
    }
}
