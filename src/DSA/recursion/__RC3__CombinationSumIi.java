package DSA.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: Combination Sum Ii
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/combination-sum-ii/description/">Combination Sum Ii</a></li>
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
public class __RC3__CombinationSumIi {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        var subArray = new ArrayList<Integer>();
        solve(0, target, candidates, subArray, result);
        return result;
    }

    private void solve(int idx, int target, int[] arr, List<Integer> subArray, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(subArray));
            return;
        }
        for (int i = idx; i < arr.length; i++) {
            // Skip duplicates at same recursion level
            if (i > idx && arr[i] == arr[i - 1]) continue;
            // Pruning (since sorted)
            if (arr[i] > target) break;
            subArray.add(arr[i]);
            // i + 1 -> element can be used only once
            solve(i + 1, target - arr[i], arr, subArray, result);
            subArray.removeLast();
        }
    }
}
