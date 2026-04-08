package MultiThreading._05_ThreadCommunication;


class SharedResource {
    private int data;
    private boolean hasData;

    public synchronized void produce(int value) {
        /*
           when hasData is true that means some data in buffer, then wait for buffer to consumed
           otherwise produce that data
         */
        while (hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify();
    }


    public synchronized int consume() {
        /*
          when hasData is false, means no data present on buffer, then wait
          otherwise make hasData false & notify producer thread
         */
        while (!hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        System.out.println("Consumed: " + data);
        notify();
        return data;
    }
}


class Producer implements Runnable {
    private SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            resource.produce(i);
        }
    }
}

class Consumer implements Runnable {
    private SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int value = resource.consume();
        }
    }
}

public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producerThread = new Thread(new Producer(resource), "Producer Thread");
        Thread consumerThread = new Thread(new Consumer(resource), "Consumer Thread");
        producerThread.start();
        consumerThread.start();
    }

}

/*
  Output
  Produced: 0
  Consumed: 0
  Produced: 1
  Consumed: 1
  Produced: 2
  Consumed: 2
  Produced: 3
  Consumed: 3
  Produced: 4
  Consumed: 4
  Produced: 5
  Consumed: 5
  Produced: 6
  Consumed: 6
  Produced: 7
  Consumed: 7
  Produced: 8
  Consumed: 8
  Produced: 9
  Consumed: 9
 */