package DSA.linkedlist;

/**
 * Problem: Add 1 To A Number Represented As Linked List
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/add-1-to-a-number-represented-as-linked-list/description/">Add 1 To A Number Represented As Linked List</a></li>
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
public class __LL13__Add1ToANumberRepresentedAsLinkedList {
    /***
     *  with reverse technique, reverse twice & carry propagation.
     */
    public Node addOne(Node head) {
        Node curHead = reverse(head);

        Node temp = curHead, prev = null;
        int carry = 1;
        while(temp != null){

            int sum = temp.data + carry;
            carry = sum / 10;
            temp.data = sum % 10;

            prev = temp;
            temp = temp.next;
        }

        if(carry > 0 ){
            prev.next = new Node(carry);
        }
        return reverse(curHead);
    }

    private Node reverse(Node head){
        if(head == null || head.next == null) return head;
        Node newNode = reverse(head.next);

        head.next.next = head;
        head.next = null;

        return newNode;
    }


    /**
     * without reverse, only carry propagate last 9's
     */
    public Node addOneWithOutReverse(Node head) {
        Node dummy = new Node(0);
        dummy.next = head;

        Node lastNonNine = dummy;
        Node curr = head;

        // Step 1: find rightmost node != 9
        while (curr != null) {
            if (curr.data != 9) {
                lastNonNine = curr;
            }
            curr = curr.next;
        }

        // Step 2: increment it
        lastNonNine.data++;

        // Step 3: zero out all nodes after it
        curr = lastNonNine.next;
        while (curr != null) {
            curr.data = 0;
            curr = curr.next;
        }

        // Step 4: return head (handle 999 case)
        return dummy.data == 0 ? dummy.next : dummy;
    }

}
