/*
 * implement delete() and floor()
 * of BinarySearchST
 */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex3116<Key extends Comparable<Key>, Value> {
    private static final int INT_CAPACITY = 2;
    private int size;
    private Key[] keys;
    private Value[] vals;

    public Ex3116() {
        this(INT_CAPACITY);
    }

    public Ex3116(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
        size = 0;
    }

    public int rank(Key key) {
        int lo = 0;
        int hi = size -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp > 0) {
                lo = mid + 1;
            } else if (cmp < 0) {
                hi = mid -1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if (contains(key)) {
            vals[i] = val;
            return ;
        }
        if (size == keys.length)
            resize(2*keys.length);
        for (int j = size; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        size++;
    }

    public Value get(Key key) {
        if (isEmpty() || !contains(key))
            return null;
        int i = rank(key);
        return vals[i];
    }

    public void delete(Key key) {
        if (isEmpty() || !contains(key))
            return ;
        int i = rank(key);
        for (int j = i; j < size-1; j++) {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        size--;
        keys[size] = null;
        vals[size] = null;
        if (size > 0 && size < keys.length/4) {
            resize(keys.length/2);
        }
    }

    public boolean contains(Key key) {
        int i = rank(key);
        return i < size && keys[i].compareTo(key) == 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[size-1];
    }

    public Key floor(Key key) {
        int i = rank(key);
        if (i == 0)
            return null;
        return (keys[i].compareTo(key) == 0) ? keys[i] : keys[i-1];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == size)
            return null;
        return keys[i];
    }

    public Key select(int k) {
        return keys[k];
    }

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0)
            return 0;
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    private void resize(int capacity) {
        assert capacity >= size;
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        keys = tempk;
        vals = tempv;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi)) {
            q.enqueue(hi);
        }
        return q;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public static void main(String[] args) {
        Ex3116<String, Integer> st;
        st = new Ex3116<String, Integer>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        StdOut.println("size: " + st.size());
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }

        for (String s : st.keys()) {
            if (StdRandom.random() > 0.5) {
                st.delete(s);
            }
        }
        StdOut.println("size: " + st.size());
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}

    
