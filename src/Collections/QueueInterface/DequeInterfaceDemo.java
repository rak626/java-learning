package Collections.QueueInterface;

import java.util.Deque;
import java.util.LinkedList;

public class DequeInterfaceDemo {
    public static void main(String[] args) {
        // if we want deque to be queue better to go with linkedlist, and for stack go with ArrayDeque;
        Deque<Integer> deque = new LinkedList<>();

        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerLast(3);
        deque.offerLast(4);


        System.out.println(deque);

        System.out.println(deque.pollLast());
        System.out.println(deque.pollFirst());

        System.out.println(deque);


    }
}
