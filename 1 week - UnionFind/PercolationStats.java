import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] fractions;
    private final int T;
    private static final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        Percolation g; 
        int row, col;
        double fraction;
        T = trials;
        fractions = new double[trials];
        for (int i = 0; i < trials; i++) {
            g = new Percolation(n);
            int iter = 0;
            fraction = 0;
            while (!g.percolates()) {
                col = randint(1, n + 1);
                row = randint(1, n + 1);
                g.open(row, col);
                iter = iter + 1;
            }
            fraction = g.numberOfOpenSites()/(double) (n * n);
            fractions[i] = fraction;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }


    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double cLo = this.mean() - CONFIDENCE_95 * Math.sqrt(this.stddev()/Math.sqrt((double) this.T));
        return cLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double cHi = this.mean() + CONFIDENCE_95 * Math.sqrt(this.stddev()/Math.sqrt((double) this.T));
        return cHi;
    }

    private int randint(int min, int max) {
        return (int) ((StdRandom.uniform() * (max - min)) + min);
    }

   // test client (see below)
   public static void main(String[] args) {
       int n = Integer.parseInt(args[0]);
       int trials = Integer.parseInt(args[1]);
       PercolationStats tries = new PercolationStats(n, trials);
       System.out.println("mean                     = " + tries.mean());
       System.out.println("stddev                   = " + tries.stddev());
       System.out.println("95% confidence interval  = [" + tries.confidenceLo() + ", " + tries.confidenceHi() + "]");

   }

}
