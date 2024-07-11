import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> tail;

    private int n;

    private class Node<Item> {
        private Item item;
        private Node<Item> next;

    }

    public RandomizedQueue() {
        n = 0;
        head = null;
        tail = null;

    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if(item == null) {
            throw new IllegalArgumentException("");
        }
        Node<Item> oldlast = tail;
        tail = new Node<Item>();
        tail.item = item;
        tail.next = null;
        if (isEmpty()) head = tail;
        else oldlast.next = tail;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("");
        }
        int x = StdRandom.uniformInt(n+1);
        Node<Item> c = head;
        if (!isEmpty()) {
            for (int i = 0; i < x-1; i++) {
                c = c.next;
            }
            if (c.next != null) {

                c.next = c.next.next;
            }
            n--;

        }
        return head.item;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("");
        }
        int x = StdRandom.uniformInt(n);
        Node<Item> current = head;
        for (int i = 0; i < x; i++) {
            current = current.next;
        }

        return current.item;

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private final Item[] items;
        private  int current;

        public ListIterator() {
            items = (Item[]) new Object[n];
            Node<Item> currentNode = head;
            for (int i = 0; i < n; i++) {
                items[i] = currentNode.item;
                currentNode = currentNode.next;
            }
            StdRandom.shuffle(items);
            current = 0;
        }


        public boolean hasNext() {
            return current < items.length;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        r.enqueue(1);


        StdOut.println("Sample: " + r.sample());
        StdOut.println("Old: " + r.toString());
        r.dequeue();

        StdOut.println("New: "+ r.toString());
    }

}



