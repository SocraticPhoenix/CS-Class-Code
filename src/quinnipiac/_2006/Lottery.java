package quinnipiac._2006;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Lottery {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] drawn = Stream.of(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<int[]> vals = new ArrayList<>();
        int[] v;
        while (!allZero(v = Stream.of(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray())) {
            vals.add(v);
        }

        for (int[] val : vals) {
            int white = 0;
            for (int i = 0; i < val.length - 1; i++) {
                for (int j = 0; j < drawn.length; j++) {
                    if (val[i] == drawn[j]) {
                        white++;
                        break;
                    }
                }
            }
            
            boolean red = val[5] == drawn[5];

            int amt = 0;
            if (red || white > 2) {
                if (red) {
                    switch (white) {
                        case 0:
                            amt = 3;
                            break;
                        case 1:
                            amt = 4;
                            break;
                        case 2:
                            amt = 7;
                            break;
                        case 3:
                            amt = 100;
                            break;
                        case 4:
                            amt = 10_000;
                            break;
                        case 5:
                            amt = -1;
                            break;
                    }
                } else {
                    switch (white) {
                        case 3:
                            amt = 7;
                            break;
                        case 4:
                            amt = 100;
                            break;
                        case 5:
                            amt = 200_000;
                            break;
                    }
                }
            }

            System.out.println(amt == -1 ? "JACKPOT" : amt);
        }
    }

    private static boolean allZero(int... ints) {
        for (int k : ints) {
            if (k != 0) {
                return false;
            }
        }

        return true;
    }

}