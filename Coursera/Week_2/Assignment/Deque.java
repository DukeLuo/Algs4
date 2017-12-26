/*
 * Programming Assignment 2: Deques and Randomized Queues
 * Dequeue. A double-ended queue or deque (pronounced “deck”) is a generalization of 
 * a stack and a queue that supports adding and removing items from either the front 
 * or the back of the data structure.
 */

import java.util.Iterator;
import java.util.NoSuchElementException; 
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node head;
    private Node tail;
    private class Node {
        Item item;
        Node next = null;
        Node previous = null;
    }

    public Deque() {
        size = 0;
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node first = new Node();
        first.item = item;
        if (size == 0) {
            head = first;
            tail = first;
        } else {
            Node oldhead = head;
            first.next = oldhead;
            oldhead.previous = first;
            head = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node last = new Node();
        last.item = item;
        if (size == 0) {
            head = last;
            tail = last;
        } else {
            Node oldtail = tail;
            last.previous = oldtail;
            oldtail.next = last;
            tail = last;
        }
        size++;
    }

    public Item removeFirst() {
        if (size == 0)
            throw new NoSuchElementException();
        Item item = head.item;
        Node newhead = head.next;
        head.next = null;
        head.item = null;
        head = newhead;
        size--;
        return item;
    }

    public Item removeLast() {
        if (size == 0)
            throw new NoSuchElementException();
        Item item = tail.item;
        Node newtail = tail.previous;
        tail.previous = null;
        tail.item = null;
        tail = newtail;
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;
        private int n = size;

        public boolean hasNext() {
            return n != 0;
        }
        public Item next() {
            if (n == 0)
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            n--;
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<Integer>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("--") && !item.equals("-+")) {
                int i = Integer.parseInt(item);
                if (i >= 0)
                    dq.addFirst(i);
                else
                    dq.addLast(i);
            }
            else if (!dq.isEmpty())
                if (item.equals("--"))
                    StdOut.print(dq.removeLast() + " ");
                else if (item.equals("-+"))
                    StdOut.print(dq.removeFirst() + " ");
        }
        StdOut.println("(" + dq.size() + " left on deque)");
    }
}
        
        
