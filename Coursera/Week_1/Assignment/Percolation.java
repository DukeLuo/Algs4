/* Percolation data type */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final int size;       // the size of the grid, which equals to the row of the grid
    private final int topRoot;
    private final int downRoot;
    private int count;            // number of open sites
    private boolean[] open;       // sign of a site, true is open and false if close
    
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        uf = new WeightedQuickUnionUF(n*n+2);
        size = n;
        topRoot = 0;
        downRoot = size * size + 1;
        count = 0;
        open = new boolean[size*size+1];
    }

    private int index(int row, int col, int n) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException();
        return n * (row - 1) + col;
    }
    
    public boolean isOpen(int row, int col) {
        return open[index(row, col, size)];
    }

    public void open(int row, int col) {
        int i = index(row, col, size);
        if (!open[i]) {
            open[i] = true;
            ++count;
            int up = i - size;
            int down = i + size;
            int left = i - 1;
            int right = i + 1;
            if ((i >= 1) && (i <= size))
                uf.union(i, topRoot);
            if ((i >= size * (size - 1) + 1) && (i <= size * size))
                uf.union(i, downRoot);
            if ((up > 0) && open[up])
                uf.union(i, up);
            if ((down <= size * size) && open[down])
                uf.union(i, down);
            if ((left > 0) && (left % size != 0) && open[left])
                uf.union(i, left);
            if ((right <= size * size) && (right % size != 1) && open[right])
                uf.union(i, right);
        }
    }
    
    public int numberOfOpenSites() {
        return count;
    }

    public boolean isFull(int row, int col) {
        int i = index(row, col, size);
        return isOpen(row, col) && uf.connected(i, 0);
    }

    public boolean percolates() {
        return uf.connected(0, size*size+1);
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);
        
        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            if (percolation.isOpen(row, col))
                StdOut.printf("(%d, %d) is open\n", row, col);
            else
                percolation.open(row, col);
            if (percolation.isFull(row, col)) {
                StdOut.printf("(%d, %d) is full\n", row, col);
                if (percolation.percolates())
                    StdOut.printf("(%d, %d): grid percolates\n", row, col);
                else
                    StdOut.printf("(%d, %d): grid not percolation\n", row, col);
            }
        }
        StdOut.println("number of open sites: " + percolation.numberOfOpenSites());
    }
}
            

