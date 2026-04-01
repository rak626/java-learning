package DSA.graph;

import java.util.*;

/**
 * Problem: Alien Dictionary
 * <ul>
 *   <li>Link: <a href="https://leetcode.com/problems/alien-dictionary/">LeetCode</a></li>
 *   <li>Difficulty: Hard</li>
 *   <li>Tags: Graph, Topological Sort, BFS, DFS</li>
 * </ul>
 *
 * <p>
 * Description:
 * <ul>
 *   <li>Given a sorted list of words in an alien language, determine the order of characters.</li>
 *   <li>If multiple valid orders exist, return any valid order.</li>
 *   <li>If no valid order exists (cycle), return an empty string.</li>
 * </ul>
 * </p>
 *
 * <h3>Approach:</h3>
 * <ul>
 *   <li>Both BFS (Kahn’s Algorithm) and DFS (Reverse Postorder) methods are implemented.</li>
 *   <li>Steps to build the graph:</li>
 *   <ol>
 *     <li>Create a node for each unique character.</li>
 *     <li>Compare adjacent words and add edges from the first differing character.</li>
 *   </ol>
 *   <li>BFS Approach:</li>
 *     <ul>
 *       <li>Compute indegree of each node.</li>
 *       <li>Add nodes with indegree 0 to the queue.</li>
 *       <li>Process queue, reduce indegree of neighbors, and build topological order.</li>
 *       <li>Check if order includes all characters (cycle detection).</li>
 *     </ul>
 *   <li>DFS Approach:</li>
 *     <ul>
 *       <li>Use state array: 0 = unvisited, 1 = visiting, 2 = visited.</li>
 *       <li>DFS recursively visits neighbors, detects cycles, and appends nodes postorder.</li>
 *       <li>Reverse the postorder list to get topological order.</li>
 *     </ul>
 * </ul>
 *
 * <h3>Complexity:</h3>
 * <ul>
 *   <li>Time: O(N * L) — N = number of words, L = average/max word length</li>
 *   <li>Space: O(1) — alphabet size ≤ 26, adjacency list and other structures scale with number of unique letters.</li>
 * </ul>
 */
public class __G26__AlienDictionary {

    // ====================== BFS Approach ======================

    /**
     * Returns a valid character order using BFS (Kahn’s Algorithm).
     *
     * @param words Array of words sorted according to alien language rules.
     * @return String representing __1__ valid character order, or "" if no valid order exists.
     */
    public String alienOrderBFS(String[] words) {
        Map<Character, List<Character>> adj = new HashMap<>();
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1); // mark unused characters

        // Step 1: Initialize graph nodes for all unique characters
        for (String w : words) {
            for (char c : w.toCharArray()) {
                adj.putIfAbsent(c, new ArrayList<>());
                indegree[c - 'a'] = 0;
            }
        }

        // Step 2: Build edges from adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];

            // Edge case: invalid input (prefix issue)
            if (w1.length() > w2.length() && w1.startsWith(w2)) return "";

            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    char u = w1.charAt(j), v = w2.charAt(j);
                    adj.get(u).add(v); // u -> v
                    indegree[v - 'a']++;
                    break;
                }
            }
        }

        // Step 3: Kahn's BFS
        Queue<Character> q = new ArrayDeque<>();
        for (int i = 0; i < 26; i++) if (indegree[i] == 0) q.offer((char) (i + 'a'));

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char node = q.poll();
            sb.append(node);

            for (char nei : adj.getOrDefault(node, new ArrayList<>())) {
                if (--indegree[nei - 'a'] == 0) q.offer(nei);
            }
        }

        // Step 4: Check for cycles
        return sb.length() == adj.size() ? sb.toString() : "";
    }

    // ====================== DFS Approach ======================

    /**
     * Returns a valid character order using DFS (Reverse Postorder).
     *
     * @param words Array of words sorted according to alien language rules.
     * @return String representing __1__ valid character order, or "" if no valid order exists.
     */
    public String alienOrderDFS(String[] words) {
        Map<Character, List<Character>> adj = new HashMap<>();

        // Step 1: Initialize graph nodes
        for (String w : words) {
            for (char c : w.toCharArray()) adj.putIfAbsent(c, new ArrayList<>());
        }

        // Step 2: Build edges from adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];
            if (w1.length() > w2.length() && w1.startsWith(w2)) return "";

            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    adj.get(w1.charAt(j)).add(w2.charAt(j));
                    break;
                }
            }
        }

        // Step 3: DFS with cycle detection
        Map<Character, Integer> state = new HashMap<>(); // 0=unvisited, 1=visiting, 2=visited
        StringBuilder sb = new StringBuilder();

        for (char c : adj.keySet()) {
            if (state.getOrDefault(c, 0) == 0) {
                if (!dfs(c, adj, state, sb)) return "";
            }
        }

        // Step 4: Reverse postorder to get topological order
        return sb.reverse().toString();
    }

    /**
     * Helper DFS method with cycle detection.
     *
     * @param node  Current character
     * @param adj   Adjacency list
     * @param state Map tracking visitation state of characters
     * @param sb    StringBuilder accumulating postorder
     * @return true if no cycle detected from this node, false otherwise
     */
    private boolean dfs(char node, Map<Character, List<Character>> adj,
                        Map<Character, Integer> state, StringBuilder sb) {
        state.put(node, 1); // mark visiting

        for (char nei : adj.get(node)) {
            if (state.getOrDefault(nei, 0) == 1) return false; // cycle detected
            if (state.getOrDefault(nei, 0) == 0) {
                if (!dfs(nei, adj, state, sb)) return false;
            }
        }

        state.put(node, 2); // mark visited
        sb.append(node);    // postorder add
        return true;
    }
}