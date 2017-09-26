package quinnipiac._2007;

import java.util.Scanner;

public class Football {

    public static void main(String[] args) {
        System.out.println(new Scanner(System.in).nextLine().codePoints().map(u -> u == 'S' ? 2 : u == 'F' ? 3 : u == 'T' ? 6 : u == '+' ? 1 : u == '*' ? 2 : 0).sum());
    }

}
