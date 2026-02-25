package DSA.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Permutations
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/permutations/description/">Permutations</a></li>
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
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        findPermute(0, nums, ans);
        return ans;
    }

    private void findPermute(int idx, int[] nums, List<List<Integer>> ans) {
        // base case
        if (idx == nums.length) {
            List<Integer> perm = new ArrayList<>();
            for (int n : nums) {
                perm.add(n);
            }
            ans.add(perm);
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            swap(nums, idx, i);  // choose
            findPermute(idx + 1, nums, ans);  // explore
            swap(nums, idx, i);    // undo (backtrack)
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
