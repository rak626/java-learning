package DSA.linkedlist;

import java.util.HashMap;
import java.util.Map;


/**
 * Problem: Lru Cache
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/lru-cache/description/">Lru Cache</a></li>
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
public class __LL19__LRUCache {
    class LRUCache<K, V> {

        private final Map<K, LRUNode<K, V>> map;
        private final LRUNode<K, V> head;
        private final LRUNode<K, V> tail;
        private final int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;

            head = new LRUNode<>(null, null);
            tail = new LRUNode<>(null, null);

            head.next = tail;
            tail.prev = head;

            map = new HashMap<>();
        }

        public V get(K key) {
            LRUNode<K, V> node = map.get(key);

            if (node == null) return null;

            deleteNode(node);
            addAfterHead(node);

            return node.val;
        }

        public void put(K key, V value) {

            if (capacity == 0) return;

            LRUNode<K, V> node = map.get(key);

            if (node != null) {
                node.val = value;
                deleteNode(node);
                addAfterHead(node);
                return;
            }

            if (map.size() == capacity) {
                LRUNode<K, V> lru = tail.prev;
                deleteNode(lru);
                map.remove(lru.key);
            }

            LRUNode<K, V> newNode = new LRUNode<>(key, value);
            map.put(key, newNode);
            addAfterHead(newNode);
        }

        private void deleteNode(LRUNode<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addAfterHead(LRUNode<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }
    }
}

/*
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
