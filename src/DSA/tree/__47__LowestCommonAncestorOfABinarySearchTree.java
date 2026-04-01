package DSA.tree;

import DSA.utils.TreeNode;

/**
 * Problem: Lowest Common Ancestor Of A Binary Search Tree
 * Link: <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/">Click here</a>
 * Difficulty: Medium
 * Tags: tree
 * <p>
 /**
 * Finds the Lowest Common Ancestor (LCA) of __2__ nodes in a Binary Search Tree (BST).
 *
 * <p><b>Problem:</b><br>
 * Given a BST and __2__ nodes `p` and `q`, find their lowest common ancestor (LCA).
 * The LCA is the lowest node in the tree that has both `p` and `q` as descendants
 * (a node can be a descendant of itself).
 *
 * <p><b>Intuition:</b><br>
 * Take advantage of the BST property:
 * - All nodes in the left subtree of a node are less than the node's value.
 * - All nodes in the right subtree are greater.
 * <p>
 * So:
 * - If both `p` and `q` are less than the current node → go left.
 * - If both are greater → go right.
 * - Else, the current node is the split point → this is the LCA.
 *
 * <p><b>Approaches:</b></p>
 *
 * <ul>
 *   <li><b>1. Brute Force (General Binary Tree):</b><br>
 *       - Traverse from root to both `p` and `q`, and store the paths.<br>
 *       - Compare paths to find the last common node.<br>
 *       - <b>Time:</b> O(n), <b>Space:</b> O(n)<br>
 *       ❌ Ignores BST structure.
 *   </li>
 *
 *   <li><b>2. Better (Recursive for BST):</b><br>
 *       - Recursively navigate left or right based on the values of `p` and `q`.<br>
 *       - Split point is the LCA.<br>
 *       - <b>Time:</b> O(h), <b>Space:</b> O(h) for recursion stack.<br>
 *       ✔ Uses BST structure.
 *   </li>
 *
 *   <li><b>3. Optimized (Iterative for BST):</b><br>
 *       - Same as recursive, but uses a loop to avoid recursion stack.<br>
 *       - <b>Time:</b> O(h), <b>Space:</b> O(1)<br>
 *       🚀 Most efficient version for BST.
 *   </li>
 * </ul>
 *
 *
 * <p><b>Time Complexity:</b> O(h)
 * <br>Where h = height of the tree. O(log n) for balanced trees, O(n) in the worst case.</p>
 *
 * <p><b>Space Complexity:</b>
 * <ul>
 *   <li>Recursive: O(h) for the call stack</li>
 *   <li>Iterative: O(1)</li>
 * </ul>
 * </p>
 */
public class __47__LowestCommonAncestorOfABinarySearchTree {
    // recursive solution
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    // iterative solution
    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                // Found the split point
                return root;
            }
        }
        return null; // Should not be reached if p and q are guaranteed to be in the tree
    }
}
