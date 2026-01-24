package DSA.linkedlist;

/**
 * Problem: Linked List Cycle
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/linked-list-cycle/description/">Linked List Cycle</a></li>
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
public class __LL4__LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        var fast = head;
        var slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
