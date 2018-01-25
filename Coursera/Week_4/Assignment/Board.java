/*
 * Programming Assignment 4: 8 Puzzle
 * Board data type.
 */

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private final int[] board1D;
    private final int dimen;

    public Board(int[][] blocks) {
        int row = blocks.length;
        int col = blocks[0].length;
        int max = row * col - 1;
        board1D = new int[row*col];
        dimen = row;

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                if (blocks[i][j] < 0 || blocks[i][j] > max)
                    throw new IllegalArgumentException();
        
        int n = 0;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                board1D[n++] = blocks[i][j];
    }

    public int dimension() {
        return dimen;
    }

    public int hamming() {
        int c = 0;
        for (int i = 0; i < board1D.length; i++)
            if (board1D[i] != i+1 && board1D[i] != 0)
                c++;
        return c;
    }

    public int manhattan() {
        int distance = 0;
        int hd, vd;

        for (int i = 0; i < board1D.length; i++)
            if (board1D[i] != i+1 && board1D[i] != 0) {
                hd = Math.abs((board1D[i]-1) % dimen - i % dimen);
                vd = Math.abs((board1D[i]-1) / dimen - i / dimen);
                distance += (hd + vd);
            }
        return distance;
    }

    public boolean isGoal() {
        return this.hamming() == 0;
    }

    public Board twin() {
        if (board1D[0] != 0 && board1D[1] != 0)
            return exchBlock(this.board1D, this.dimen, 0, 1);
        else
            return exchBlock(this.board1D, this.dimen, dimen*dimen-2, dimen*dimen-1);
    }

    public boolean equals(Object y) {
        if (y == this)
            return true;
        if (y == null)
            return false;
        if (y.getClass() != this.getClass())
            return false;
        Board that = (Board) y;

        if (that.board1D.length != this.board1D.length)
            return false;
        for (int i = 0; i < that.board1D.length; i++)
            if (that.board1D[i] != this.board1D[i])
                return false;
        return true;
    }

    public Iterable<Board> neighbors() {
        Stack<Board> s = new Stack<Board>();
        int zeroPos = -1;

        for (int i = 0; i < board1D.length; i++)
            if (board1D[i] == 0) {
                zeroPos = i;
                break;
            }
        int[] nblocks = neighborBlocks(zeroPos);
        for (int i = 0; i < nblocks.length; i++)
            s.push(exchBlock(this.board1D, this.dimen, zeroPos, nblocks[i]));
        return s;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimen + "\n");
        for (int i = 0; i < board1D.length; i++) {
            s.append(String.format("%2d ", board1D[i]));
            if (i % dimen == dimen-1)
                s.append("\n");
        }
        return s.toString();
    }

    private Board exchBlock(int[] bd, int d, int i, int j) {
        int[] backup = new int[bd.length];
        for (int k = 0; k < bd.length; k++)
            backup[k] = bd[k];
        int temp = backup[i];
        backup[i] = backup[j];
        backup[j] = temp;

        int k = 0;
        int[][] blocks = new int[d][d];
        for (int x = 0; x < d; x++)
            for (int y = 0; y < d; y++)
                blocks[x][y] = backup[k++];
        return new Board(blocks);
    }

    private int[] neighborBlocks(int zeroPos) {
        Stack<Integer> s = new Stack<Integer>();
        int up = zeroPos - dimen;
        int dw = zeroPos + dimen;
        int lf = zeroPos - 1;
        int rh = zeroPos + 1;

        if (up >= 0)
            s.push(up);
        if (dw < board1D.length)
            s.push(dw);
        if (lf >= 0 && (lf % dimen != dimen-1))
            s.push(lf);
        if (rh < board1D.length && (rh % dimen != 0))
            s.push(rh);

        int i = 0;
        int[] a = new int[s.size()];
        while (!s.isEmpty())
            a[i++] = s.pop();
        return a;
    }

    public static void main(String[] args) {
        int[][] test = {{2, 0, 3, 4}, {1, 10, 6, 8}, {5, 9, 7, 12}, {13, 14, 11, 15}};

        Board board1D = new Board(test);
        Board twin = board1D.twin();
        StdOut.println("initial:");
        StdOut.println(board1D);
        if (!board1D.equals(twin) && !twin.isGoal()) {
            StdOut.println("dimension: " + board1D.dimension());
            StdOut.println("hamming value: " + board1D.hamming());
            StdOut.println("manhattan value: " + board1D.manhattan());
            StdOut.println("neighbors:");
            for (Board b : board1D.neighbors())
                StdOut.println(b);
        }
    }
}


