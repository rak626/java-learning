package DSA.linkedlist;


/**
 * Problem: Sort Linked List Of 0s 1s And 2s
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/Sort-linked-list-of-0s-1s-and-2s/description/">Sort Linked List Of 0s 1s And 2s</a></li>
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
public class __LL11__SortLinkedListOf0s1sAnd2s {
    public Node segregate(Node head) {
        Node zero = new Node(-1);
        Node one = new Node(-1);
        Node two = new Node(-1);

        Node zerop = zero, onep = one, twop = two;
        Node cur = head;

        while (cur != null) {
            Node next = cur.next; // save next
            cur.next = null;      // detach node

            switch (cur.data) {
                case 0:
                    zerop.next = cur;
                    zerop = cur;
                    break;
                case 1:
                    onep.next = cur;
                    onep = cur;
                    break;
                case 2:
                    twop.next = cur;
                    twop = cur;
                    break;
            }
            cur = next;
        }

        zerop.next = one.next;
        onep.next = two.next;

        return zero.next;
    }
}
