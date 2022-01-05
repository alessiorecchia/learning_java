import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("You must specify how many items you want to print");
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> input = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String val = StdIn.readString();

            input.enqueue(val);
            // System.out.println("enqueued: " + val);
        }
        // for (String str : input) System.out.println(str);
        if (k < 0 || k > input.size()) throw new IllegalArgumentException("Size of permutation must be grater than 0 and less or equals to the number of words in the text file");
        for (int i = 0; i < k; i++) System.out.println(input.dequeue());
    }
 }
