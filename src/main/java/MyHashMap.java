import java.util.ArrayList;
import java.util.Iterator;

public class MyHashMap<T> implements Iterable<T> {
    private final ArrayList<GenericQueue<T>> map; // 10 buckets
    private int totalSize = 0;

    // Per PDF: map has 10 buckets; constructor seeds first entry.
    public MyHashMap(String key, T value) {
        map = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) map.add(null);
        put(key, value);
    }

    public void put(String key, T value) {
        int code = key.hashCode();
        int idx = code & 9;                 // follow spec's example (mask 0b1001)
        GenericQueue<T> bucket = map.get(idx);

        if (bucket == null) {
            // create bucket seeded with first node
            bucket = new GenericQueue<>(value);
            // stamp the code onto the head node
            GenericList.Node<T> h = bucket.getHead();
            if (h != null) h.code = code;
            map.set(idx, bucket);
        } else {
            // collision chain using queue with (data, code)
            bucket.add(value, code);
        }
        totalSize++;
    }

    public boolean contains(String key) {
        int code = key.hashCode();
        int idx = code & 9;
        GenericQueue<T> bucket = map.get(idx);
        if (bucket == null) return false;

        GenericList.Node<T> n = bucket.getHead();
        while (n != null) {
            if (n.code == code) return true;
            n = n.next;
        }
        return false;
    }

    public T get(String key) {
        int code = key.hashCode();
        int idx = code & 9;
        GenericQueue<T> bucket = map.get(idx);
        if (bucket == null) return null;

        GenericList.Node<T> n = bucket.getHead();
        while (n != null) {
            if (n.code == code) return n.data;
            n = n.next;
        }
        return null;
    }

    public T replace(String key, T value) {
        int code = key.hashCode();
        int idx = code & 9;
        GenericQueue<T> bucket = map.get(idx);
        if (bucket == null) return null;

        GenericList.Node<T> n = bucket.getHead();
        while (n != null) {
            if (n.code == code) {
                T old = n.data;
                n.data = value;
                return old;
            }
            n = n.next;
        }
        return null;
    }

    public int size() { return totalSize; }

    public boolean isEmpty() { return totalSize == 0; }

    @Override
    public Iterator<T> iterator() {
        return new HMIterator<>(map);
    }
}
