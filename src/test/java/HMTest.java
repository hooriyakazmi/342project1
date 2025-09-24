import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HMTest {

    @Test
    public void testConstructorAndPut() {
        MyHashMap<Integer> map = new MyHashMap<>("key1", 10);
        assertTrue(map.contains("key1"));
        assertEquals(1, map.size());
    }

    @Test
    public void testGetAndReplace() {
        MyHashMap<Integer> map = new MyHashMap<>("k", 5);
        map.put("another", 7);
        assertEquals(5, map.get("k"));
        assertNull(map.get("missing"));

        Integer old = map.replace("another", 99);
        assertEquals(7, old);
        assertEquals(99, map.get("another"));
    }

    @Test
    public void testIsEmpty() {
        MyHashMap<String> map = new MyHashMap<>("a", "first");
        assertFalse(map.isEmpty());
    }

    @Test
    public void testIterator() {
        MyHashMap<Integer> map = new MyHashMap<>("k1", 1);
        map.put("k2", 2);
        map.put("k3", 3);

        int sum = 0;
        for (int v : map) sum += v;
        assertEquals(6, sum);
    }

    // ===== Extra coverage tests =====

    @Test
    public void testCollisionsIncreaseSize() {
        MyHashMap<Integer> map = new MyHashMap<>("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        assertEquals(3, map.size()); // all inserts counted
        assertTrue(map.contains("a"));
        assertTrue(map.contains("b"));
        assertTrue(map.contains("c"));
    }

    @Test
    public void testReplaceMissingDoesNotChangeSize() {
        MyHashMap<String> map = new MyHashMap<>("x", "one");
        int before = map.size();
        assertNull(map.replace("nope", "two")); // missing key
        assertEquals(before, map.size());
        assertEquals("one", map.get("x"));
    }

    @Test
    public void testGetUnderMultipleInserts() {
        MyHashMap<Integer> map = new MyHashMap<>("k1", 10);
        map.put("k2", 20);
        map.put("k3", 30);
        assertEquals(10, map.get("k1"));
        assertEquals(20, map.get("k2"));
        assertEquals(30, map.get("k3"));
    }

    @Test
    public void testIteratorCoversAllValues() {
        MyHashMap<Integer> map = new MyHashMap<>("p", 5);
        map.put("q", 7);
        map.put("r", 9);
        int count = 0, sum = 0;
        for (int v : map) { count++; sum += v; }
        assertEquals(3, count);
        assertEquals(21, sum);
    }

    @Test
    public void testReplaceUpdatesValue() {
        MyHashMap<String> map = new MyHashMap<>("id1", "A");
        map.put("id2", "B");
        String old = map.replace("id1", "Z");
        assertEquals("A", old);
        assertEquals("Z", map.get("id1"));
        assertEquals("B", map.get("id2"));
    }
}
