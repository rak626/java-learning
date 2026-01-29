package DSA.linkedlist;

import DSA.utils.Node;

import java.util.HashMap;

/**
 * Problem: Copy List With Random Pointer
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/copy-list-with-random-pointer/description/">Copy List With Random Pointer</a></li>
 * <li>Difficulty: Medium</li>
 * <li>Tags: linkedlist</li>
 * </ul>
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __LL18__CopyListWithRandomPointer {

    /**
     * With the help of Hashmap
     */
    public Node copyRandomList(Node head) {
        var map = new HashMap<Node, Node>();

        Node cur = head;

        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;

        while (cur != null) {
            Node copy = map.get(cur);
            copy.next = map.get(cur.next);
            copy.random = map.get(cur.random);
            cur = cur.next;
        }

        return map.get(head);
    }


    /**
     *  O(1) space solution with 3 passes. O(3N) time
     */
    public Node copyRandomListOptimized(Node head) {
        if (head == null) return null;
        Node cur = head;

        // new node created beside the cur node
        while (cur != null) {
            Node temp = new Node(cur.val);
            temp.next = cur.next;
            cur.next = temp;
            cur = temp.next;
        }

        cur = head;

        // place random pointer
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // detach the list
        Node newHead = head.next;
        cur = head;
        while (cur != null) {
            Node copy = cur.next;
            cur.next = copy.next; // restore original list
            copy.next = (cur.next != null) ? cur.next.next : null; // build copied list
            cur = cur.next;
        }

        return newHead;

    }
}
