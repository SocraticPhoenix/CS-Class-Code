package structures;

import java.util.Comparator;
import java.util.Random;

public class IntegerList {
    private int[] backing;

    public IntegerList(int size) {
        this.backing = new int[size];
    }

    public void add(int val) {
        int[] next = new int[this.backing.length + 1];
        for (int i = 0; i < this.backing.length; i++) {
            next[i] = this.backing[i];
        }
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

}
