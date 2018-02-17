/* 
 * Brute-force implementation
 * Write a mutable data type PointSET.java that represents 
 * a set of points in the unit square
 */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PointSET {
    private final SET<Point2D> s;

    public PointSET() {
        s = new SET<Point2D>();
    }

    public int size() {
        return s.size();
    }

    public boolean isEmpty() {
        return s.isEmpty();
    }

    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        s.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        return s.contains(p);
    }

    public void draw() {
        for (Point2D p : s)
            p.draw();
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();
        Queue<Point2D> q = new Queue<Point2D>();
        for (Point2D p : s)
            if (rect.contains(p))
                q.enqueue(p);
        return q;
    }

    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        if (s.isEmpty())
            return null;
        Point2D n = s.max();
        double d = n.distanceSquaredTo(p);
        for (Point2D i : s)
            if (i.distanceSquaredTo(p) < d) {
                n = i;
                d = i.distanceSquaredTo(p);
            }
        return n;
    }

    public static void main(String[] args) {
        PointSET s = new PointSET();
        double x, y;
        while (!StdIn.isEmpty()) {
            x = StdIn.readDouble();
            y = StdIn.readDouble();
            s.insert(new Point2D(x, y));
        }
        s.draw();
        StdOut.println("size: " + s.size());
        StdOut.println("nearest point to (0, 0): " + s.nearest(new Point2D(0, 0)));
    }
}


