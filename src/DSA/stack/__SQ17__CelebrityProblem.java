package DSA.stack;

/**
 * <h2>🟢 Celebrity Problem</h2>
 *
 * <b>Problem Statement:</b>
 * There are N people at a party. A celebrity is defined as someone who:
 * <ul>
 *     <li>Is known by everyone else</li>
 *     <li>Does not know anyone</li>
 * </ul>
 *
 * You are given a matrix <code>mat[][]</code> where:
 * <pre>
 * mat[i][j] == 1 → Person i knows Person j
 * mat[i][j] == 0 → Person i does NOT know Person j
 * </pre>
 *
 * Return the index of the celebrity if present, otherwise return -1.
 *
 * <hr>
 *
 * <h3>🔗 Problem Link</h3>
 * <ul>
 *   <li>
 *     GFG:
 *     <a href="https://www.geeksforgeeks.org/problems/the-celebrity-problem/1">
 *     The Celebrity Problem</a>
 *   </li>
 * </ul>
 *
 * <hr>
 *
 * <h3>🧠 Key Learning</h3>
 * <ul>
 *   <li>Candidate Elimination Pattern</li>
 *   <li>Stack Reduction Thinking</li>
 *   <li>Two Pointer Optimization</li>
 * </ul>
 *
 * <hr>
 *
 * <h3>📊 Solution Progression</h3>
 *
 * <table border="1">
 *   <tr>
 *     <th>Approach</th>
 *     <th>Time</th>
 *     <th>Space</th>
 *   </tr>
 *   <tr>
 *     <td>Brute Force (Counting)</td>
 *     <td>O(n²)</td>
 *     <td>O(n)</td>
 *   </tr>
 *   <tr>
 *     <td>Stack Elimination</td>
 *     <td>O(n)</td>
 *     <td>O(n)</td>
 *   </tr>
 *   <tr>
 *     <td>Two Pointer (Optimal)</td>
 *     <td>O(n)</td>
 *     <td>O(1)</td>
 *   </tr>
 * </table>
 */
public class __SQ17__CelebrityProblem {

    /**
     * <h3>🔴 Brute Force — Counting In-degree & Out-degree</h3>
     *
     * <b>Idea:</b>
     * Count how many people each person knows (out-degree)
     * and how many people know them (in-degree).
     * <p>
     * <b>Celebrity Conditions:</b>
     * <ul>
     *     <li>Out-degree = 0 → Celebrity knows nobody</li>
     *     <li>In-degree = n-1 → Everyone knows celebrity</li>
     * </ul>
     *
     * <b>Time Complexity:</b> O(n²)
     * <b>Space Complexity:</b> O(n)
     */
    public int celebrityBrute(int[][] mat) {

        int n = mat.length;

        int[] known = new int[n];
        int[] iknow = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && mat[i][j] == 1) {
                    iknow[i]++;
                    known[j]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (known[i] == n - 1 && iknow[i] == 0) return i;
        }

        return -1;
    }


    /**
     * <h3>🟡 Better — Stack Based Candidate Elimination</h3>
     *
     * <b>Idea:</b>
     * Push all people into a stack. Compare two at a time and eliminate
     * one non-celebrity candidate per comparison.
     * <p>
     * If A knows B → A cannot be celebrity
     * If A does NOT know B → B cannot be celebrity
     * <p>
     * After elimination, verify the remaining candidate.
     * <p>
     * <b>Time Complexity:</b> O(n)
     * <b>Space Complexity:</b> O(n)
     */
    public int celebrityBetter(int[][] mat) {

        int n = mat.length;
        java.util.Stack<Integer> st = new java.util.Stack<>();

        for (int i = 0; i < n; i++) st.push(i);

        while (st.size() > 1) {

            int a = st.pop();
            int b = st.pop();

            if (mat[a][b] == 1) st.push(b);
            else st.push(a);
        }

        int candidate = st.pop();

        for (int i = 0; i < n; i++) {
            if (i == candidate) continue;
            if (mat[candidate][i] == 1 || mat[i][candidate] == 0) return -1;
        }

        return candidate;
    }


    /**
     * <h3>🟢 Optimal — Two Pointer Candidate Elimination</h3>
     *
     * <b>Idea:</b>
     * Maintain two pointers. Eliminate one or two candidates per step
     * based on knowing relationship.
     * <p>
     * After elimination, only one candidate remains → verify it.
     * <p>
     * <b>Time Complexity:</b> O(n)
     * <b>Space Complexity:</b> O(1)
     */
    public int celebrity(int[][] mat) {

        int n = mat.length;

        int top = 0;
        int down = n - 1;

        while (top < down) {

            if (mat[top][down] == 1) top++;
            else if (mat[down][top] == 1) down--;
            else {
                top++;
                down--;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i == top) continue;

            if (mat[top][i] == 1 || mat[i][top] == 0) return -1;
        }

        return top;
    }
}
