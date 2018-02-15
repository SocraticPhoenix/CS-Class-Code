public class RecursionSum {

    public static void main(String[] args) {
        for (int i = 0; i <= 5; i++) {
            System.out.println(sum(i));
        }
    }

    public static int sum(int n) {
        return sum(n, 0);
    }

    public static int sum(int n, int k) {
        return n == k ? k : sum(n, k + 1) + k;
    }

}
