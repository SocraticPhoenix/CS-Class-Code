package fakecontest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Problem6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            String line = scanner.nextLine();
            String[] pieces = line.split(", ");
            int k = Integer.parseInt(pieces[1]);
            List<BigInteger> possible = permutations(pieces[0]).stream().map(s -> new BigInteger(s, 36)).sorted().collect(Collectors.toList());

            BigInteger min = possible.get(0);
            BigInteger max = possible.get(possible.size() - 1);
            BigInteger fiftieth = possible.get(possible.size() - 50);
            BigInteger kth = possible.get(k);

            BigDecimal mean = new BigDecimal(min.add(max)).divide(new BigDecimal(2), 5, BigDecimal.ROUND_UNNECESSARY);
            BigDecimal minDiff = mean;
            BigInteger close = null;
            BigInteger otherClose = null;

            for (BigInteger integer : possible) {
                BigDecimal test = mean.subtract(new BigDecimal(integer)).abs();
                if (test.compareTo(minDiff) < 0) {
                    minDiff = test;
                    close = integer;
                    otherClose = null;
                } else if (test.compareTo(minDiff) == 0) {
                    otherClose = integer;
                }
            }

            System.out.println(min.toString(36));
            System.out.println(max.toString(36));
            System.out.println(fiftieth.toString(36));
            System.out.println(kth.toString(36));
            System.out.println(close.toString(36));
            if (otherClose != null) {
                System.out.println(otherClose.toString(36));
            }
        }
    }

    public static List<String> permutations(String str) {
        List<String> perms = new ArrayList<>();
        permutation("", str, perms);
        return perms;
    }

    private static void permutation(String prefix, String str, List<String> perms) {
        int n = str.length();
        if (n == 0) {
            perms.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), perms);
            }
        }
    }

}
