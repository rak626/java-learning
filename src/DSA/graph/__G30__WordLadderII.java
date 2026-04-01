package DSA.graph;

import java.util.*;

/**
 * Problem: Word Ladder II
 * <ul>
 *   <li>Link: <a href="https://leetcode.com/problems/word-ladder-ii/description/">Word Ladder II</a></li>
 *   <li>Difficulty: Hard</li>
 *   <li>Tags: Graph, BFS, Backtracking</li>
 * </ul>
 * <p>
 * Description:
 * Given __2__ words (beginWord and endWord) and a dictionary wordList,
 * return all the shortest transformation sequences from beginWord to endWord.
 * Each transformation changes exactly __1__ letter and must exist in the wordList.
 * If there is no such sequence, return an empty list.
 */
public class __G30__WordLadderII {

    // --------------------------------------------------------
    // 1️⃣ Optimized BFS + DFS Approach
    // --------------------------------------------------------

    /**
     * Optimized Approach:
     * - BFS builds the graph of valid "next word" connections for shortest paths only.
     * - DFS backtracks through this graph to collect all possible shortest sequences.
     * <p>
     * Time: O(N * M * 26 + P * L)
     * Space: O(N * M)
     * where N = number of words, M = word length,
     * P = number of shortest paths, L = path length
     */
    public List<List<String>> findLaddersOptimized(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> results = new ArrayList<>();
        if (!dict.contains(endWord)) return results;

        // Graph: word -> list of next words in shortest paths
        Map<String, List<String>> neighbors = new HashMap<>();
        // Distance: shortest distance from beginWord to each word
        Map<String, Integer> distance = new HashMap<>();

        // Step 1: BFS to build shortest-path graph
        bfs(beginWord, endWord, dict, neighbors, distance);

        // Step 2: DFS to collect all shortest sequences
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, neighbors, distance, path, results);

        return results;
    }

    private void bfs(String beginWord, String endWord, Set<String> dict,
                     Map<String, List<String>> neighbors, Map<String, Integer> distance) {
        // Initialize graph adjacency lists
        for (String word : dict) {
            neighbors.put(word, new ArrayList<>());
        }
        neighbors.put(beginWord, new ArrayList<>());

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean foundEnd = false; // stop once shortest path to endWord is reached

            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                int currDist = distance.get(word);

                // Find all valid next words (1 letter difference)
                for (String next : getNeighbors(word, dict)) {
                    neighbors.get(word).add(next); // connect word -> next in graph

                    // If 'next' not visited yet, assign distance and enqueue
                    if (!distance.containsKey(next)) {
                        distance.put(next, currDist + 1);
                        if (next.equals(endWord)) {
                            foundEnd = true; // we found the endWord, stop after this level
                        } else {
                            queue.offer(next);
                        }
                    }
                }
            }

            if (foundEnd) break; // ensure only shortest paths are kept
        }
    }

    private void dfs(String word, String endWord, Map<String, List<String>> neighbors,
                     Map<String, Integer> distance, List<String> path, List<List<String>> results) {
        // Base case: reached target word
        if (word.equals(endWord)) {
            results.add(new ArrayList<>(path));
            return;
        }

        // Explore only neighbors that are exactly +1 step further
        for (String next : neighbors.get(word)) {
            if (distance.get(next) == distance.get(word) + 1) {
                path.add(next);                // choose
                dfs(next, endWord, neighbors, distance, path, results); // recurse
                path.remove(path.size() - 1);  // backtrack
            }
        }
    }

    private List<String> getNeighbors(String word, Set<String> dict) {
        List<String> res = new ArrayList<>();
        char[] chars = word.toCharArray();

        // Try changing each character to 'a'..'z'
        for (int i = 0; i < chars.length; i++) {
            char old = chars[i];
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch == old) continue;
                chars[i] = ch;
                String newWord = new String(chars);
                if (dict.contains(newWord)) {
                    res.add(newWord);
                }
            }
            chars[i] = old; // restore
        }
        return res;
    }

    // --------------------------------------------------------
    // 2️⃣ BFS-only Approach (No DFS)
    // --------------------------------------------------------

    /**
     * BFS-only Approach:
     * - Each queue element is a full path.
     * - Once endWord is found at a level, stop and collect all paths of that level.
     * <p>
     * Time: O(N * M * 26 * P) in worst case
     * Space: O(N * P * L)
     * where P = number of shortest paths, L = path length
     */
    public List<List<String>> findLaddersBFSOnly(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> results = new ArrayList<>();
        if (!dict.contains(endWord)) return results;

        Queue<List<String>> queue = new LinkedList<>();
        queue.offer(Collections.singletonList(beginWord));

        Set<String> visited = new HashSet<>();
        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            Set<String> levelVisited = new HashSet<>(); // words visited at this BFS level

            for (int i = 0; i < size; i++) {
                List<String> path = queue.poll();
                String lastWord = path.get(path.size() - 1);

                if (lastWord.equals(endWord)) {
                    results.add(new ArrayList<>(path));
                    found = true; // collect only shortest paths
                }

                char[] chars = lastWord.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char old = chars[j];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == old) continue;
                        chars[j] = ch;
                        String nextWord = new String(chars);

                        // Only consider words from dict not yet used in previous levels
                        if (dict.contains(nextWord) && !visited.contains(nextWord)) {
                            List<String> newPath = new ArrayList<>(path);
                            newPath.add(nextWord);
                            queue.offer(newPath);       // extend path
                            levelVisited.add(nextWord); // mark for this level
                        }
                    }
                    chars[j] = old; // restore
                }
            }

            // Mark words as visited after the whole level is processed
            visited.addAll(levelVisited);
        }
        return results;
    }
}
