package DSA.recursion;

/**
 * Problem: Combination Sum Ii
 * <ul>
 * <li>Link: <a href="https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1">subset sum</a></li>
 * <li>Difficulty: Medium</li>
 * <li>Tags: recursion</li>
 * </ul>
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __RC4__SubsetSumI {
    static Boolean isSubsetSum(int[] arr, int sum) {
        return solve(0, sum, arr);
    }

    static boolean solve(int idx, int sum, int[] arr) {

        if (sum == 0) return true;
        if (idx == arr.length || sum < 0) return false;

        // take
        if (arr[idx] <= sum && solve(idx + 1, sum - arr[idx], arr))
            return true;

        // not take
        return solve(idx + 1, sum, arr);
    }
}
