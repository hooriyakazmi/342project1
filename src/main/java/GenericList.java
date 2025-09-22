
import java.util.ArrayList;
import java.util.Iterator;

public abstract class GenericList<T> implements Iterable<T> {
    // ===== fields =====
    private Node<T> head;
    private int length;

    // ===== inner node class =====
    class Node<U> {
        U data;
        int code;
        Node<U> next;
    }

    // ===== printing =====
    public void print() {
        if (head == null) {
            System.out.println("Empty List");
            return;
        }
        Node<T> curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println(); // newline at the end
    }

    // ===== abstract methods (to be filled in subclasses like GenericQueue) =====
    public abstract void add(T data);
    public abstract T delete();

    // ===== utility methods =====
    public ArrayList<T> dumpList() {
        // TODO: walk from head, collect data, return ArrayList
        return null;
    }

    public T get(int index) {
        // TODO: return value at index or null if out of bounds
        return null;
    }

    public T set(int index, T element) {
        // TODO: replace at index, return old value or null if out of bounds
        return null;
    }

    // ===== getters/setters for private fields =====
    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> h) {
        head = h;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int n) {
        length = n;
    }

    // ===== iterators =====
    @Override
    public Iterator<T> iterator() {
        // TODO: return new GLLIterator<>(head)
        return null;
    }

    public Iterator<T> descendingIterator() {
        // TODO: return new ReverseGLLIterator<>(head)
        return null;
    }
}
