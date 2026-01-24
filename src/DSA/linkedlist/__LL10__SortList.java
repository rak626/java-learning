package DSA.linkedlist;

/**
 * Problem: Sort List
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/sort-list/description/">Sort List</a></li>
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
public class __LL10__SortList {
    public ListNode sortList(ListNode head) {
        // base condition, either empty list or one node
        if (head == null || head.next == null) return head;

        // split the ll into two halves
        ListNode mid = getMid(head);
        ListNode right = mid.next;
        mid.next = null; // cut the link , make ll into two half

        // sort both half
        ListNode left = sortList(head);
        right = sortList(right);

        // merge both half
        return merge(left, right);
    }

    public ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left != null) {
            cur.next = left;
        }
        if (right != null) {
            cur.next = right;
        }
        return dummy.next;
    }

    private ListNode getMid(ListNode head) {
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
