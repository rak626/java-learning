package MultiThreading._06_ExecutorFramework;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        executorService_Future_Example();
        executorService_Future_Bulk_Example();
    }

    private static void executorService_Future_Example() throws InterruptedException, ExecutionException {
        System.out.println(" Current Thread: " + Thread.currentThread().getName());
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
//            Runnable runnable = () -> "Hello"; // runnable can not return
            Callable<String> callable = () -> "Hello";
            Future<?> future = executorService.submit(callable);
            Future<?> future2 = executorService.submit(() -> {
                // runnable is running
                System.out.println(Thread.currentThread().getName());
            });
            System.out.println(future.get());
            if (future.isDone()) {
                System.out.println("Future has been done");
            }
            executorService.shutdown();

            System.out.println(executorService.isTerminated());
        }
    }

    private static void executorService_Future_Bulk_Example()
            throws InterruptedException, ExecutionException {
        System.out.println(" Current Thread: " + Thread.currentThread().getName());
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {

            Callable<Integer> callable1 = () -> {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " running task 1");
                return 1;
            };
            Callable<Integer> callable2 = () -> {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " running task 2");
                return 2;
            };
            Callable<Integer> callable3 = () -> {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " running task 3");
                return 3;
            };

//            List<Future<Integer>> futures = executorService.invokeAll(List.of(callable1, callable2, callable3));
            try {
                List<Future<Integer>> futures = executorService.invokeAll(
                        List.of(callable1, callable2, callable3), 1, TimeUnit.SECONDS);


                for (Future<Integer> future : futures) {
                    System.out.println(future.get());
                }

            } catch (InterruptedException | CancellationException e) {
                Thread.currentThread().interrupt();
            }
            // invokeAll() block the parent thread.
            // that's why hello world will be print end of the invokeAll()
            System.out.println(" hello world from end of program");


        }
    }
}
