public class GenericQueue<T> extends GenericList<T> {
    private GenericList.Node<T> tail;

    public GenericQueue(T firstValue) {
        GenericList.Node<T> n = new GenericList.Node<>(firstValue);
        setHead(n);
        tail = n;
        setLength(1);
    }

    @Override
    public void add(T data) {
        GenericList.Node<T> n = new GenericList.Node<>(data);
        if (getLength() == 0) {
            setHead(n);
            tail = n;
            setLength(1);
            return;
        }
        tail.next = n;
        tail = n;
        setLength(getLength() + 1);
    }

    public void add(T data, int code) {
        GenericList.Node<T> n = new GenericList.Node<>(data, code);
        if (getLength() == 0) {
            setHead(n);
            tail = n;
            setLength(1);
            return;
        }
        tail.next = n;
        tail = n;
        setLength(getLength() + 1);
    }

    @Override
    public T delete() {
        if (getLength() == 0) return null;

        if (getLength() == 1) {
            T val = getHead().data;
            setHead(null);
            tail = null;
            setLength(0);
            return val;
        }

        GenericList.Node<T> prev = getHead();
        while (prev.next != tail) {
            prev = prev.next;
        }
        T val = tail.data;
        prev.next = null;
        tail = prev;
        setLength(getLength() - 1);
        return val;
    }

    public void enqueue(T data) { add(data); }
    public T dequeue() { return delete(); }
}
