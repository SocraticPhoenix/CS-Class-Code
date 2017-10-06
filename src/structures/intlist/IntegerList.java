package structures.intlist;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class IntegerList implements Iterable<Integer> {
    private int[] backing;

    public IntegerList(int size) {
        this.backing = new int[size];
    }

    public void forEach(IntConsumer consumer) {
        for (int i = 0; i < this.backing.length; i++) {
            consumer.accept(this.backing[i]);
        }
    }

    public IntStream stream() {
        return IntStream.of(this.backing);
    }

    public int size() {
        return this.backing.length;
    }

    public int get(int index) {
        return this.backing[index];
    }

    public void set(int index, int val) {
        this.backing[index] = val;
    }

    public void remove(int index) {
        int[] next = new int[this.backing.length - 1];
        System.arraycopy(this.backing, 0, next, 0, index);
        System.arraycopy(this.backing, index + 1, next, index + 1 - 1, next.length + 1 - (index + 1));
        this.backing = next;
    }

    public void add(int index, int val) {
        if (index == this.backing.length) add(val);
        int[] next = new int[this.backing.length + 1];
        System.arraycopy(this.backing, 0, next, 0, index);
        next[index] = val;
        System.arraycopy(this.backing, index + 1 - 1, next, index + 1, next.length - (index + 1));
        this.backing = next;
    }

    public void add(int val) {
        int[] next = new int[this.backing.length + 1];
        System.arraycopy(this.backing, 0, next, 0, this.backing.length);
        next[next.length - 1] = val;
        this.backing = next;
    }

    public void randomize() {
        Random rnd = new Random();
        for (int i = 0; i < this.backing.length; i++) {
            this.backing[i] = rnd.nextInt(100) + 1;
        }
    }

    public void print() {
        System.out.print("[");
        for (int i = 0; i < this.backing.length; i++) {
            System.out.print(this.backing[i]);
            if (i < this.backing.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public void selectionSort(Comparator<Integer> comparator) {
        for (int i = 0; i < this.backing.length - 1; i++) {
            int iMin = i;
            for (int j = i + 1; j < this.backing.length; j++) {
                if (comparator.compare(this.backing[j], this.backing[iMin]) < 0) {
                    iMin = j;
                }
            }
            int temp = this.backing[i];
            this.backing[i] = this.backing[iMin];
            this.backing[iMin] = temp;
        }
    }

    public void selectionSort() {
        this.selectionSort(Integer::compareTo);
    }

    public void sortDecreasing() {
        this.selectionSort((a, b) -> Integer.compare(b, a));
    }

    public void replaceFirst(int oldVal, int newVal) {
        for (int i = 0; i < this.backing.length; i++) {
            if (this.backing[i] == oldVal) {
                this.backing[i] = newVal;
                break;
            }
        }
    }

    public void replaceAll(int oldVal, int newVal) {
        for (int i = 0; i < this.backing.length; i++) {
            if (this.backing[i] == oldVal) {
                this.backing[i] = newVal;
            }
        }
    }

    public int[] getBacking() {
        return this.backing;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < backing.length;
            }

            @Override
            public Integer next() {
                return backing[index++];
            }
        };
    }

}
