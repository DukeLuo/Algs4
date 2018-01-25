/*
 * priority queue
 * Time-driven simulation: N bouncing balls in the unit square
 * Ball data type
 */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Ball {
    private double rx, ry;
    private double vx, vy;
    private final double radius;

    public Ball() {
        this.rx = StdRandom.random();
        this.ry = StdRandom.random();
        this.vx = StdRandom.random();
        this.vy = StdRandom.random();
        this.radius = 0.01;
    }

    public void move(double dt) {
        if ((rx + vx*dt < radius) || (rx + vx*dt > 1.0 - radius))
            vx = -vx;
        if ((ry + vy*dt < radius) || (ry + vy*dt > 1.0 -radius))
            vy = -vy;
        rx += vx*dt;
        ry += vy*dt;
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }
}

 
