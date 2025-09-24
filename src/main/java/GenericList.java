
import java.util.ArrayList;
import java.util.Iterator;

public abstract class GenericList<T> implements Iterable<T> {
    // ===== fields =====
    private Node<T> head;
    private int length;

    // ===== inner node class =====
    public static class Node<U> {
        public U data;
        public int code;
        public Node<U> next;
        public Node(U data) { this.data = data; }
        public Node(U data, int code) { this.data = data; this.code = code; }
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
        ArrayList<T> out = new ArrayList<>();
        Node<T> curr = head;
        while (curr != null) {
            out.add(curr.data);
            curr = curr.next;
        }
        return out;
    }

    public T get(int index) {
        if (index < 0 || index >= length) return null;
        Node<T> curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;
        return curr.data;
    }

    public T set(int index, T element) {
        if (index < 0 || index >= length) return null;
        Node<T> curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;
        T old = curr.data;
        curr.data = element;
        return old;
    }

    // ===== getters/setters for private fields =====
    public Node<T> getHead() { return head; }
    public void setHead(Node<T> h) { head = h; }
    public int getLength() { return length; }
    public void setLength(int n) { length = n; }


    // ===== iterators =====
    @Override
    public Iterator<T> iterator() {
        return new GLLIterator<>(head);
    }

    public Iterator<T> descendingIterator() {
        return new ReverseGLLIterator<>(head);
    }
}
