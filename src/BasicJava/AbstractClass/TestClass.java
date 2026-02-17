package BasicJava.AbstractClass;

public class TestClass {

    public static void main(String[] args) {
        Animal cat = new Cat("Bob", 2);
        cat.sleep();
        Cat puffer = new Cat("Puffer", 2);
        Dog dog = new Dog("Bhulu", 12);
        System.out.println(Cat.CNT);
        Animal.sayHello();
        Dog.sayHello();

        Integer a = 1;
        Integer b = 1;
    }
}
