import java.util.Iterator;

// Forward iterator over singly linked list
public class GLLIterator<T> implements Iterator<T> {
    private GenericList.Node<T> curr;

    public GLLIterator(GenericList.Node<T> head) {
        this.curr = head;
    }

    @Override
    public boolean hasNext() {
        return curr != null;
    }

    @Override
    public T next() {
        T val = curr.data;
        curr = curr.next;
        return val;
    }
}
