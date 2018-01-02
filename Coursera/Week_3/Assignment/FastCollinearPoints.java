/*
 * Programming Assignment 3: Pattern Recognition
 * A faster, sorting-based solution. Remarkably, it is possible to 
 * solve the problem much faster than the brute-force solution described above. 
 * Given a point p, the following method determines whether p participates in 
 * a set of 4 or more collinear points.
 */

import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private int count = 0;
    private Stack<LineSegment> lineSegmentStack = new Stack<LineSegment>();
    private Stack<Point> collinearPointsStack = new Stack<Point>();

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        int n = points.length;
        for (int i = 0; i < n; i++)
            if (points[i] == null)
                throw new IllegalArgumentException();
        Arrays.sort(points);
        for (int i = 0; i < points.length-1; i++)
            if (points[i].compareTo(points[i+1]) == 0)
                throw new IllegalArgumentException();

        for (int i = 0; i < n; i++) {
            Point origin = points[i];
            Point[] otherPoints = new Point[n-i-1];
            for (int j = i+1, k = 0; j < n; j++)
                otherPoints[k++] = points[j];
            Arrays.sort(otherPoints, origin.slopeOrder());

            for (int s = 0; s < otherPoints.length-1; s++) {
                int t = s + 1;
                while (Double.compare(origin.slopeTo(otherPoints[s]), origin.slopeTo(otherPoints[t])) == 0) {
                    t++;
                    if (t == otherPoints.length)
                        break;             
                }
                int num = t - s;
                s = t - 1;
                if (num >= 3) {
                    collinearPointsStack.push(origin);
                    while (num-- > 0)
                        collinearPointsStack.push(otherPoints[--t]);
                    Point[] extremePoints = findExtremePoints(collinearPointsStack);
                    LineSegment newLineSegment = new LineSegment(extremePoints[0], extremePoints[1]);
                    lineSegmentStack.push(newLineSegment);
                    ++count;
                }
            }
        }
    }

    private static Point[] findExtremePoints(Stack<Point> collinearPointsStack) {
        assert collinearPointsStack.size() >= 4;
        Point[] extremePoints = new Point[2];
        extremePoints[0] = collinearPointsStack.pop();
        extremePoints[1] = extremePoints[0];
        while (collinearPointsStack.size() != 0) {
            Point p = collinearPointsStack.pop();
            if (extremePoints[0].compareTo(p) < 0)
                extremePoints[0] = p;
            if (extremePoints[1].compareTo(p) > 0)
                extremePoints[1] = p;
        }
        return extremePoints;
    }
            
    public int numberOfSegments() {
        return  count;
    }

    public LineSegment[] segments() {
        int n = lineSegmentStack.size();
        LineSegment[] lineSegmentArray = new LineSegment[n];
        for (int i = 0; i < n; i++)
            lineSegmentArray[i] = lineSegmentStack.pop();
        assert lineSegmentStack.size() == 0;
        return lineSegmentArray;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}    


