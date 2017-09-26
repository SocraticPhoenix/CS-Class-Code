package quinnipiac._2007;

import java.util.Scanner;

public class Cryptograms {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String key = scanner.nextLine();
        String text;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWYXZ";

        while (!(text = scanner.nextLine()).isEmpty()) {
            for (char c : text.toCharArray()) {
                int ind = alphabet.indexOf(Character.toUpperCase(c));
                if (ind == -1) {
                    System.out.print(c);
                } else {
                    char v = key.charAt(ind);
                    System.out.print(Character.isUpperCase(c) ? Character.toUpperCase(v) : Character.toLowerCase(v));
                }
            }
        }
    }

}
