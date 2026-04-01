package DSA.graph;

import DSA.utils.DisjointSet;

import java.util.*;

/**
 * Problem: Accounts Merge
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/accounts-merge/description/">Accounts Merge</a></li>
 * <li>Difficulty: Medium</li>
 * <li>Tags: graph, dsu</li>
 * </ul>
 * <p>
 * Approach:
 * - Use Disjoint Set Union (DSU) to merge accounts sharing emails.
 * - Iterate through each account:
 *     - For each email, if it's seen before, union the current account index with the existing __1__.
 *     - Otherwise, map the email to the current account index.
 * - After union operations, the parent of each account represents a merged group.
 * - Group emails by their parent, sort them, and prepend the username.
 * - Alternative approach: Build a graph of emails and use DFS/BFS to find connected components.
 * </p>
 * <p>
 * Time Complexity: O(M log M), where M = total number of emails.
 *   - DSU operations are nearly O(1) per union/find.
 *   - Sorting emails in each group dominates the complexity.
 * Space Complexity: O(N + M), for DSU arrays, email mapping, and group storage.
 * </p>
 * <p>
 * Notes:
 * - Mapping emails to account index is the key trick to efficiently detect overlaps.
 * - DSU helps in merging connected components without explicitly building a graph.
 * - Keep in mind: each email belongs to only __1__ user; final result emails must be sorted.
 * </p>
 */
public class __G50__AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        Map<String, Integer> emailToParent = new HashMap<>();

        // created the DSU
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (emailToParent.containsKey(email)) {
                    ds.unionBySize(i, emailToParent.get(email));
                } else {
                    emailToParent.put(email, i);
                }
            }
        }

        // group them
        Map<Integer, List<String>> groups = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailToParent.entrySet()) {
            int uParent = ds.find(entry.getValue());
            groups.computeIfAbsent(uParent, k -> new ArrayList<>()).add(entry.getKey());
        }

        List<List<String>> res = new ArrayList<>();

        // build result
        for (var entry : groups.entrySet()) {
            int parent = entry.getKey();
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            var merge = new ArrayList<String>();
            merge.add(accounts.get(parent).get(0));
            merge.addAll(emails);
            res.add(merge);
        }

        return res;
    }
}
