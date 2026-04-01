package Collections.QueueInterface;

import java.util.Collections;
import java.util.PriorityQueue;

public class PriorityQueueInterfaceDemo {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(1);
        pq.offer(2);
        pq.offer(3);
        pq.offer(1);
        pq.offer(70);
        pq.offer(10);


//        while(!pq.isEmpty()){
//            System.out.println(pq.poll());
//        }

        PriorityQueue<StudentMarks> spq = new PriorityQueue<StudentMarks>();

        spq.offer(new StudentMarks(45, 40, 38));
        spq.offer(new StudentMarks(40, 27, 80));
        spq.offer(new StudentMarks(78, 65, 98));
        spq.offer(new StudentMarks(77, 69, 91));
        spq.offer(new StudentMarks(77, 40, 75));
        spq.offer(new StudentMarks(100, 95, 97));

        // write now it will throw class cast exception, because it did not provide comparison properties
//        System.out.println(spq);

        /*
         * There is __2__-way to provide comparison properties
         * 1. implementing comparable interface
         * 2. passing new instance of Comparator class in pq declaration;
         * */

        while (!spq.isEmpty()) {
            System.out.println(spq.poll());
        }

        System.out.println("\n\n\n ------comparator interface----------");

//        PriorityQueue<StudentMarks> spq1 = new PriorityQueue<StudentMarks>(new Comparator<StudentMarks>() {
//            @Override
//            public int compare(StudentMarks obj1, StudentMarks obj2) {
//                return obj1.getMath() - obj2.getMath();
//            }
//        });
        PriorityQueue<StudentMarks> spq1 = new PriorityQueue<StudentMarks>((a, b) -> a.getMath() - b.getMath());

        spq1.offer(new StudentMarks(45, 40, 38));
        spq1.offer(new StudentMarks(40, 27, 80));
        spq1.offer(new StudentMarks(78, 65, 98));
        spq1.offer(new StudentMarks(77, 69, 91));
        spq1.offer(new StudentMarks(77, 40, 75));
        spq1.offer(new StudentMarks(100, 95, 97));


        while (!spq1.isEmpty()) {
            System.out.println(spq1.poll());
        }


    }


}
