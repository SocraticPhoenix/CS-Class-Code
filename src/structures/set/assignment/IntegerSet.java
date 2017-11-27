package structures.set.assignment;

public interface IntegerSet extends Iterable<Integer> {

    boolean add(int a);
    boolean addAll(int... a);

    boolean remove(int a);
    boolean removeAll(int... a);

    boolean contains(int a);
    boolean containsAll(int... a);

    int size();
    boolean isEmpty();

    IntegerSet union(IntegerSet other);

    int[] toArray();

}
