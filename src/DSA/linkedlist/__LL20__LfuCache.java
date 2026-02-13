package DSA.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Lfu Cache
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/lfu-cache/description/">Lfu Cache</a></li>
 * <li>Difficulty: Hard</li>
 * <li>Tags: linkedlist</li>
 * </ul>
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __LL20__LfuCache {
    class LFUNode {
        int key, val, freq;
        LFUNode prev, next;

        public LFUNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }

    class DLL {
        LFUNode head, tail;
        int size;

        public DLL() {
            head = new LFUNode(-1, -1);
            tail = new LFUNode(-1, -1);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void add(LFUNode node) {
            node.next = head.next;
            head.next = node;
            node.prev = head;
            node.next.prev = node;
            size++;
        }

        public void remove(LFUNode node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
            size--;
        }

        public LFUNode removeLast() {
            if (size <= 0) return null;

            LFUNode last = tail.prev;
            remove(last);
            return last;
        }
    }


    class LFUCache {
        private final int capacity;
        private final Map<Integer, LFUNode> nodeMap;
        private final Map<Integer, DLL> freqMap;
        private int minFreq = 0;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            nodeMap = new HashMap<>();
            freqMap = new HashMap<>();
        }

        public int get(int key) {
            LFUNode node = nodeMap.get(key);
            if (node == null) return -1;

            updateFreq(node);
            return node.val;

        }

        public void put(int key, int value) {
            if (capacity == 0) return;
            if (nodeMap.containsKey(key)) {
                LFUNode node = nodeMap.get(key);
                node.val = value;
                updateFreq(node);
                return;
            }
            if (nodeMap.size() == capacity) {
                DLL list = freqMap.get(minFreq);
                LFUNode delNode = list.removeLast();
                nodeMap.remove(delNode.key);
            }

            LFUNode newNode = new LFUNode(key, value);
            minFreq = 1;

            DLL list = freqMap.getOrDefault(1, new DLL());
            list.add(newNode);
            freqMap.put(1, list);
            nodeMap.put(key, newNode);
        }

        private void updateFreq(LFUNode node) {
            int freq = node.freq;

            // remove the node from previous list from freqMap
            DLL list = freqMap.get(freq);
            list.remove(node);

            // if curFreq = minFreq and that curFreq list size is now zero
            // then increase the minFreq
            if (freq == minFreq && list.size == 0) {
                freqMap.remove(freq);
                minFreq++;
            }

            node.freq++;

            // update the node based on curFreq
            // if curFreq is already in freqMap, add node end of the list
            // else create new list & add it behind
            DLL newList = freqMap.getOrDefault(node.freq, new DLL());
            newList.add(node);
            freqMap.put(node.freq, newList);

        }
    }

}
