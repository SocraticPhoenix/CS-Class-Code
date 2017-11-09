package structures.set;

import java.util.Set;

public class Test {

    public static void main(String[] args) {
        Set<Integer> obj = new HashSetImpl<Integer>(10){{
            add(5);
            add(10);
            add(5);
            add(51);
            add(53124);
            add(53);
        }};

        for (Integer a : obj) {
            System.out.print(a + " ");
        }

        obj.remove(5);
        obj.remove(53);
        obj.remove(51);
        System.out.println();

        for (Integer a : obj) {
            System.out.print(a + " ");
        }

    }

}
