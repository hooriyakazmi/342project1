import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GQTest {

    // ===== Constructors / length tracking =====
    @Test
    public void testConstructorInitializesHeadAndLength() {
        GenericQueue<Integer> q = new GenericQueue<>(1);
        assertEquals(1, q.getLength());
        assertEquals(Integer.valueOf(1), q.get(0));
    }

    // ===== enqueue (delegates to add) =====
    @Test
    public void testEnqueueAppendsAndUpdatesLength() {
        GenericQueue<Integer> q = new GenericQueue<>(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals(3, q.getLength());
        assertEquals(Arrays.asList(1, 2, 3), q.dumpList());
    }

    // ===== add(T, code) overload =====
    @Test
    public void testAddWithCodeAppendsAtTail() {
        GenericQueue<Integer> q = new GenericQueue<>(1);
        q.enqueue(2);
        q.enqueue(3);
        q.add(42, 12345); // code stored internally
        assertEquals(4, q.getLength());
        assertEquals(Integer.valueOf(42), q.get(3));
        assertEquals(Arrays.asList(1, 2, 3, 42), q.dumpList());
    }

    // ===== dumpList returns a copy, not the internal structure =====
    @Test
    public void testDumpListReturnsCopy() {
        GenericQueue<Integer> q = new GenericQueue<>(1);
        q.enqueue(2);
        q.enqueue(3);

        ArrayList<Integer> snapshot = q.dumpList();
        assertEquals(Arrays.asList(1, 2, 3), snapshot);

        // mutate the snapshot and verify queue unchanged
        snapshot.set(0, 999);
        assertEquals(Integer.valueOf(1), q.get(0));
    }

    // ===== get (in-bounds / out-of-bounds) =====
    @Test
    public void testGetBounds() {
        GenericQueue<Integer> q = new GenericQueue<>(1);
        q.enqueue(2);
        q.enqueue(3);

        assertEquals(Integer.valueOf(1), q.get(0));
        assertEquals(Integer.valueOf(3), q.get(2));
        assertNull(q.get(5));
        assertNull(q.get(-1));
    }

    // ===== set (in-bounds) =====
    @Test
    public void testSetReplacesAndReturnsOldValue() {
        GenericQueue<Integer> q = new GenericQueue<>(1);
        q.enqueue(2);
        q.enqueue(3);

        Integer old = q.set(1, 99);
        assertEquals(Integer.valueOf(2), old);
        assertEquals(Arrays.asList(1, 99, 3), q.dumpList());
        assertEquals(3, q.getLength(), "length should not change on successful set");
    }

    // ===== set (out-of-bounds) =====
    @Test
    public void testSetOutOfBoundsReturnsNullAndNoChange() {
        GenericQueue<Integer> q = new GenericQueue<>(1);
        q.enqueue(2);

        Integer old = q.set(5, 100);
        assertNull(old);
        assertEquals(Arrays.asList(1, 2), q.dumpList());
        assertEquals(2, q.getLength());
    }

    // ===== dequeue (delegates to delete from tail) =====
    @Test
    public void testDequeueOrderAndEmptyBehavior() {
        GenericQueue<Integer> q = new GenericQueue<>(1);
        q.enqueue(2);
        q.enqueue(3);
        q.add(42, 111); // tail

        assertEquals(Integer.valueOf(42), q.dequeue());
        assertEquals(Integer.valueOf(3), q.dequeue());
        assertEquals(Integer.valueOf(2), q.dequeue());
        assertEquals(Integer.valueOf(1), q.dequeue());
        assertNull(q.dequeue());
        assertEquals(0, q.getLength());
    }

    // ===== iterator (forward: head -> tail) =====
    @Test
    public void testForwardIteratorCoversAllElementsInOrder() {
        GenericQueue<Integer> q = new GenericQueue<>(1);
        q.enqueue(2);
        q.enqueue(3);

        List<Integer> seen = new ArrayList<Integer>();
        for (int x : q) seen.add(x);
        assertEquals(Arrays.asList(1, 2, 3), seen);
    }

    // ===== descendingIterator (tail -> head) =====
    @Test
    public void testDescendingIteratorCoversAllElementsInReverse() {
        GenericQueue<Integer> q = new GenericQueue<>(1);
        q.enqueue(2);
        q.enqueue(3);

        Iterator<Integer> it = q.descendingIterator();
        List<Integer> seen = new ArrayList<Integer>();
        while (it.hasNext()) seen.add(it.next());
        assertEquals(Arrays.asList(3, 2, 1), seen);
    }

    // ===== descendingIterator edge cases =====
    @Test
    public void testDescendingIteratorOnSingleAndEmpty() {
        // single
        GenericQueue<Integer> single = new GenericQueue<>(10);
        Iterator<Integer> it1 = single.descendingIterator();
        assertTrue(it1.hasNext());
        assertEquals(Integer.valueOf(10), it1.next());
        assertFalse(it1.hasNext());

        // empty (dequeue to empty)
        GenericQueue<Integer> empty = new GenericQueue<>(5);
        empty.dequeue(); // now empty
        Iterator<Integer> it2 = empty.descendingIterator();
        assertFalse(it2.hasNext());
    }

    // ===== print(): capture System.out and assert output =====
    @Test
    public void testPrintOutputsValuesOrEmptyList() {
        // capture stdout
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        try {
            // Non-empty
            GenericQueue<Integer> q = new GenericQueue<>(1);
            q.enqueue(2);
            q.enqueue(3);
            q.print();

            String out1 = buffer.toString().replace("\r\n", "\n").trim();
            // Expect three lines: "1", "2", "3"
            assertEquals("1\n2\n3", out1);

            // Reset buffer and test empty case
            buffer.reset();
            GenericQueue<Integer> e = new GenericQueue<>(9);
            e.dequeue(); // make empty
            e.print();

            String out2 = buffer.toString().replace("\r\n", "\n").trim();
            assertEquals("Empty List", out2);

        } finally {
            // restore stdout
            System.setOut(originalOut);
        }
    }
    @Test
    public void testNodeCodeIsSetByAddWithCode() {
        GenericQueue<Integer> q = new GenericQueue<>(1);   // first node (no code)
        q.add(2, 222);
        q.add(3, 333);

        // Walk nodes and verify codes where we set them
        GenericList.Node<Integer> n = q.getHead();
        assertEquals(Integer.valueOf(1), n.data);      // first node
        n = n.next;
        assertEquals(Integer.valueOf(2), n.data);
        assertEquals(222, n.code);
        n = n.next;
        assertEquals(Integer.valueOf(3), n.data);
        assertEquals(333, n.code);
    }
    @Test
    public void testForwardIteratorOnEmpty() {
        GenericQueue<Integer> q = new GenericQueue<>(7);
        q.dequeue(); // now empty
        int count = 0;
        for (int x : q) count++;
        assertEquals(0, count);
    }


}