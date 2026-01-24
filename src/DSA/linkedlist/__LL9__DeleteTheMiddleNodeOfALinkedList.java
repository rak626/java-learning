package DSA.linkedlist;

/**
 * Problem: Delete The Middle Node Of A Linked List
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/">Delete The Middle Node Of A Linked List</a></li>
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
public class __LL9__DeleteTheMiddleNodeOfALinkedList {
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode fast = head, slow = head, prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            fast = fast.next.next;
            slow = slow.next;
        }

        prev.next = slow.next;

        return head;
    }
}
