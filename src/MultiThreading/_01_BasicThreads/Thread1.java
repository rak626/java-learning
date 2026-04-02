package MultiThreading._01_BasicThreads;

public class Thread1 extends Thread {

    // overload the super constructor
    public Thread1(String threadName)  {
        super(threadName);
    }

    @Override
    public void run(){
        for (int i = 0 ; i < 5 ; i++){
            System.out.println("Inside Thread1 :   " + Thread.currentThread() + i);
            if(i == 2) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
