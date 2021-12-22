import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Arrays;

public class Percolation {

    private int[][] grid;
    private int[] tree;
    private int numOpenSistes = 0;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        
        grid = new int[n][n];
        tree = new int[n * n];

        for (int i = 0; i < tree.length; i++) {
            tree[i] = i;
        }

        for (int i=0; i < n; i++) {
            for (int j=0; j < n; j++) {
                grid[i][j] = 0;
            }
        }
        // System.out.println(Arrays.toString(grid));
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (this.grid[row][col] == 0) {
            this.grid[row][col] = 1;
            this.numOpenSistes++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        boolean isOpen;
        if (this.grid[row][col] == 1) {
            isOpen = true;
        }
        else {
            isOpen = false;
        }
        return isOpen;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        boolean isFull = false;
        if ((row == 0) && (this.isOpen(row, col))) {
            isFull = true;
        }
        return isFull;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numOpenSistes;
    }

    // does the system percolate?
    public boolean percolates() {
        boolean percolates = false;
        return percolates;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation perc = new Percolation(5);
        perc.open(0, 4);
        perc.open(3, 2);
        perc.open(1, 3);

        System.out.println(perc.numberOfOpenSites());
        System.out.println(perc.isOpen(0, 4));
        System.out.println(perc.isFull(0, 4));
        System.out.println(perc.isFull(3, 2));
        System.out.println(perc.isFull(0, 2));

    }
    
}