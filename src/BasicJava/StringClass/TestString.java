package BasicJava.StringClass;

public class TestString {
    int a = 5;

    public static void main(String[] args) {
        String x = "ram";
        String a = new String("ram"); // string object creation
        String b = new String(x);
        String c = "ram"; // String literals
        String d = "ram";

        // false, because we are comparing two different object location
        System.out.println(a == b);
        // true, because in string literal, it uses string pool for storing the object,
        // if object value is same, then same object reference will be shared, no new object creation happens
        // so, for c and d both time it is same object from string pool;
        System.out.println(c == d);
    }
}
