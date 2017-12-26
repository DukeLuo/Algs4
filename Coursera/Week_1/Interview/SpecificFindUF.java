/* Union-find with specific canonical element　*/
/* 因为存在一个find()方法，故将要求实现方法更名为max() */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SpecificFindUF {
    private int[] id;
    private int[] sz;       // 各个根节点所对应的分量的大小
    private int[] max;
    private int count;
    
    public SpecificFindUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
        sz = new int[N];
        for (int i = 0; i < N; i++)
            sz[i] = 1;
        max = new int[N];
        for (int i = 0; i < N; i++)
            max[i] = i;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    public int max(int p) {
        int i = find(p);
        return max[i];
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j)
            return ;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        if (max[i] < max[j])
            max[i] = max[j];
        else
            max[j] = max[i];
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        SpecificFindUF uf = new SpecificFindUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            StdOut.printf("largest element in %d: %d\n", p, uf.max(p));
            StdOut.printf("largest element in %d: %d\n", q, uf.max(q));
            if (uf.connected(p, q))
                continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}


