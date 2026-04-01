package DSA.slidingwindow;

/**
 * Problem: Maximum Points You Can Obtain From Cards
 * <ul>
 *   <li>LeetCode: <a href="https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/">
 *       Maximum Points You Can Obtain From Cards</a></li>
 *   <li>Difficulty: Medium</li>
 *   <li>Tags: Sliding Window, Array</li>
 * </ul>
 *
 * <p><b>Problem Summary:</b></p>
 * <p>
 * You have an array <code>cardPoints</code> and can pick <code>k</code> cards from either
 * the beginning or end of the array. Return the maximum points you can obtain.
 * </p>
 *
 * <h2>Approach / Intuition:</h2>
 * <ul>
 *   <li>Sliding Window / Prefix-Suffix technique:
 *     <ol>
 *       <li>Start by taking the first <code>k</code> elements as initial sum (all from the left).</li>
 *       <li>Then, iteratively move cards from left to right:
 *         <ul>
 *           <li>Remove __1__ from the left and add __1__ from the right end.</li>
 *           <li>Update <code>maxSum</code> at each step.</li>
 *         </ul>
 *       </li>
 *       <li>After <code>k</code> iterations, the max sum achieved is the answer.</li>
 *     </ol>
 *   </li>
 * </ul>
 *
 * <h2>Complexity:</h2>
 * <ul>
 *   <li><b>Time:</b> O(k) → we iterate at most 2k elements</li>
 *   <li><b>Space:</b> O(1) → constant extra space</li>
 * </ul>
 */
public class __SW2__MaximumPointsYouCanObtainFromCards {

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int leftSum = 0, rightSum = 0, maxSum = 0;
        int right = n - 1;

        // Sum of first k elements (all from left)
        for (int i = 0; i < k; i++) {
            leftSum += cardPoints[i];
        }
        maxSum = leftSum;

        // Move window: remove from left, add from right
        for (int i = k - 1; i >= 0; i--) {
            leftSum -= cardPoints[i];
            rightSum += cardPoints[right];
            right--;
            maxSum = Math.max(maxSum, leftSum + rightSum);
        }

        return maxSum;
    }
}
