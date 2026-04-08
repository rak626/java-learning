package MultiThreading._04_DeadLock;


class Pen {

    public synchronized void drawWithPenAndPaper(Paper paper) {
        System.out.println(Thread.currentThread().getName() + " is using pen to draw");
        paper.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " is using pen to finish writing");
    }
}

class Paper {

    public synchronized void drawWithPaperAndPen(Pen pen) {
        System.out.println(Thread.currentThread().getName() + " is using paper to draw");
        pen.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " is using paper to finish writing");
    }

}

class Task1 implements Runnable {
    private final Pen pen;
    private final Paper paper;

    public Task1(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
//        pen.drawWithPenAndPaper(paper); // locks pen & tries to lock paper (with pen lock)

        // to resolve this,
        synchronized (paper) {
            pen.drawWithPenAndPaper(paper);
        }
        // this means for running pen, we need lock of paper
    }
}

class Task2 implements Runnable {
    private final Pen pen;
    private final Paper paper;

    public Task2(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
//        paper.drawWithPaperAndPen(pen); // locks paper & tries to lock pen (with paper lock)
        // to resolve this
        synchronized (pen) {
            paper.drawWithPaperAndPen(pen);
        }
        // this means for running paper, we need lock of pen
    }
}

public class DeadLockExample {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread t1 = new Thread(new Task1(pen, paper), "Thread1");
        Thread t2 = new Thread(new Task2(pen, paper), "Thread2");

        t1.start();
        t2.start();
    }
}
