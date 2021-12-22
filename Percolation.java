import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Arrays;

import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

public class Percolation {

    private int[][] grid;
    private WeightedQuickUnionUF tree;
    private int numOpenSistes = 0;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        
        grid = new int[n][n];
        tree = new WeightedQuickUnionUF(n * n);

        for (int i=0; i < n; i++) {
            for (int j=0; j < n; j++) {
                grid[i][j] = 0;
            }
        }
        // System.out.println(Arrays.toString(grid));
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (grid[row][col] == 0) {
            grid[row][col] = 1;
            numOpenSistes++;
        }
        int n = this.grid.length;

        // cells not on the edges/corners
        if (0 < row && row < n - 1 && 0 < col && col < n - 1) {
            if (this.isOpen(row - 1, col)) {                        // upper row
                tree.union(n * row + col, n * (row - 1) + col);
            }
            else if (this.isOpen(row + 1, col)) {                   // lower row
                tree.union(n * row + col, n * (row + 1) + col);
            }
            else if (this.isOpen(row, col + 1)) {                   // rightmost col
                tree.union(n * row + col, col + 1 + n * row);
            }
            else if (this.isOpen(row, col - 1)) {                   // leftmost col
                tree.union(n * row + col, col -1 + n * row);
            }
        }
        // upper edge (without corners)
        else if (row == 0 && 0 < col && col < n - 1) {
            if (this.isOpen(row + 1, col)) {                        // lower row
                tree.union(n * row + col, n * (row + 1) + col);
            }
            else if (this.isOpen(row, col + 1)) {                   // rightmost col
                tree.union(n * row + col, (col + 1) + n * row);
            }
            else if (this.isOpen(row, col - 1)) {                   // leftmost col
                tree.union(n * row + col, col - 1 + n * row);
            }
        }
        // bottom edge (without corners)
        else if (row == n - 1 && 0 < col && col < n - 1) {
            if (this.isOpen(row - 1, col)) {                        // upper row
                tree.union(n * row + col, n * (row - 1) + col);
            }
            else if (this.isOpen(row, col + 1)) {                   // rightmost col
                tree.union(n * row + col, (col + 1) + n * row);
            }
            else if (this.isOpen(row, col - 1)) {                   // leftmost col
                tree.union(n * row + col, col - 1 + n * row);
            }
        }
        // left edge (without corners)
        else if (0 < row && row < n - 1 && col == 0) {
            if (this.isOpen(row + 1, col)) {                        // lower row
                tree.union(n * row + col, n * (row + 1) + col);
            }
            else if (this.isOpen(row - 1, col)) {                   // upper row
                tree.union(n * row + col, n * (row - 1) + col);
            }
            else if (this.isOpen(row, col + 1)) {                   // rightmost col
                tree.union(n * row + col, col + 1 + n * row);
            }
        }
        // right edge (without corners)
        else if (0 < row && row < n - 1 && col == n - 1) {
            if (this.isOpen(row + 1, col)) {                        // lower row
                tree.union(n * row + col, n * (row + 1) + col);
            }
            else if (this.isOpen(row - 1, col)) {                   // upper row
                tree.union(n * row + col, n * (row - 1) + col);
            }
            else if (this.isOpen(row, col - 1)) {                   // leftmost col
                tree.union(n * row + col, col - 1 + n * row);
            }
        }
        // corners
        // top-left
        else if (row == 0 && col == 0) {
            if (this.isOpen(row, col + 1)) {                        // rightmost col
                tree.union(n * row + col, n * row + col + 1);
            }
            else if (this.isOpen(row + 1, col)) {                   // lower row
                tree.union(n * row + col, n * (row + 1) + col);
            }
        }
        // top-right
        else if (row == 0 && col == n - 1) {
            if (this.isOpen(row, col - 1)) {                        // leftmost col
                tree.union(n * row + col, n * row + col - 1);
            }
            else if (this.isOpen(row + 1, col)) {                   // lower row
                tree.union(n * row + col, n * (row + 1) + col);
            }
        }
        // bottom-left
        else if (row == n - 1 && col == 0) {
            if (this.isOpen(row, col + 1)) {                        // rightmost col
                tree.union(n * row + col, n * row + col + 1);
            }
            else if (this.isOpen(row - 1, col)) {                   // upper row
                tree.union(n * row + col, n * (row - 1) + col);
            }
        }
        // bottom right
        else if (row == n - 1 && col == n - 1) {
            if (this.isOpen(row, col - 1)) {                        // leftmost col
                tree.union(n * row + col, n * row + col - 1);
            }
            else if (this.isOpen(row - 1, col)) {                   // upper row
                tree.union(n * row + col, n * (row - 1) + col);
            }
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
        boolean percolates = false;
        int n = this.grid.length;
        int i = 0;
        System.out.println(i);
        do {
            if (this.isFull(n - 1, i)) {
                percolates = true;
            }
            i++;
        } while (percolates == false && i < n);
        return percolates;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation perc = new Percolation(5);

        System.out.println(perc.tree.find(4));

        perc.open(0, 4);
        System.out.println(perc.tree.find(4));
        perc.open(3, 2);
        perc.open(1, 3);
        System.out.println(perc.tree.find(4));
        perc.open(1, 4);
        System.out.println(perc.tree.find(4));
        perc.open(2, 4);
        System.out.println(perc.tree.find(4));
        perc.open(3, 4);
        System.out.println(perc.tree.find(4));
        perc.open(4, 4);
        System.out.println(perc.tree.find(4));

        System.out.println(perc.numberOfOpenSites());
        System.out.println("Is (0, 4) open? " + perc.isOpen(0, 4));
        System.out.println(perc.tree.find(4));
        System.out.println("Is (0, 4) full? " + perc.isFull(0, 4));
        System.out.println("Is (3, 2) full? " + perc.isFull(3, 2));
        System.out.println("Is (0, 2) full? " + perc.isFull(0, 2));
        System.out.println("Is (1, 4) full? " + perc.isFull(0, 2));
        System.out.println("Is (4, 4) full? " + perc.isFull(4, 4));
        System.out.println("Is (3, 4) full? " + perc.isFull(3, 4));
        System.out.println("Is (2, 4) full? " + perc.isFull(2, 4));
        System.out.println("Percolates? " + perc.percolates());
        System.out.println(perc.tree.find(4));
    }
    
}