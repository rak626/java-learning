package MultiThreading._03_locks.types;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {

    private final Lock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " outerMethod");
            innerMethod();
        } finally {
            lock.unlock();
        }
    }

    public void innerMethod() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " innerMethod");
        }  finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantExample example = new ReentrantExample();
        example.outerMethod();

        new Thread(example::outerMethod).start(); // deadlock prevention happening with reentrant lock
    }
}
