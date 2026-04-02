package MultiThreading._03_locks.fix_using_lock;

public class Test {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        Runnable task = () -> bankAccount.withdraw(50);
        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();

        /*
          Output:
          Thread 1 attempting to withdraw 50
          Thread 2 attempting to withdraw 50
          Thread 1 processing withdraw 50
          Thread 2 could not acquire lock, try again
          Thread 1 completed withdraw 50
         */
    }
}