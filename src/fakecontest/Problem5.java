package fakecontest;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Problem5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            String line = scanner.nextLine();
            int count = Integer.parseInt(line.substring(0, 1));
            line = line.substring(1);

            int previous = Integer.MIN_VALUE;
            List<Integer> arr = line.codePoints().map(c -> Character.digit(c, 10)).boxed().collect(Collectors.toList());

            while (arr.size() >= count) {
                String num = "";
                for (int j = 0; j < count; j++) {
                    int index = 0;
                    if (j == 0) {
                        index = minNonZero(arr);
                    } else {
                        index = min(arr);
                    }
                    num += arr.remove(index);
                }

                int k = Integer.parseInt(num);
                if (k > previous) {
                    previous = Integer.parseInt(num);
                    System.out.print(num + " ");
                }
            }
            System.out.println();
        }
    }

    private static int min(List<Integer> arr) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < arr.size(); i++) {
            int k = arr.get(i);
            if (k < min) {
                min = k;
                index = i;
            }
        }

        return index;
    }

    private static int minNonZero(List<Integer> arr) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < arr.size(); i++) {
            int k = arr.get(i);
            if (k < min && k != 0) {
                min = k;
                index = i;
            }
        }

        return index;
    }

}
