package LLD.creational.SingletonDesignPattern;

public class DoubleLockSingletonClass {

    private static DoubleLockSingletonClass object;

    private DoubleLockSingletonClass() {
    }

    public static DoubleLockSingletonClass getInstance() {
        if (object == null) {
            synchronized (DoubleLockSingletonClass.class) {
                if (object == null) {
                    object = new DoubleLockSingletonClass();
                }
            }
        }
        return object;
    }
}

/*
 * Advantages :-
 * It will be faster than the Synchronized Singleton Class
 * as it only do lock and unlock once during initialization of object
 * */

/*
 * Disadvantages :-
 * if we observed in production scenario,
 * let assume __2__ thread want to create this object at same time,
 * and both thread is running in different core of the cpu,
 * when they are checking object == null condition,
 * generally every core check their own L1 cache,
 * then let assume thread t1, present in core c1, create the object, and modify the L1 cache of c1,
 * then second thread t2, present in core c2, wants to create object, sees object == null in L1 cache of c2,
 * then t2 will also create another object. which violets the singleton nature.
 * that issue is covered in Modified Double Lock Singleton class
 * */