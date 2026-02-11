package DSA.linkedlist;


public class DoublyListNode<K, V> {
    K key;
    V val;
    DoublyListNode<K, V> prev;
    DoublyListNode<K, V> next;

    DoublyListNode(K key, V val) {
        this.key = key;
        this.val = val;
    }
}
