package DSA.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: Subsets Ii
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/subsets-ii/description/">Subsets Ii</a></li>
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
public class __RC5__SubsetsIi {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();

        solve(0, nums, subset, result);
        return result;
    }

    private void solve(int idx, int[] nums, List<Integer> subset, List<List<Integer>> result) {

        // add current subset (IMPORTANT)
        result.add(new ArrayList<>(subset));

        for (int i = idx; i < nums.length; i++) {
            // skip duplicates
            if (i > idx && nums[i] == nums[i - 1]) continue;

            subset.add(nums[i]);

            // move forward
            solve(i + 1, nums, subset, result);

            // backtrack
            subset.remove(subset.size() - 1);
        }
    }
}

