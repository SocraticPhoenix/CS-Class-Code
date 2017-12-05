package structures.linkedlist;

import java.util.Iterator;

public class LinkedList {
    private Node initial;
    private int size;

    public LinkedList() {
        this.size = 0;
    }

    public void add(Object o, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        } else if (index == size) {
            add(o);
        } else if (index == 0) {
            Node next = new Node(o);
            next.setNext(initial);
            initial = next;
            size++;
        } else {
            Node c = new Node(o);
            getNode(index - 1).setNext(c);
            c.setNext(getNode(index));
            size++;
        }
    }

    public void add(Object o) {
        if (initial == null) {
            initial = new Node(o);
        } else {
            last().setNext(new Node(o));
        }
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        if (index == 0) {
            initial = initial.getNext();
            size--;
        } else if (index == size - 1) {
            getNode(index - 1).setNext(null);
        } else {
            getNode(index - 1).setNext(getNode(index + 1));
            getNode(index).setNext(null);
        }
    }

    public Object get(int index) {
        return getNode(index).getVal();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Iterator<Object> iterator = iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next());
            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            Node node = initial;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Object next() {
                Object o = node.getVal();
                node = node.getNext();
                return o;
            }
        };
    }

    private boolean contains(int index) {
        return index >= 0 && index < size;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }

        Node n = initial;
        for (int i = 0; i < index; i++) {
            n = n.getNext();
        }
        return n;
    }

    private Node last() {
        Node k = initial;
        while (k.getNext() != null) {
            k = k.getNext();
        }
        return k;
    }

}
