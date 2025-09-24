import java.util.ArrayList;
import java.util.Iterator;

// Reverse iterator using a simple buffer (no pointer reversal)
public class ReverseGLLIterator<T> implements Iterator<T> {
    private final ArrayList<T> buf = new ArrayList<>();
    private int i;

    public ReverseGLLIterator(GenericList.Node<T> head) {
        for (GenericList.Node<T> c = head; c != null; c = c.next) {
            buf.add(c.data);
        }
        i = buf.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return i >= 0;
    }

    @Override
    public T next() {
        return buf.get(i--);
    }
}
