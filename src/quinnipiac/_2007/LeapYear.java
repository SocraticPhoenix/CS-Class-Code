package quinnipiac._2007;

import java.util.Calendar;
import java.util.Scanner;

public class LeapYear {

    public static void main(String[] args) {
        int k = new Scanner(System.in).nextInt();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, k);
        if (cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365) {
            System.out.println(k + " is a leap year");
        } else {
            System.out.println(k + " is not a leap year");
        }
    }

}
