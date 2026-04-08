package MultiThreading._06_ExecutorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorFrameworkExample {
    /**
     * normal way
     */
    public static void main_normal(String[] args) {
        long startedTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println(factorial(i));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total time " + (endTime - startedTime) + " ms");
    }

    /**
     * MultiThreading way to solve
     */
    public static void main_NormalThread(String[] args) {
        long startedTime = System.currentTimeMillis();
        Thread[] thread = new Thread[10];
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            thread[i] = new Thread(() -> {
                System.out.println("From " + Thread.currentThread().getName() + " Factorial of " + finalI + " is " + factorial(finalI));
            }, "Thread-" + finalI);
            thread[i].start();
        }
        for (int i = 0; i < 10; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total time " + (endTime - startedTime) + " ms");
    }

    public static void main(String[] args) {
        long startedTime = System.currentTimeMillis();
        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                executorService.submit(() -> {
                    System.out.println("From " + Thread.currentThread().getName() + " Factorial of " + finalI + " is " + factorial(finalI));
                });
            }
            executorService.shutdown();
            try {
                while(!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)){
                    System.out.println("Waiting for all threads to finish");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Total time " + (endTime - startedTime) + " ms");
        }
    }

    private static long factorial(int n) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
