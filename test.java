import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.Math;

public class test {
    public static void main(String[] args) {
        WeightedQuickUnionUF tree = new WeightedQuickUnionUF(24);
        tree.union(5, 8);
        System.out.println(tree.find(8));

    //     int m;
    //     for (int i = 0; i < 1000; i++) {
    //         m = (int)  ((Math.random() * (9 - 0)) + 0);
    //         System.out.print(m + " ");
    //     }
    }
    
}

/*
public int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
}
*/

