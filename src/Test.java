import structures.linkedlist.LinkedList;

public class Test {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList() {{
            add(5);
            add(53);
            add(4312);
            add(4312, 0);
        }};

        System.out.println(linkedList);
    }

}
