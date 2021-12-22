import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class test {
    public static void main(String[] args) {
        WeightedQuickUnionUF tree = new WeightedQuickUnionUF(24);
        tree.union(5, 8);
        System.out.println(tree.find(4));
    }
    
}
