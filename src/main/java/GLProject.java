
//Hooriya Kazmi
//hkazm2
//hkazm2@uic.edu
//Ammani Khan
//akhan381
//akhan381@uic.edu
//Implements custom GenericList, GenericQueue, and
// MyHashMap data structures with iterators for CS342 Project 1
import java.util.Iterator;

public class GLProject {
    public static void main(String[] args) {
        System.out.println("Welcome to project 1");

        GenericQueue<Integer> q = new GenericQueue<>(1);
        q.enqueue(2);
        q.enqueue(3);

        // quick demo (not graded — real tests are in GQTest/HMTest)
        q.print(); // should print each on its own line: 1, 2, 3

        Iterator<Integer> it = q.descendingIterator();
        while (it.hasNext()) System.out.println(it.next());
    }
}