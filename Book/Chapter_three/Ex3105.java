/*
 * implement size(), delete() and keys() 
 * of SequentialSearchST
 */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex3105<Key, Value> {
    private Node first;
    private int size;

    private class Node {
        public Key key;
        public Value val;
        public Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node n = first; n != null; n = n.next) {
            if (key.equals(n.key))
                return n.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        for (Node n = first; n != null; n = n.next) {
            if (key.equals(n.key)) {
                n.val = val;
                return ;
            }
        }
        first = new Node(key, val, first);
        size++;
    }

    public int size() {
        return size;
    }

    public void delete(Key key) {
        Node temp = first;
        Node prev = null;
        
        if (temp != null && key.equals(temp.key)) {
            first = temp.next;
            temp.next = null;
            size--;
            return ;
        }
        while (temp != null && !(key.equals(temp.key))) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null)
            return ;
        prev.next = temp.next;
        size--;
        temp.next = null;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        for (Node n = first; n != null; n = n.next) {
            q.enqueue(n.key);
        }
        return q;
    }

    public static void main(String[] args) {
        Ex3105<String, Integer> st;
        st = new Ex3105<String, Integer>();

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
                
        
