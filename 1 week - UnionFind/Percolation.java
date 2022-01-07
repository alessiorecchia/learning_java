import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int numOpenSistes = 0;
    private final int N;
    private WeightedQuickUnionUF tree;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        if (n <= 0) throw new IllegalArgumentException();
        N = n;
        
        grid = new boolean[n][n];
        tree = new WeightedQuickUnionUF(n * n + 2);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        
        if (row < 1) throw new IllegalArgumentException();
        else if (row > N) throw new IllegalArgumentException();
        else if (col < 1) throw new IllegalArgumentException();
        else if (col > N) throw new IllegalArgumentException();

        int ro = row - 1;
        int co = col - 1;

        if (!this.isOpen(row, col)) {
            grid[ro][co] = true;
            numOpenSistes++;

            if (ro - 1 < 0) tree.union(0, this.map2D1D(ro, co));
            if (ro + 1 == N) tree.union(N * N + 1, this.map2D1D(ro, co));
            
            if (ro - 1 >= 0) {
                if (this.isOpen(row - 1, col)) {
                    tree.union(this.map2D1D(ro, co), this.map2D1D(ro - 1, co));
                }
            }            

            if ((ro + 1) < N) {
                if (this.isOpen(row + 1, col)) {
                    tree.union(this.map2D1D(ro, co), this.map2D1D(ro + 1, co));
                }
            }

            if ((co - 1) >= 0) {
                if (this.isOpen(row, col - 1)) {
                    tree.union(this.map2D1D(ro, co), this.map2D1D(ro, co - 1));
                }
            }
            
            if ((co + 1) < N) {
                if (this.isOpen(row, col + 1)) {
                    tree.union(this.map2D1D(ro, co), this.map2D1D(ro, co + 1));
                }
            }
        }
        
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        
        if (row < 1) throw new IllegalArgumentException();
        else if (row > N) throw new IllegalArgumentException();
        else if (col < 1) throw new IllegalArgumentException();
        else if (col > N) throw new IllegalArgumentException();

        row--;
        col--;

        boolean isOpen = false;
        if (this.grid[row][col]) {
            isOpen = true;
        }
        return isOpen;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        
        if (row < 1) throw new IllegalArgumentException();
        else if (row > N) throw new IllegalArgumentException();
        else if (col < 1) throw new IllegalArgumentException();
        else if (col > N) throw new IllegalArgumentException();
        int ro = row - 1;
        int co = col - 1;

        return tree.find(0) == tree.find(this.map2D1D(ro, co));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numOpenSistes;
    }

    // does the system percolate?
    public boolean percolates() {
        return tree.find(0) == tree.find(N * N + 1);
    }

    private int map2D1D(int row, int col) {
        int idx = N * row + col + 1;
        return idx;
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Percolation perc = new Percolation(n);

        for (int i = 1; i < n; i++) {
            perc.open(i, 4);
        }
        perc.open(2, 2);

        System.out.println("Is (1, 4) full? " + perc.isFull(1, 4));
        System.out.println("Is (1, 4) open? " + perc.isOpen(1, 4));
        System.out.println("Is (2, 2) full? " + perc.isFull(2, 2));
        System.out.println("Is (2, 4) open? " + perc.isOpen(2, 2));
        System.out.println("Is (4, 4) full? " + perc.isFull(4, 4));
        System.out.println("Is (4, 4) open? " + perc.isOpen(4, 4));
        System.out.println("Is (3, 4) full? " + perc.isFull(3, 4));
        System.out.println("Is (3, 4) open? " + perc.isOpen(3, 4));
        System.out.println("Is (2, 4) full? " + perc.isFull(2, 4));
        System.out.println("Is (2, 4) open? " + perc.isOpen(2, 4));
    }
    
}