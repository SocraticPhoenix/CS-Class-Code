package structures.intlist;

import java.util.Arrays;
import java.util.List;

public class IntegerListSimpleTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("one", "two", "three");
        for (String s : list) { //Type 1
            System.out.println(s);
        }

        System.out.println("---");

        list.forEach(s -> System.out.println(s)); //Type 2

        System.out.println("---");

        list.forEach(System.out::println); //Type 3

    }

}
