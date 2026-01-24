package DSA.linkedlist;

/**
 * Problem: Linked List Cycle Ii
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/linked-list-cycle-ii/description/">Linked List Cycle Ii</a></li>
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
public class __LL5__LinkedListCycleIi {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;

        // Step 1: Detect cycle
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }

        // Step 2: No cycle case
        if (fast == null || fast.next == null) {
            return null;
        }

        // Step 3: Find entry point of cycle
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}
