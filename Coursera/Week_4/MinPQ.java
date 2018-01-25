/* 2.4 优先队列
　* 基于堆的优先队列
 * priority queue
　*/

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;       // 基于堆的完全二叉树
    private int N = 0;      // 存储于pq[1..N]中，pq[0]没有使用
    
    public MinPQ(int minN) {
        pq = (Key[]) new Comparable[minN+1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        if (N == pq.length - 1)
            resize(2*pq.length); 
        pq[++N] = v;
        swim(N);
    }

    public Key delMin() {
        if (this.isEmpty())
            throw new NoSuchElementException();
        Key min = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        if ((N > 0) && N == (pq.length - 1) / 4)
            resize(pq.length/2);
        return min;
    }

    private boolean greater(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j+1))
                j++;
            if (!greater(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    private void resize(int capacity) {
        assert capacity > N;
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= N; i++)
            temp[i] = pq[i];
        pq = temp;
    }
}


