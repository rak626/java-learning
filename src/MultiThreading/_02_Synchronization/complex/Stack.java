package MultiThreading._02_Synchronization.complex;

public class Stack {
    private final int[] array;
    private int stackTop;
    private final Object lock;

    public Stack(int capacity) {
        this.array = new int[capacity];
        this.stackTop = -1;
        lock = new Object();
    }

    public boolean isFull() {
        return stackTop > array.length - 1;
    }

    public boolean isEmpty() {
        return stackTop < 0;
    }

    public boolean push(int value) {
        synchronized (lock){
            if (isFull()) return false;
            ++stackTop;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            array[stackTop] = value;
            return true;
        }
    }

    public int pop() {
        synchronized (lock){
            if (isEmpty()) return Integer.MIN_VALUE;
            int delVal = array[stackTop];
            array[stackTop] = Integer.MIN_VALUE;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            --stackTop;
            return delVal;
        }
    }
}
