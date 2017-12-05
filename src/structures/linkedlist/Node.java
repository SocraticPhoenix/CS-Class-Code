package structures.linkedlist;

public class Node {
    private Node next;
    private Object val;

    public Node(Object val) {
        this.val = val;
        this.next = null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

}
