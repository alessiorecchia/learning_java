import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node<Item> node;
    private int n;

    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;
        int idx;
    }



    // construct an empty randomized queue
    public RandomizedQueue() {
        node = null;
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null) throw new IllegalArgumentException();
        Node<Item> oldHead = node;
        node = new Node<Item>();
        node.item = item;
        node.next = null;
        node.idx = n;
        if (isEmpty()) node.prev = null;
        else node.prev = oldHead;
    }

    // remove and return a random item
    public Item dequeue() {

    }

    // return a random item (but do not remove it)
    public Item sample() {

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
