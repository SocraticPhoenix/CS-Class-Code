package quinnipiac._2007;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Newspaper {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int av = scanner.nextInt(), st = scanner.nextInt();
        scanner.nextLine();
        int[][] orders = new int[av][st];
        for (int avn = 0; avn < av; avn++) {
            for (int stn = 0; stn < st; stn++) {
                orders[avn][stn] = scanner.nextInt();
            }
            scanner.nextLine();
        }

        String initial = "";
        int i;
        for (i = 0; i < av; i++) {
            initial += "S";
        }
        for (int j = i; j < st + i; j++) {
            initial += "E";
        }

        Set<String> perms = new HashSet<>();
        permutations("", st - 1, av - 1, perms);

        String target = perms.stream().sorted((d1, d2) -> Integer.compare(sum(orders, d2), sum(orders, d1))).findFirst().get();
        int s = sum(orders, target);
        System.out.println("Best route: " + target + "; " + s + " papers sold.");
    }

    public static int sum(int[][] map, String directions) {
        int sum = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < directions.length(); i++) {
            if (y >= map.length || x >= map[y].length) {
                return -1;
            }

            sum += map[y][x];
            if (directions.charAt(i) == 'S') {
                y++;
            } else {
                x++;
            }
        }

        sum += map[y][x];
        return sum;
    }

    public static void permutations(String prefix, int st, int av, Set<String> perms) {
        if (st + av == 0) {
            perms.add(prefix);
        } else {
            if (st != 0) {
                permutations(prefix + "E", st - 1, av, perms);
            }

            if (av != 0) {
                permutations(prefix + "S", st, av - 1, perms);
            }
        }
    }

}
