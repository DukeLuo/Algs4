/*
 * Programming Assignment 4: 8 Puzzle
 * Solver data type.
 */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private final boolean isSolvable;
    private final SearchNode goal;

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int manhattan;
        private final int moves;
        private final int priority;
        private final SearchNode previous;
        private final int isTwin;        //　标志位，标志是否是初始值的twins

        public SearchNode(Board bd, int twinBit) {
            this.board = bd;
            this.manhattan = bd.manhattan();
            this.moves = 0;
            this.priority = this.moves + this.manhattan;
            this.previous = null;
            this.isTwin = twinBit;
        }

        public SearchNode(Board bd, SearchNode pre) {
            this.board = bd;
            this.manhattan = bd.manhattan();
            this.moves = pre.moves + 1;
            this.priority = this.moves + this.manhattan;
            this.previous = pre;
            this.isTwin = pre.isTwin;
        }     

        public Board getBoard() {
            return this.board;
        }

        public SearchNode getPreNode() {
            return this.previous;
        }

        public int getMoves() {
            return this.moves;
        }

        public boolean isTwin() {
            return this.isTwin == 1;
        }

        public int compareTo(SearchNode that) {
            if (this.priority < that.priority)
                return -1;
            if (this.priority > that.priority)
                return +1;
            if (this.manhattan < that.manhattan)
                return -1;
            if (this.manhattan > that.manhattan)
                return +1;
            return 0;
        }
    }

    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException();

        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        pq.insert(new SearchNode(initial, 0));
        pq.insert(new SearchNode(initial.twin(), 1));
        SearchNode currentNd = pq.min();        // 初始化       
        while (!pq.isEmpty()) {
            currentNd = pq.delMin();
            if (currentNd.getBoard().isGoal())
                break;
            for (Board bd: currentNd.getBoard().neighbors()) {
                SearchNode preNd = currentNd.getPreNode();
                if (preNd == null || !bd.equals(preNd.getBoard()))
                    pq.insert(new SearchNode(bd, currentNd));
            }
        }

        goal = currentNd;
        isSolvable = !goal.isTwin();
    }

    public boolean isSolvable() {
        return this.isSolvable;
    }

    public int moves() {
        if (!this.isSolvable())
            return -1;
        return goal.getMoves();
    }

    public Iterable<Board> solution() {
        if (!this.isSolvable())
            return null;
        Stack<Board> comeFrom = new Stack<Board>();
        SearchNode nd = goal;
        while (nd.getPreNode() != null) {
            comeFrom.push(nd.getBoard());
            nd = nd.getPreNode();
        }
        comeFrom.push(nd.getBoard());
        return comeFrom;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}

       
