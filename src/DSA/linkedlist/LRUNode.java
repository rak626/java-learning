package DSA.linkedlist;


public class LRUNode<K, V> {
    K key;
    V val;
    LRUNode<K, V> prev;
    LRUNode<K, V> next;

    LRUNode(K key, V val) {
        this.key = key;
        this.val = val;
    }
}
