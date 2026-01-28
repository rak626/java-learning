package DSA.linkedlist;

public class __LL17__FlattenLinkedList {
    public Node flatten(Node root) {
        // code here
        if(root == null || root.next == null) return root;

        root.next = flatten(root.next);
        root = merge(root, root.next);
        return root;
    }

    private Node merge(Node root1, Node root2) {
        Node dummy = new Node (-1);
        Node temp = dummy;

        while(root1 != null && root2 != null){
            if(root1.data <= root2.data) {
                temp.bottom = root1;
                root1 = root1.bottom;
            } else {
                temp.bottom = root2;
                root2 = root2.bottom;
            }
            temp = temp.bottom;
        }

        if(root1 != null) temp.bottom = root1;
        else temp.bottom = root2;
        return dummy.bottom;
    }
}
