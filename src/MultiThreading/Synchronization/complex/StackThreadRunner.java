package MultiThreading.Synchronization.complex;

public class StackThreadRunner {
    public static void main(String[] args) {
        Stack stack = new Stack(5);

        new Thread(() -> {
            int counter = 0;
            while (++counter < 10)
                System.out.println("Pushing : " + counter + " -> " + stack.push(counter));
        }, "Stack Thread 1").start();
        new Thread(() -> {
            int counter = 0;
            while (++counter < 10)
                System.out.println("Popping : " + counter + " -> " + stack.pop());
        }, "Stack Thread 2").start();
    }
}
