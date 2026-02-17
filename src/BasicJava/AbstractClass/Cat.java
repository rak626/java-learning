package BasicJava.AbstractClass;

public class Cat extends Animal {

    public Cat(String name , int age) {
        super(name, age);
    }

    @Override
    public void sleep() {
        System.out.println(name + " is sleeping..");
    }
}
