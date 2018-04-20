/*
 * Binary Search Tree
 * Sample Table
 *
 * Implement the put()、get() without using recursion
 * Update n of each node still a problem!!!
 */

public class Ex3213<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private int n;
        private Node left;
        private Node right;

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }

    public Ex3213() {
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                return x.val;
            } else if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (root == null) {
            root = new Node(key, val, 1);
            return ;
        }

        Node x = root;
        Node prev = null;
        while (x != null) {
            prev = x;
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                x.val = val;
                return ;
            } else if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        x = new Node(key, val, 1);
        if (key.compareTo(prev.key) < 0) {
            prev.left = x;
        } else {
            prev.right = x;
        }
        return ;
    }
}
