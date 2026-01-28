package DSA.linkedlist;

/**
 * Problem: Rotate List
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/rotate-list/description/">Rotate List</a></li>
 * <li>Difficulty: Hard</li>
 * <li>Tags: linkedlist</li>
 * </ul>
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __LL16__RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;
        int len = 1;
        ListNode tail = head, newTail = head, newHead = null;

        while (tail.next != null) {
            len++;
            tail = tail.next;
        }

        // normalize k
        int k1 = k % len;
        if (k1 == 0) return head;

        // find new tail
        for (int i = 0; i < len - k1 - 1; i++) {
            newTail = newTail.next;
        }

        // rewire [newhead, ...  , tail] -> [head, ... , newtail]
        newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        return newHead;

    }
}
