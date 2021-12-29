import java.util.Arrays;

public class QuickFind {

    int[] id;

    public QuickFind(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int find(int i) {
            while (i != id[i]) i = id[i];
            return i;
        }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i < j) {
            id[j] = i;
        }
        else {
            id[i] = j;
        }
    }

    // public int find(int p) {
    //     return id[p];
    // }

    public void print() {
        System.out.println(Arrays.toString(id));
    }

    public static void main(String[] args) {
        QuickFind tree = new QuickFind(Integer.parseInt(args[0]));
        tree.print();
        tree.union(4, 2);
        tree.union(3, 2);
        tree.print();
        System.out.println(tree.find(3));
    }
}
