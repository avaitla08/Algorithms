import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
// This is coursera project 2
// This is a Dequeue and Randomized Queues

public class Deque<Item> implements Iterable<Item> {


    private Node<Item> head;
    private Node<Item> tail;

    private int n;

    private class Node<Item> {
        private final Item item;
        private Node<Item> prev;
        private Node<Item> next;

        Node(Item item, Node<Item> prev, Node<Item> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    // construct an empty deque
    public Deque() {
        n = 0;
        //Deque<Item> x;
        head = null;
        tail = null;


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
        if (item == null) {
            throw new IllegalArgumentException("");
        }
        Node<Item> newNode = new Node<>(item, null, head);
        if (isEmpty()) {
            tail = newNode;
        } else {
            head.prev = newNode;
        }
        head = newNode;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("");
        }
        Node<Item> newNode = new Node<>(item, tail, null);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;

        n++;

    }


    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("");
        }

        Item item = head.item;
        head = head.next;
        n--;
        return item;

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("");
        }
        Item i = tail.item;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        n--;
        return i;

    }

   /* public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }
    */

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        d.addFirst(9);

        d.addLast(7);

        d.removeFirst();
        d.removeLast();
        StdOut.print(d);
    }

}

