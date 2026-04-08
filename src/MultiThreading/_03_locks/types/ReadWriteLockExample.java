package MultiThreading._03_locks.types;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment() {
        writeLock.lock();
        try {
            count++;
            // this sleep help me understand how cpu scheduling
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount() {
        readLock.lock();
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockExample example = new ReadWriteLockExample();
        Runnable readTask = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " read : " + example.getCount());
            }
        };
        Runnable writeTask = () -> {
            for (int i = 0; i < 10; i++) {
                example.increment();
                System.out.println(Thread.currentThread().getName() + " incremented");
            }
        };

        Thread writerThread = new Thread(writeTask, "writer");
        Thread readerThread1 = new Thread(readTask, "reader1");
        Thread readerThread2 = new Thread(readTask, "reader2");

        writerThread.start();
        readerThread1.start();
        readerThread2.start();

        try {
            writerThread.join();
            readerThread1.join();
            readerThread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("final count : " + example.getCount());
    }
}
