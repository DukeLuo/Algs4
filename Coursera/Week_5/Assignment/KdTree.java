/*
 * 2d-tree implementation
 * Write a mutable data type KdTree.java that uses 
 * a 2d-tree to implement the same API
 */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
    private static final int V = 0;     // vertical
    private static final int H = 1;     // horizontal

    private final RectHV plane;
    private Node root;
    private int count;
    private Point2D pnst;
    private double dmin;

    private static class Node {
        private final Point2D p;
        private final RectHV rect;
        private final int dth;
        private Node lb;
        private Node rt;
        

        public Node(Point2D p, RectHV rect, int dth) {
            this.p = p;
            this.rect = rect;
            this.dth = dth;
        }
    }

    public KdTree() {
        count = 0;
        plane = new RectHV(0, 0, 1, 1);
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        root = insert(root, p, plane, 0);
    }

    private Node insert(Node x, Point2D p, RectHV rect, int dth) {
        if (x == null) {
            count++;
            return new Node(p, rect, dth);
        }
        if (x.p.equals(p))
            return x;
        int d = x.dth;
        int vorh = (d % 2 == 0) ? V : H;
        int cmp = compare(p, x.p, vorh);
        RectHV childrect = childRect(x, vorh, cmp);
        if (cmp < 0)
            x.lb = insert(x.lb, p, childrect, d++);
        else if (cmp > 0)
            x.rt = insert(x.rt, p, childrect, d++);
        else
            x.rt = insert(x.rt, p, childrect, d);
        return x;
    }

    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        return contains(root, p);
    }

    private boolean contains(Node x, Point2D p) {
        if (x == null)
            return false;
        if (x.p.equals(p))
            return true;
        int vorh = (x.dth % 2 == 0) ? V : H;
        int cmp = compare(p, x.p, vorh);
        if (cmp < 0)
            return contains(x.lb, p);
        else
            return contains(x.rt, p);
    }

    public void draw() {
        draw(root);
    }

    private void draw(Node x) {
        if (x == null)
            return ;
        StdDraw.setPenRadius(0.01);
        draw(x.lb);
        int vorh = (x.dth % 2 == 0) ? V : H;
        if (vorh == V) {
            StdDraw.setPenColor(StdDraw.RED);
            x.p.draw();
            StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
        }
        else if (vorh == H) {
            StdDraw.setPenColor(StdDraw.BLUE);
            x.p.draw();
            StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
        }
        draw(x.rt);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();
        Queue<Point2D> q = new Queue<Point2D>();
        range(root, rect, q);
        return q;
    }

    private void range(Node x, RectHV rect, Queue<Point2D> q) {
        if (x == null || !x.rect.intersects(rect))
            return ;        
        int vorh = (x.dth % 2 == 0) ? V : H;
        boolean lb = (vorh == V && rect.xmin() < x.p.x()) ||
                       (vorh == H && rect.ymin() < x.p.y());
        boolean rt = (vorh == V && rect.xmax() >= x.p.x()) ||
                        (vorh == H && rect.ymax() >= x.p.y());
        if (lb)
            range(x.lb, rect, q);
        if (rect.contains(x.p))
            q.enqueue(x.p);
        if (rt)
            range(x.rt, rect, q);
    }

    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        pnst = null;
        dmin = Double.POSITIVE_INFINITY;
        nearest(root, p);
        return pnst;
    }

    private void nearest(Node x, Point2D p) {
        if (x == null || x.rect.distanceSquaredTo(p) >= dmin)
            return ;
        if (x.p.distanceSquaredTo(p) < dmin) {
            pnst = x.p;
            dmin = x.p.distanceSquaredTo(p);
        }
        int vorh = (x.dth % 2 == 0) ? V : H;
        int cmp = compare(p, x.p, vorh);
        if (cmp < 0) {
            nearest(x.lb, p);
            nearest(x.rt, p);
        }
        else {
            nearest(x.rt, p);
            nearest(x.lb, p);
        }
    }

    private int compare(Point2D p, Point2D q, int vorh) {
        if (vorh == V) {
            if (p.x() > q.x())
                return +1;
            if (p.x() < q.x())
                return -1;
        } else {
            if (p.y() > q.y())
                return +1;
            if (p.y() < q.y())
                return -1;
        }
        return 0;
    }

    private RectHV childRect(Node x, int vorh, int cmp) {
        RectHV rect = x.rect;
        if (cmp == -1) {
            if (x.lb != null)
                return x.lb.rect;
            else if (vorh == V)
                rect = new RectHV(x.rect.xmin(), x.rect.ymin(), x.p.x(), x.rect.ymax());
            else if (vorh == H)
                rect = new RectHV(x.rect.xmin(), x.rect.ymin(), x.rect.xmax(), x.p.y());
        }
        else if (cmp == +1) {
            if (x.rt != null)
                return x.rt.rect;
            else if (vorh == V)
                rect = new RectHV(x.p.x(), x.rect.ymin(), x.rect.xmax(), x.rect.ymax());
            else if (vorh == H)
                rect = new RectHV(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.rect.ymax());
        }
        return rect;
    }

    public static void main(String[] args) {
        KdTree s = new KdTree();
        double x, y;
        while (!StdIn.isEmpty()) {
            x = StdIn.readDouble();
            y = StdIn.readDouble();
            StdOut.println("x: " + x +", y: " + y);
            s.insert(new Point2D(x, y));
        }
        s.draw();
        StdOut.println("size: " + s.size());
        StdOut.println("nearest point to (0, 0): " + s.nearest(new Point2D(0, 0)));
    }
}


