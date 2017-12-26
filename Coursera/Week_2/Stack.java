/* 1.3 背包、队和栈 */
/**
  * Stack 
  * 链表实现
  */

import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stack<Item> implements Iterable<Item> {
    private Node first;     //栈顶(最近添加的元素)
    private int N;          //元素数量
    private class Node {
        Item item;
        Node next;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return N;
    }
    
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    
    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    
    public Iterator<Item> iterator() {
        return new StackIterator();
    }
    
    private class StackIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
        
        public void remove() {
        }
    }
    
    public static void main(String[] args) {
        Stack<String> s = new Stack<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty())
                StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}

    
