package quin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Polynomial {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] coefs = Stream.of(scanner.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<String> evals = new ArrayList<>();
        String line;
        while (!(line = scanner.nextLine().trim()).equals("0")) {
            evals.add(line);
        }
        evals.add("0");

        int[] evalInts = evals.stream().mapToInt(Integer::parseInt).toArray();
        for (int evalInt : evalInts) {
            System.out.println(eval(evalInt, coefs));
        }
    }

    private static int eval(int in, int[] coefs) {
        int res = 0;
        for (int i = 0; i < coefs.length; i++) {
            int xv = 1;
            for (int j = 0; j < i; j++) {
                xv *= in;
            }
            res += xv * coefs[i];
        }
        return res;
    }

}
