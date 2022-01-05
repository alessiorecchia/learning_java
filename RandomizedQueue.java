import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 8;

    private Item[] q;       // queue elements
    private int n;          // number of elements on queue
    private int first;      // index of first element of queue
    private int last;       // index of next available slot


    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("You must specify an item to enqueue");
        // double size of array if necessary and recopy to front of array
        if (n == q.length) resize(2*q.length);   // double size of array if necessary
        q[last++] = item;                        // add item
        // if (last == q.length) last = 0;          // wrap-around
        n++;

    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        // System.out.println("First: " + first + " | Last: " + last);
        int rand = StdRandom.uniform(first, last);
        Item item = q[rand];
        while (rand < last - 1) {
            q[rand] = q[rand + 1];
            rand++;
        }
        q[rand] = null;
        last--;        
        n--;
        if (rand == first) first++;
        if (first == q.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (n > 0 && n == q.length/4) {
            resize(q.length/2);
        }
        return item;        
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int rand = StdRandom.uniform(n);
        Item item = null;
        if (q[rand] != null) item = q[rand];
        else sample();
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    // move all the elements at the beginnig of the array
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = first; i < n; i++) {
            copy[i] = q[(first + i) % q.length];
        }
        q = copy;
        first = 0;
        last  = n;
    }

    // private void compact() {
    //     Item[] copy = (Item[]) new Object[n];
    //     int idx = 0;
    //     for (int i = 0; i < n; i++) {
    //         if (q[i] != null) {
    //             copy[idx] = q[i];
    //             idx++;
    //         }
    //     }
    // }

    private class RandomArrayIterator implements Iterator<Item> {
        private int i = 0;
        private final Item[] iter = shuffle(q);
        public boolean hasNext()  { return i < n;                               }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = iter[(i + first) % iter.length];
            i++;
            return item;
        }

        private Item[] shuffle(Item[] arr) {
            Item itm;
            Item[] copy = (Item[]) new Object[n];
            int randIdx;
            for (int j = 0; j < n; j++) {
                copy[j] = arr[j];
            }

            for (int k = 0; k < n; k++) {
                randIdx = StdRandom.uniform(n);
                itm = copy[k];
                copy[k] = copy[randIdx];
                copy[randIdx] = itm;
            }
            return copy;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> que = new RandomizedQueue<Integer>();
        que.enqueue(1);
        que.enqueue(3);
        que.enqueue(8);
        que.enqueue(5);
        que.enqueue(7);
        que.enqueue(0);
        que.enqueue(9);
        que.enqueue(4);
        que.enqueue(2);
        que.enqueue(6);
        // System.out.println(que.sample());
        System.out.println("########## Full Queue ##########");
        System.out.println(que.size());
        for (int i : que) {
            System.out.println(i);
        }
        System.out.println("########## Queue size ##########");
        System.out.println(que.dequeue());
        System.out.println(que.size());
        System.out.println("########## Full Queue after dequeue ##########");
        for (int i : que) {
            System.out.println(i);
        }
        System.out.println("########## Queue size ##########");
        System.out.println(que.dequeue());
        System.out.println(que.size());
        System.out.println("########## Full Queue after dequeue ##########");
        for (int i : que) {
            System.out.println(i);
        }
        System.out.println("########## Queue size ##########");
        System.out.println(que.dequeue());
        System.out.println(que.size());
        System.out.println("########## Full Queue after dequeue ##########");
        for (int i : que) {
            System.out.println(i);
        }
        System.out.println("########## Queue size ##########");
        System.out.println(que.dequeue());
        System.out.println(que.size());
        System.out.println("########## Full Queue after dequeue ##########");
        for (int i : que) {
            System.out.println(i);
        }
    }

}
