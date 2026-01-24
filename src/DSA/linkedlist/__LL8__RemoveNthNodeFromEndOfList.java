package DSA.linkedlist;

/**
 * Problem: Remove Nth Node From End Of List
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/">Remove Nth Node From End Of List</a></li>
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
public class __LL8__RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move fast n steps ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Move both until fast reaches end
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Delete node
        slow.next = slow.next.next;

        return dummy.next;
    }
}
