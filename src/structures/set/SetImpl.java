package structures.set;

import java.lang.reflect.Array;
import java.util.*;

public class SetImpl<T> implements Set<T> {
    private T[] array;
    private int size;

    public SetImpl(int size) {
        this.array = (T[]) new Object[size];
        this.size = 0;
    }

    public SetImpl() {
        this(20);
    }

    private void tryExpand() {
        if (size + 1 > array.length) {
            int ns = this.array.length * 2;
            T[] narr = (T[]) new Object[ns];
            System.arraycopy(array, 0, narr, 0, array.length);
            this.array = narr;
        }
    }

    public T removeRandom() {
        int i = new Random().nextInt(this.size);
        T t = array[i];
        T[] narr = (T[]) new Object[this.size - 1];
        this.size--;
        System.arraycopy(this.array, 0, narr, 0, i);
        System.arraycopy(this.array, i + 1, narr, i, this.size - i);
        this.array = narr;
        return t;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (Objects.equals(o, this.array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return this.toArray(new Object[this.size]);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < this.size) {
            a = (T1[]) Array.newInstance(a.getClass().getComponentType(), this.size);
        }
        for (int i = 0; i < this.size; i++) {
            a[i] = (T1) this.array[i];
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        if (this.contains(t)) {
            return false;
        } else {
            this.tryExpand();
            this.size++;
            this.array[this.size - 1] = t;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (Objects.equals(array[i], o)) {
                T[] narr = (T[]) new Object[this.size - 1];
                this.size--;
                System.arraycopy(this.array, 0, narr, 0, i);
                System.arraycopy(this.array, i + 1, narr, i, this.size - i);
                this.array = narr;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean res = false;
        for (T o : c) {
            res |= this.add(o);
        }
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean res = false;
        for (Object a : this) {
            if (!c.contains(a)) {
                res |= this.remove(a);
            }
        }
        return res;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (Object a : c) {
            res |= this.remove(a);
        }
        return res;
    }

    @Override
    public void clear() {
        this.size = 0;
        Arrays.fill(this.array, null);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return array[index++];
            }
        };
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Set && ((Set) other).size() == this.size() && this.containsAll((Collection) other);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(this.toArray());
    }

}
