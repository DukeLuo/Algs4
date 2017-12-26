/* PercolationStats */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] result;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;
    
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        else {
            result = new double[trials];
            for (int i = 0; i < trials; i++) {
                Percolation perc = new Percolation(n);
                while (!perc.percolates())
                    perc.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1));
                result[i] = perc.numberOfOpenSites() / (n * n * 1.0);
            }
            mean = StdStats.mean(result);
            stddev = StdStats.stddev(result);
            confidenceLo = mean - 1.96 * stddev / Math.sqrt(trials);
            confidenceHi = mean + 1.96 * stddev / Math.sqrt(trials);
            }
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percS = new PercolationStats(n, t);
        StdOut.printf("mean                    = %.16f\n", percS.mean());
        StdOut.printf("stddev                  = %.16f\n", percS.stddev());
        StdOut.printf("95%% confidence interval = [%.16f, %.16f]\n", percS.confidenceLo(), percS.confidenceHi());
    }
}
                
        
