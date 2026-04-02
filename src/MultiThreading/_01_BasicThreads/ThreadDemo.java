package MultiThreading._01_BasicThreads;

public class ThreadDemo {
    public static void main(String[] args) {

        System.out.println("main starting ..");


        Thread1 thread1 = new Thread1("my first thread");
//        thread1.setDaemon(true);
        thread1.start();
        /*try {
            Thread.sleep(80);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        ThreadFromRunnable threadFromRunnable = new ThreadFromRunnable();
        threadFromRunnable.run();

        Thread thread = new Thread(new ThreadFromRunnable(), "This is thread 2 from runnable");
        thread.start();

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("i am from thread lambda function : ");
            }
        });
        thread2.start();
        System.out.println("main ending ..");


    }
}
