package DSA.linkedlist;

/**
 * Problem: Odd Even Linked List
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/odd-even-linked-list/description/">Odd Even Linked List</a></li>
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
public class __LL8__OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        var odd = head;
        var even = head.next;
        var head1 = even;

        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = head1;
        return head;
    }
}
