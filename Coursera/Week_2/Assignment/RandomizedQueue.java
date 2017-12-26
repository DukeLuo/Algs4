/*
 * Programming Assignment 2: Deques and Randomized Queues
 * Randomized queue. A randomized queue is similar to a stack or queue, 
 * except that the item removed is chosen uniformly at random from items
 * in the data structure.
 */

import java.util.Iterator;
import java.util.NoSuchElementException; 
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int size;

    public RandomizedQueue() {
        a = (Item[]) new Object[1];
        size = 0;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < size; i++)
            temp[i] = a[i];
        a = temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        a[size++] = item;
        if (size == a.length)
            resize(2*a.length);
    }

    public Item dequeue() {
        if (size == 0)
            throw new NoSuchElementException();
        int i = StdRandom.uniform(size);
        Item item = a[i];
        a[i] = a[size-1];       //与a末端元素交换
        a[size-1] = null;
        size--;
        if ((size > 0) && (size == a.length/4))
            resize(a.length/2);
        return item;
    }

    public Item sample() {
        if (size == 0)
            throw new NoSuchElementException();
        int i = StdRandom.uniform(size);
        return a[i];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int n;
        private Item[] b;

        public RandomizedQueueIterator() {
            n = size;
            b = (Item[]) new Object[n];
            for (int i = 0; i < n; i++)
                b[i] = a[i];
        }
        public boolean hasNext() {
            return n > 0;
        }
        public Item next() {
            if (n == 0)
                throw new NoSuchElementException();
            int i = StdRandom.uniform(n);
            Item item = b[i];       //与b末端元素交换
            b[i] = b[n-1];
            b[n-1] = item;
            n--;
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                rq.enqueue(item);
            else if (!rq.isEmpty())
                StdOut.print(rq.dequeue() + " ");
        }
        StdOut.println("(" + rq.size() + " left on randomized queue)");
        for (int i = 0; i < rq.size(); i++)
            StdOut.println("random item: " + rq.sample());
    }
}
        
 
