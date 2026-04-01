package DSA.graph;

import java.util.*;

/**
 * Problem: Word Ladder
 * <ul>
 *   <li>Link: <a href="https://leetcode.com/problems/word-ladder/description/">Word Ladder</a></li>
 *   <li>Difficulty: Hard</li>
 *   <li>Tags: Graph, BFS</li>
 * </ul>
 * <p>
 * Description:
 * Given __2__ words (beginWord and endWord) and a dictionary wordList,
 * return the length of the shortest transformation sequence from beginWord
 * to endWord, where:
 * <ul>
 *   <li>Only __1__ letter can be changed at a time.</li>
 *   <li>Each transformed word must exist in the wordList.</li>
 * </ul>
 * If no such sequence exists, return 0.
 * <p>
 * Approach:
 * - Use BFS (level-order traversal) starting from beginWord.
 * - For each word, generate all possible __1__-character transformations.
 * - If a transformed word exists in the dictionary, enqueue it and remove from the set (to avoid revisits).
 * - Stop when endWord is reached and return the step count.
 * - If BFS finishes without finding endWord, return 0.
 * <p>
 * Time: O(N * M * 26) where N = number of words, M = word length
 * Space: O(N) for the word set and BFS queue
 */
public class __G29__WordLadder {

    /**
     * Helper record to store a word and the step count to reach it.
     */
    record Node(String word, int step) {}

    /**
     * Computes the shortest transformation sequence from beginWord to endWord.
     *
     * @param beginWord starting word
     * @param endWord   target word
     * @param wordList  list of valid intermediate words
     * @return length of the shortest transformation sequence, or 0 if not possible
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Store dictionary words in a HashSet for O(1) lookups
        Set<String> set = new HashSet<>(wordList);

        // BFS queue initialized with beginWord at step 1
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(beginWord, 1));

        // Standard BFS loop
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            String word = current.word;
            int step = current.step;

            // Found the target word -> return transformation length
            if (word.equals(endWord)) {
                return step;
            }

            // Try changing each character of the current word
            for (int i = 0; i < word.length(); i++) {
                char[] chars = word.toCharArray();

                // Replace with every possible character 'a' to 'z'
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;
                    String nextWord = new String(chars);

                    // If the transformed word exists in the dictionary
                    if (set.contains(nextWord)) {
                        set.remove(nextWord); // remove to avoid revisiting
                        queue.offer(new Node(nextWord, step + 1));
                    }
                }
            }
        }

        // No valid transformation found
        return 0;
    }
}
