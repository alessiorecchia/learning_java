import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;       // top of deque
    private Node<Item> last;        // tail of deque
    private int n;                  // size of the deque

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.prev = null;
        if (isEmpty()) last = first;
        else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        last.next = null;
        n--;
        if (isEmpty()) first=null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }
    
    // an iterator, doesn't implement remove() since it's optional
    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current;

        public DequeIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deq = new Deque<Integer>();

        deq.addLast(3);
        deq.addFirst(1);
        deq.addLast(4);
        deq.addFirst(0);
        deq.addLast(5);
        deq.addFirst(2);
        
        System.out.println("Deq size: " + deq.size());
        System.out.println("");
        System.out.println("######## Deq elements ########");
        for (int i : deq) {
            System.out.println(i); 
        }
        System.out.println("");
        deq.removeFirst();
        deq.removeLast();

        System.out.println("Deq size: " + deq.size());
        System.out.println("");
        System.out.println("######## Deq elements ########");
        for (int i : deq) {
            System.out.println(i); 
        }

        System.out.println("");
        deq.removeLast();
        System.out.println("Deq size: " + deq.size());
        System.out.println("");
        System.out.println("######## Deq elements ########");
        for (int i : deq) {
            System.out.println(i); 
        }
        deq.removeLast();
        System.out.println("Deq size: " + deq.size());
        System.out.println("");
        System.out.println("######## Deq elements ########");
        for (int i : deq) {
            System.out.println(i); 
        }
        deq.removeLast();
        System.out.println("Deq size: " + deq.size());
        System.out.println("");
        System.out.println("######## Deq elements ########");
        for (int i : deq) {
            System.out.println(i); 
        }
        deq.removeLast();
        System.out.println("Deq size: " + deq.size());
        System.out.println("");
        System.out.println("######## Deq elements ########");
        for (int i : deq) {
            System.out.println(i); 
        }
    }

}
