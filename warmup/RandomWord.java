import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        int i = 1;
        double p = 0.0;
        while (!StdIn.isEmpty()) {
            String value = StdIn.readString();
            p = 1.0/i;
            boolean isChampion = StdRandom.bernoulli(p);
            
            if (isChampion) {
                champion = value;
            }
            i++;
        }
        System.out.println(champion);
    }
}
