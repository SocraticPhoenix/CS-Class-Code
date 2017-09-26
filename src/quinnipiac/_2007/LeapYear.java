package quinnipiac._2007;

import java.util.Scanner;

public class LeapYear {

    public static void main(String[] args) {
        int k = new Scanner(System.in).nextInt();
        if (k % 400 == 0 || k % 4 == 0) {
            System.out.println(k + " is a leap year");
        } else {
            System.out.println(k + " is not a leap year");
        }
    }

}
