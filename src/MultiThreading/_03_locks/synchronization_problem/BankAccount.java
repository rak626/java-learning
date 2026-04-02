package MultiThreading._03_locks.synchronization_problem;

public class BankAccount {
    private int balance = 100;

    public synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " processing withdraw " + amount);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " completed withdraw " + amount);
        } else {
            System.out.println(Thread.currentThread().getName() + " failed withdraw " + amount);
        }
    }
}
