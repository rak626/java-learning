package LLD.creational.SingletonDesignPattern;

public class EagerSingletonClass {

    // eagerly declare the object
    private static EagerSingletonClass object = new EagerSingletonClass();

    private EagerSingletonClass() {
    }

    public static EagerSingletonClass getInstance() {
        return object;
    }
}

/*
 * Why it is called Eager ?
 * as we know static variables are loaded when program start,
 * so that means even if there is no use of this object ,
 * but system will create __1__ object
 * this behaviour is called Eager initialization
 *
 */