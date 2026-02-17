package BasicJava.AbstractClass;

public class Dog extends Animal {

    protected Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
}
