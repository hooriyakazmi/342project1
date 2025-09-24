
//Hooriya Kazmi
//hkazm2
//hkazm2@uic.edu
//Implements custom GenericList, GenericQueue, and
// MyHashMap data structures with iterators for CS342 Project 1
public class GLProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println("Welcome to project 1");

        // ==== Constructor test ====
        GenericQueue<Integer> q = new GenericQueue<>(1);
        q.enqueue(2);
        q.enqueue(3);

        // ==== print() test ====
        // Expected: "1 2 3"
        System.out.println("print():");
        q.print();

        // ==== dumpList() test ====
        // Expected: [1, 2, 3]
        System.out.println("dumpList:");
        System.out.println(q.dumpList());

        // ==== get() tests ====
        System.out.println("get(0): " + q.get(0)); // Expected: 1
        System.out.println("get(2): " + q.get(2)); // Expected: 3
        System.out.println("get(5): " + q.get(5)); // Expected: null (out of bounds)

        // ==== set() tests ====
        System.out.println("set(1, 99): " + q.set(1, 99)); // Expected: old value 2
        q.print(); // Should now show 1, 99, 3
        System.out.println("set(5, 100): " + q.set(5, 100)); // Expected: null (out of bounds)

        // ==== Forward iterator test (head → tail) ====
        // Expected: 1, 99, 3
        System.out.println("forEach:");
        for (int x : q) System.out.println(x);

        // ==== Reverse iterator test (tail → head) ====
        // Expected: 3, 99, 1
        System.out.println("descending:");
        var it = q.descendingIterator();
        while (it.hasNext()) System.out.println(it.next());

        // ==== add(T, code) test ====
        // Add a new node with a code value (not directly visible here,
        // but ensures method compiles and works for HashMap collisions later)
        q.add(42, 12345);
        System.out.println("After add(data, code):");
        q.print(); // Should include 42 at the end

        // ==== delete() / dequeue() test ====
        // Removes from the tail
        System.out.println("deleting:");
        System.out.println(q.dequeue()); // Removes 42
        System.out.println(q.dequeue()); // Removes 3
        System.out.println(q.dequeue()); // Removes 99
        System.out.println(q.dequeue()); // Removes 1
        System.out.println(q.dequeue()); // null (already empty)

        // ==== print() after deletions ====
        // Expected: "Empty List"
        q.print();



    }
}
