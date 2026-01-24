package DSA.linkedlist;

/**
 * Problem: Reverse Linked List
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/reverse-linked-list/description/">Reverse Linked List</a></li>
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
public class __LL3__ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        // using 3 pointer.
        ListNode prev = null, cur = head;

        while (cur != null) {
            ListNode curNext = cur.next;

            cur.next = prev;

            prev = cur;
            cur = curNext;
        }
        return prev;
    }


    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) return head;

        var newHead = reverseList(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
