package structures.listadt;

public class ListADT {
    private Object[] backing;
    private int size;

    public ListADT(int initialCapacity) {
        this.backing = new Object[initialCapacity];
        this.size = 0;
    }

    public ListADT() {
        this(20);
    }

    private void expandIfNeeded(int toAdd) {
        if (size + toAdd > backing.length) {
            Object[] newArray = new Object[(this.backing.length + toAdd) * 2];
            System.arraycopy(backing, 0, newArray, 0, backing.length);
            backing = newArray;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void add(int index, Object value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }

        expandIfNeeded(1);
        for (int i = backing.length - 2; i >= index; i--) {
            this.backing[i + 1] = this.backing[i];
        }
        this.backing[index] = value;
        this.size++;
    }

    public void add(Object value) {
        this.add(size, value);
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }

        Object[] newArray = new Object[this.backing.length - 1];
        System.arraycopy(this.backing, 0, newArray, 0, index);
        System.arraycopy(this.backing, index + 1, newArray, index, newArray.length - index);
        this.backing = newArray;
        this.size--;
    }
    
    public void removeAll() {
        this.size = 0;
        this.backing = new Object[20];
    }
    
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        return this.backing[index];
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < this.size; i++) {
            builder.append(this.get(i));
            if (i < this.size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

}
