public class RecursiveFact {

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(factorial(i));
        }
    }

    public static int factorial(int n) {
        return n == 1 ? n : factorial(n - 1) * n;
    }

}
