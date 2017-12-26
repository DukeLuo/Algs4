/**
  * Stack with max.
  * Create a data structure that efficiently supports the stack operations (push and pop) 
  * and also a return-the-maximum operation. Assume the elements are reals numbers 
  * so that you can compare them.
  */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

public class MaxStack {
    private Node first;     //栈顶(最近添加的元素)
    private int N;          //元素数量
    private class Node {
        int item;
        Node next;
    }

    private Stack<Integer> max = new Stack<Integer>();        //存储最大值
    private int i;                                            //max中下一个元素

    private int maxNum() {
        //返回栈中的最大值
        int item = max.pop();
        max.push(item);
        return item;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return N;
    }
    
    public void push(int item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;

        if (max.size() == 0)
            max.push(item);
        else {
            int current = maxNum();
            if (item >= current) {
                max.push(item);
            }
        }
    }
    
    public int pop() {
        int item = first.item;
        first = first.next;
        N--;

        int current = maxNum();
        if (item == current)
            max.pop();
        return item;
    }

    public int max() {
        int item = max.pop();
        max.push(item);
        return item;
    }
    
    public static void main(String[] args) {
        MaxStack s = new MaxStack();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                int i = Integer.parseInt(item);
                s.push(i);
                StdOut.printf("push: %2d;   max number in the stack is %2d\n", i, s.max());
            }
            else if (!s.isEmpty())
                StdOut.printf("pop:  %2d;   max number in the stack is %2d\n", s.pop(), s.max());
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}

    
