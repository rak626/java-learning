package MultiThreading._03_locks.types;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnFairLockExample {

    private final Lock unFairLock = new ReentrantLock(true);

    public void accessResource() {
        unFairLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": acquired the lock");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the lock");
            unFairLock.unlock();
        }
    }

    public static void main(String[] args) {
        UnFairLockExample example = new UnFairLockExample();
        Runnable task = example::accessResource;

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        Thread t3 = new Thread(task, "Thread 3");

        t1.start();
        t2.start();
        t3.start();

        /*
          Without Fairness thread can be starved
          With fairness thread will not be starved
         */
    }
}
