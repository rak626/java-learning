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
public class __LL6__LinkedListCycleIii {
    public int lengthOfLoop(ListNode head) {
        ListNode fast = head, slow = head;

        // Step 1: Detect cycle
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                // Step 2: Count cycle length
                return countLoopLength(slow);
            }
        }

        // No cycle
        return 0;
    }

    private int countLoopLength(ListNode meetingPoint) {
        int count = 1;
        ListNode curr = meetingPoint.next;

        while (curr != meetingPoint) {
            count++;
            curr = curr.next;
        }

        return count;
    }
}
