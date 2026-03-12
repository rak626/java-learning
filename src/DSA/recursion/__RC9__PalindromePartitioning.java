package DSA.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Palindrome Partitioning
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/palindrome-partitioning/description/">Palindrome Partitioning</a></li>
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
public class __RC9__PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        solve(0, s, ans, res);
        return res;
    }

    private void solve(int ind, String s, List<String> ans, List<List<String>> res) {
        if (ind == s.length()) {
            res.add(new ArrayList<>(ans));
            return;
        }
        for (int i = ind; i < s.length(); i++) {
            String cur = s.substring(ind, i + 1);
            if (isPalindrome(cur)) {
                ans.add(cur);
                solve(i + 1, s, ans, res);
                ans.removeLast();
            }
        }
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;

        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
