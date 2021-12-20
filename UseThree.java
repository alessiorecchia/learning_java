public class UseThree {

    public static void main(String[] args) {
        // System.out.print("Hi, ");
        // System.out.print(args[0]);
        // System.out.println(". How are you?");
        int len = args.length - 1;
        System.out.print("Hi ");
        for (int i = 0; i < len; i++) {
            System.out.print(args[i]);
            System.out.print(", ");
        }
        System.out.print("and ");
        System.out.print(args[len]);
        System.out.println(".");
    }

}
