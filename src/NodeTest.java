import structures.linkedlist.Node;

public class NodeTest {

    public static void main(String[] args) {
        //step 2
        Node n1 = new Node('a');
        Node n2 = new Node('b');
        Node n3 = new Node('c');
        Node n4 = new Node('d');

        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);

        //step 3
        System.out.println(n1.getVal());
        System.out.println(n1.getNext().getVal());
        System.out.println(n1.getNext().getNext().getVal());
        System.out.println(n1.getNext().getNext().getNext().getVal());

        //step 4
        n2.setNext(null);
        n3.setNext(null);
        n4.setNext(null);

        System.out.println(n1.getVal());

        //step 5
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);

        Node curr = n1;
        while (curr != null) {
            System.out.println(curr.getVal());
            curr = curr.getNext();
        }
    }

}
