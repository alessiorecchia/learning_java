import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        
        int[][] grid = new int[n][n];
        for (int i=0; i < n; i++) {
            for (int j=0; j < n; j++) {
                grid[i][j] = 0;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        ;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        boolean isOpen = true;
        return isOpen;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        boolean isFull = true;
        return isFull;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int numOpenSistes = 0;
        return numOpenSistes;
    }

    // does the system percolate?
    public boolean percolates() {
        boolean percolates = false;
        return percolates;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation perc = new Percolation(5);
        System.out.println(perc);
    }
    
}