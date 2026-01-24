package DSA.linkedlist;

/**
 * Problem: Intersection Of Two Linked Lists
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/intersection-of-two-linked-lists/description/">Intersection Of Two Linked Lists</a></li>
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
public class __LL12__IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA, curB = headB;

        while(curA != curB){
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }

        return curA;
    }

}
