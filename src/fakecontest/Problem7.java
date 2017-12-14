package fakecontest;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Problem7 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pattern od = scanner.delimiter();

        scanner.useDelimiter(", ");
        Map<String, Point2D> points = new HashMap<>();
        char c = 'A';

        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            points.put(String.valueOf(c++), new Point2D.Double(a, b));
        }

        scanner.useDelimiter(od);
        scanner.nextLine();

        for (int i = 0; i < 10; i++) {

        }
    }

}
