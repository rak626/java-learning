package DSA.linkedlist;

public class __LL2__DeleteNodeInLinkedList {

    private static ListNode createLLFromArray(int[] arr) {
        var dummy = new ListNode(0);
        var cur = dummy;
        for (int ele : arr) {
            cur.next = new ListNode(ele);
            cur = cur.next;
        }
        return dummy.next;
    }


    private static void printLL(ListNode head) {
        System.out.print("[ ");
        while (head != null) {
            if (head.next == null) {
                System.out.print(head.val);
            } else {
                System.out.print(head.val + " -> ");
            }
            head = head.next;
        }
        System.out.println(" ]");
    }

    private static ListNode removeHead(ListNode head) {
        if (head == null) return null;
        var temp = new ListNode(-1);
        temp.next = head.next;
        head.next = null;
        return temp.next;
    }

    private static void deleteNode(ListNode head, int target) {
        if (head.val == target) {
            removeHead(head);
            return;
        }
        var temp = head;
        while (temp.next != null) {
            if (temp.next.val == target) {
                var deletedNode = temp.next;
                temp.next = deletedNode.next;
                deletedNode.next = null;
                return;
            }
            temp = temp.next;
        }
    }

    // this will not work, because java always works on pass by value, not pass by reference
    private static void removeHeadPos(ListNode head) {
        head = head.next;
    }


    public void deleteNode(ListNode node) {
        var temp = node.next;
        node.val = temp.val;
        node.next = temp.next;
        temp.next = null;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        ListNode head = createLLFromArray(arr);
        printLL(head);
        head = removeHead(head);
        printLL(head);
        deleteNode(head, 4);
        printLL(head);
    }
}
