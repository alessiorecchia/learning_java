import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.util.Arrays;

public class PercolationStats {

    double fraction;
    double[] fractions;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        Percolation g; 
        int row, col;
        fractions = new double[trials];
        for (int i = 0; i < trials; i++) {
            System.out.println("Trial no. " + (i + 1));
            g = new Percolation(n);
            int iter = 0;
            fraction = 0;
            while (!g.percolates()) {
                fraction = g.numberOfOpenSites()/(double)(n * n);
                col = randint(0, n);
                row = randint(0, n);
                g.open(row, col);
                iter++;
                // System.out.print(fraction + " ");
            }
            System.out.println("Percolates after " + iter + " iterations");
            System.out.println("Fraction: " + fraction);
            // System.out.println(Arrays.toString(fractions));
            fractions[i] = fraction;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double m = 0;
        for (int i = 0; i < fractions.length; i++) {
            m += fractions[i];
        }
        m = m/fractions.length;
        return m;
    }


    // sample standard deviation of percolation threshold
    public double stddev() {
        double sigma2 = 0;
        for (int i = 0; i < fractions.length; i++) {
            sigma2 += Math.pow(fractions[i] - this.mean(), 2);
        }
        sigma2 = sigma2/(fractions.length - 1);
        return sigma2;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double cLo = 0;
        return cLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double cHi = 0;
        return cHi;
    }

    private int randint(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

   // test client (see below)
   public static void main(String[] args) {
       int n = Integer.parseInt(args[0]);
       int trials = Integer.parseInt(args[1]);
       PercolationStats tries = new PercolationStats(n, trials);
       System.out.println("Mean: " + tries.mean());
       System.out.println("Std dev: " + tries.stddev());

   }

}
