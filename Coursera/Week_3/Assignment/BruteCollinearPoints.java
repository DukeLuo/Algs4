/* 
 * Programming Assignment 3: Pattern Recognition
 * Write a program BruteCollinearPoints.java that examines 4 points at a time 
 * and checks whether they all lie on the same line segment, returning all such 
 * line segments. To check whether the 4 points p, q, r, and s are collinear, 
 * check whether the three slopes between p and q, between p and r, and between p 
 * and s are all equal. 
 */

import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private int count = 0;
    private Point[] pointsArray;
    private Stack<LineSegment> lineSegmentStack = new Stack<LineSegment>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        int n = points.length;
        for (int i = 0; i < n; i++)
            if (points[i] == null)
                throw new IllegalArgumentException();
        pointsArray = new Point[n];
        for (int i = 0; i < n; i++)
            pointsArray[i] = points[i];
        Arrays.sort(pointsArray);
        for (int i = 0; i < n-1; i++)
            if (pointsArray[i].compareTo(pointsArray[i+1]) == 0)
                throw new IllegalArgumentException();

        for (int i = 0; i < n-3; i++)
            for (int j = i+1; j < n-2; j++) {
                double k1 = pointsArray[i].slopeTo(pointsArray[j]);
                for (int k = j+1; k < n-1; k++) {
                    double k2 = pointsArray[j].slopeTo(pointsArray[k]);
                    if (Double.compare(k1, k2) == 0)
                        for (int l = k+1; l < n; l++) {
                            double k3 = pointsArray[k].slopeTo(pointsArray[l]);                
                            if (Double.compare(k2, k3) == 0) {
                                Point largestPoint = largestPointOfFourPoints(pointsArray[i], pointsArray[j], pointsArray[k], pointsArray[l]);
                                Point smallestPoint = smallestPointOfFourPoints(pointsArray[i], pointsArray[j], pointsArray[k], pointsArray[l]);
                                lineSegmentStack.push(new LineSegment(smallestPoint, largestPoint));
                                ++count;
                            }
                        }
                    }
            }
    }


    private Point largestPointOfFourPoints(Point p, Point q, Point r, Point s) {
        Point larger1 = (p.compareTo(q) == 1) ? p : q;
        Point larger2 = (r.compareTo(s) == 1) ? r : s;
        return (larger1.compareTo(larger2) == 1) ? larger1 : larger2;
    }

    private Point smallestPointOfFourPoints(Point p, Point q, Point r, Point s) {
        Point smaller1 = (p.compareTo(q) == -1) ? p : q;
        Point smaller2 = (r.compareTo(s) == -1) ? r : s;
        return (smaller1.compareTo(smaller2) == -1) ? smaller1 : smaller2;
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
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
    }
}
    

