package structures.set.assignment;

import java.util.Iterator;

public class IntegerIterator implements Iterator<Integer> {
    private int index;
    private int[] content;

    public IntegerIterator(int[] content) {
        this.index = 0;
        this.content = content;
    }

    @Override
    public boolean hasNext() {
        return index < content.length;
    }

    @Override
    public Integer next() {
        return content[index++];
    }

}
