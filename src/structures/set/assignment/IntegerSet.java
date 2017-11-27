package structures.set.assignment;

public interface IntegerSet extends Iterable<Integer> {

    boolean add(int a);
    boolean addAll(int... a);
    boolean addAll(IntegerSet set);

    boolean remove(int a);
    boolean removeAll(int... a);
    boolean removeAll(IntegerSet set);

    boolean contains(int a);
    boolean containsAll(int... a);
    boolean containsAll(IntegerSet set);

    int size();
    boolean isEmpty();

    IntegerSet union(IntegerSet other);

    int[] toArray();

}
