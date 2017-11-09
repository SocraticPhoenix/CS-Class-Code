package structures.set;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class HashSetImpl<T> implements Set<T> {
    private LinkedList<T>[] array;
    private int size;

    public HashSetImpl(int capacity) {
        this.array = new LinkedList[capacity];
        this.size = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = new LinkedList<>();
        }
    }

    public HashSetImpl() {
        this(20);
    }

    private int index(Object val) {
        return val.hashCode() % this.array.length;
    }

    private void tryExpand() {
        if (size > array.length * 3) {
            LinkedList<T>[] narr = new LinkedList[this.array.length * 2];
            for (int i = 0; i < narr.length; i++) {
                narr[i] = new LinkedList<>();
            }

        }
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
        return this.array[index(o)].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            Iterator<T> current = null;

            {
                for (int i = 0; i < array.length; i++) {
                    LinkedList<T> list = array[i];
                    if (!list.isEmpty()) {
                        index = i;
                        current = list.iterator();
                        break;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                if (current == null) {
                    return false;
                } else if (current.hasNext()) {
                    return true;
                } else {
                    for (int i = this.index + 1; i < array.length; i++) {
                        if (!array[i].isEmpty()) {
                            return true;
                        }
                    }
                    return false;
                }
            }

            @Override
            public T next() {
                if (current.hasNext()) {
                    return current.next();
                } else {
                    int i;
                    for (i = this.index + 1; i < array.length; i++) {
                        if(!array[i].isEmpty()) {
                            current = array[i].iterator();
                            break;
                        }
                    }
                    this.index = i;

                    return current.next();
                }
            }
        };
    }

    @Override
    public Object[] toArray() {
        return toArray(new Object[this.size]);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size()) {
            a = (T1[]) Array.newInstance(a.getClass().getComponentType(), size());
        }
        
        int i = 0;
        for (T o : this) {
            a[i] = (T1) o;
            i++;
        }

        for (int j = i; j < a.length; j++) {
            a[i] = null;
        }
        
        return a;
    }

    @Override
    public boolean add(T t) {
        if (this.contains(t)) {
            return false;
        } else {
            this.size++;
            this.tryExpand();
            this.array[index(t)].add(t);
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        boolean val = this.array[index(o)].remove(o);
        if (val) {
            this.size--;
        }
        return val;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean res = false;
        for (T o : c) {
            res |= add(o);
        }
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean res = false;
        for (LinkedList<T> list : this.array) {
            Iterator<T> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (!c.contains(iterator.next())) {
                    iterator.remove();
                    this.size--;
                    res = true;
                }
            }
        }
        return res;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (Object o : c) {
            res |= remove(o);
        }
        return res;
    }

    @Override
    public void clear() {
        for (LinkedList<T> list : this.array) {
            list.clear();
        }
        this.size = 0;
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
