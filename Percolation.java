// import edu.princeton.cs.algs4.StdRandom;
// import edu.princeton.cs.algs4.StdStats;
// import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Arrays;
// import edu.princeton.cs.algs4.QuickFindUF;
// import edu.princeton.cs.algs4.QuickUnionUF;

public class Percolation {

    private int[][] grid;
    private QuickFind tree;
    private int numOpenSistes = 0;
    private boolean percolates = false;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        
        grid = new int[n][n];
        tree = new QuickFind(n * n);

        for (int i=0; i < n; i++) {
            for (int j=0; j < n; j++) {
                grid[i][j] = 0;
            }
        }
        // System.out.println(Arrays.toString(grid));
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int n = this.grid.length;

        if (!this.isOpen(row, col)) {
            grid[row][col] = 1;
            numOpenSistes++;
        }

        if ((row - 1) >= 0) {
            if (this.isOpen(row - 1, col)) {
                tree.union(n * row + col, n * (row - 1) + col);
            }
        }
        if ((row + 1) < n) {
            if (this.isOpen(row + 1, col)) {
                tree.union(n * (row + 1) + col, n * row + col);
            }
        }
        if((col - 1) >= 0) {
            if (this.isOpen(row, col - 1)) {
                tree.union(n * row + col, n * row + col - 1);
            }
        }
        if ((col + 1) < n) {
            if (this.isOpen(row, col + 1)) {
                tree.union(col + 1 + n * row, n * row + col);
            }
        }
        // System.out.println("Num open sites: " + numOpenSistes);
        
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        boolean isOpen = false;
        if (this.grid[row][col] == 1) {
            isOpen = true;
        }
        return isOpen;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int n = this.grid.length;
        boolean isFull = false;
        if (this.tree.find(n * row + col) < n) {
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
        // boolean percolates = false;
        int n = this.grid.length;
        int i = 0;
        // System.out.println(i);
        do {
            if (this.isFull(n - 1, i)) {
                percolates = true;
            }
            // this.isFull(n - 1, i);
            i++;
        } while (!percolates && i < n);
        return percolates;
    }

    public void print() {
        System.out.println(Arrays.deepToString(grid));
    }

    

    // test client (optional)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int iter = 0;
        Percolation perc = new Percolation(n);
        double fraction = 0;

        while (!perc.percolates()) {
            int rand = (int) (Math.random() * n * n );

            int row = rand / n;
            int col = rand % n;

            // System.out.println("Row: " + row + " | Col: " + col);
            
            perc.open(row, col);
            // fraction = perc.numOpenSistes / (double) (n * n);
            // System.out.println(fraction);
            perc.print();
            perc.tree.print();
            iter++;
        }
        System.out.println("Percolates after " + iter + " iterations");
        // System.out.println("Percolates");

        // System.out.println(perc.tree.find(4));

        // perc.open(0, 2);
        // System.out.println(perc.tree.find(4));
        
        // perc.open(1, 2);
        // perc.open(1, 1);
        // perc.open(1, 0);
        
        // perc.open(2, 0);

        // System.out.println(perc.numberOfOpenSites());
        // System.out.println("Is (0, 4) open? " + perc.isOpen(0, 4));
        // System.out.println(perc.tree.find(4));
        // System.out.println("Is (0, 4) full? " + perc.isFull(0, 4));
        // System.out.println("Is (3, 2) full? " + perc.isFull(3, 2));
        // System.out.println("Is (0, 2) full? " + perc.isFull(0, 2));
        // System.out.println("Is (1, 4) full? " + perc.isFull(0, 2));
        // System.out.println("Is (4, 4) full? " + perc.isFull(4, 4));
        // System.out.println("Is (3, 4) full? " + perc.isFull(3, 4));
        // System.out.println("Is (2, 4) full? " + perc.isFull(2, 4));
        // System.out.println("Percolates? " + perc.percolates());
        // System.out.println(perc.tree.find(4));
        perc.print();
    }
    
}