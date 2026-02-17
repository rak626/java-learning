package BasicJava.AbstractClass;

public abstract class Animal {

    protected String name;
    protected int age;

    public static int CNT = 0;

    protected Animal(String name, int age) {
        this.name = name;
        this.age = age;
        CNT++;
    }

    public abstract void sleep();

    protected static void sayHello() {
        System.out.println("hello is static method");
    }
}
