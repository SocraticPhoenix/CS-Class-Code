import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class WarehouseTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("warehouse count> ");
        int count = Integer.parseInt(scanner.nextLine());

        List<Warehouse> warehouses = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            System.out.print("x" + (i + 1) + "> ");
            int x = Integer.parseInt(scanner.nextLine());
            System.out.print("y" + (i + 1) + "> ");
            int y = Integer.parseInt(scanner.nextLine());

            warehouses.add(new Warehouse(x, y, i + 1));
        }

        System.out.print("client x> ");
        int cx = Integer.parseInt(scanner.nextLine());
        System.out.print("client y> ");
        int cy = Integer.parseInt(scanner.nextLine());

        Warehouse closest = warehouses.stream().sorted(Comparator.comparingDouble(x -> distance(cx, cy, x))).findFirst().get();
        DecimalFormat format = new DecimalFormat("0.00");
        System.out.print("The closest warehouse is warehouse " + closest.getNumber() + " and the distance is " + format.format(distance(cx, cy, closest)));
    }

    public static double distance(double x, double y, Warehouse warehouse) {
        return Math.sqrt(Math.pow(x - warehouse.getX(), 2) + Math.pow(y - warehouse.getY(), 2));
    }

}
