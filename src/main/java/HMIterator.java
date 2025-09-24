import java.util.ArrayList;
import java.util.Iterator;

public class HMIterator<T> implements Iterator<T> {
    private final ArrayList<GenericQueue<T>> map;
    private int bucketIndex;
    private GenericList.Node<T> current;

    public HMIterator(ArrayList<GenericQueue<T>> map) {
        this.map = map;
        this.bucketIndex = 0;
        this.current = null;
        advanceToNext();
    }

    private void advanceToNext() {
        while (current == null && bucketIndex < map.size()) {
            GenericQueue<T> q = map.get(bucketIndex);
            current = (q == null) ? null : q.getHead();
            if (current == null) bucketIndex++;
        }
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        T val = current.data;
        current = current.next;
        if (current == null) {
            bucketIndex++;
            advanceToNext();
        }
        return val;
    }
}
