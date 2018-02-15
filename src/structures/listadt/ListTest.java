package structures.listadt;

public class ListTest {

    public static void main(String[] args) {
        ListADT list = new ListADT();
        list.add("hi");
        list.add("hiii");
        list.add("bob");
        list.add(0, "long");
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
    }

}
