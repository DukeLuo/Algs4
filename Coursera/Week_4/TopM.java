/* 
 * 2.4 优先队列
 * 从N个输入中找到最大的M个元素
　*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Transaction;

public class TopM { 
    public static void main(String[] args) {
    int M = Integer.parseInt(args[0]);
    MinPQ<Transaction> pq = new MinPQ<Transaction>(M+1);
    while (StdIn.hasNextLine()) {
        pq.insert(new Transaction(StdIn.readLine()));
        if (pq.size() > M)
            pq.delMin();
    }
    Stack<Transaction> s = new Stack<Transaction>();
    while (!pq.isEmpty())
        s.push(pq.delMin());
    for (Transaction t : s)
        StdOut.println(t);
    }
}


