package AdvanceJava.pool;

import java.util.Deque;

public class CustomObjectPool<T> {

    private final Deque<T> pool;
    private final int maxSize;
    private final String name;

    public CustomObjectPool(Deque<T> pool, int maxSize, String name) {
        this.pool = pool;
        this.maxSize = maxSize;
        this.name = name;
    }

    public T borrow() {
        return null;
    }
}