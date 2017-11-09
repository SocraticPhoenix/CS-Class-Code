package structures.set;

public class Test {

    public static void main(String[] args) {
        SetImpl<Integer> obj = new SetImpl<Integer>(10){{
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
