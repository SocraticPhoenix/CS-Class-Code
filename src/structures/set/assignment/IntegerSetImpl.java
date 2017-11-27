package structures.set.assignment;

import java.util.Iterator;

public class IntegerSetImpl implements IntegerSet {
    private int[] array;
    private int size;

    public IntegerSetImpl(int initialCapacity) {
        this.array = new int[initialCapacity];
        this.size = 0;
    }

    public IntegerSetImpl() {
        this(20);
    }

    private void expandIfNeeded() {
        if (size == array.length) {
            int[] replacement = new int[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                replacement[i] = array[i];
            }
            array = replacement;
        }
    }

    public boolean add(int a) {
        if (!contains(a)) {
            expandIfNeeded();
            array[size] = a;
            size++;
            return true;
        }

        return false;
    }

    public boolean addAll(int... a) {
        boolean result = false;
        for (int i = 0; i < a.length; i++) {
            result = add(i) || result;
        }
        return result;
    }

    public boolean remove(int a) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == a) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            int[] result = new int[array.length];
            for (int i = 0; i < index; i++) {
                result[i] = array[i];
            }

            for (int i = index + 1; i < array.length; i++) {
                result[i - 1] = array[i];
            }

            array = result;
            size--;
            return true;
        }
        return false;
    }

    public boolean removeAll(int... a) {
        boolean result = false;
        for (int i = 0; i < a.length; i++) {
            result = remove(i) || result;
        }
        return result;
    }

    public boolean contains(int a) {
        for (int i = 0; i < size; i++) {
            if (array[i] == a) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(int... a) {
        for (int i = 0; i < a.length; i++) {
            if (!contains(a[i])) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size != 0;
    }

    public IntegerSet union(IntegerSet other) {
        IntegerSet result = new IntegerSetImpl();
        for (int a : other) {
            result.add(a);
        }

        for (int b : this) {
            result.add(b);
        }

        return result;
    }

    public IntegerSet intersect(IntegerSet other) {
        IntegerSet result = new IntegerSetImpl();
        for (int a : other) {
            if (contains(a)) {
                result.add(a);
            }
        }

        return result;
    }

    public int[] toArray() {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public Iterator<Integer> iterator() {
        return new IntegerIterator(array);
    }

    public boolean equals(Object other) {
        if (other instanceof IntegerSet) {
            IntegerSet iset = (IntegerSet) other;
            if (iset.size() == size) {
                for (int a : iset) {
                    if (!contains(a)) {
                        return false;
                    }
                }

                return true;
            }
        }

        return false;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < size; i++) {
            builder.append(array[i]);
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

}
