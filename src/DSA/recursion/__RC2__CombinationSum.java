package DSA.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Combination Sum
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/combination-sum/description/">Combination Sum</a></li>
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
public class __RC2__CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> subArray = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        solve(0, target, candidates, subArray, result);
        return result;
    }

    private void solve(int idx, int target, int[] candidates, List<Integer> subArray, List<List<Integer>> result) {
        // base conditions
        if (target == 0) {
            result.add(new ArrayList<>(subArray));
            return;
        }
        if (target < 0) {
            return;
        }
        if (idx == candidates.length) {
            return;
        }
        // take & move on
        if (candidates[idx] <= target) {
            subArray.add(candidates[idx]);
            solve(idx, target - candidates[idx], candidates, subArray, result);
            subArray.removeLast();
        }
        // not take
        solve(idx + 1, target, candidates, subArray, result);

    }
}
