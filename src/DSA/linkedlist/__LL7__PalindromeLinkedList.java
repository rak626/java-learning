package DSA.linkedlist;

/**
 * Problem: Palindrome Linked List
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/palindrome-linked-list/description/">Palindrome Linked List</a></li>
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
public class __LL7__PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        var fast = head;
        var slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode secondHalf = reverse(slow);
        ListNode firstHalf = head;

        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) return false;
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;

        var newHead = reverse(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
