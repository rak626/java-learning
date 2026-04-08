package MultiThreading._06_ExecutorFramework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
        scheduler.schedule(
                () -> System.out.println(Thread.currentThread().getName() + " Hello World!"),
                2, TimeUnit.SECONDS
        );
        scheduler.scheduleAtFixedRate(
                () -> System.out.println(Thread.currentThread().getName() + " Scheduled at fixed rate of 2 seconds"),
                4, 2, TimeUnit.SECONDS
        );

        scheduler.scheduleWithFixedDelay(
                () -> System.out.println(Thread.currentThread().getName() + " Scheduled with fixed delay of 5 seconds"),
                1, 5, TimeUnit.SECONDS
        );
        scheduler.schedule(() -> {
            System.out.println(Thread.currentThread().getName() + " Initiating Shutdown ...");
            scheduler.shutdown();
        }, 30, TimeUnit.SECONDS);
    }
}
