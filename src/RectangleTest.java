import java.util.stream.Stream;

public class RectangleTest {

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle(3, 4);
        Rectangle r3 = new Rectangle(5);
        Stream.of(r1, r2, r3).forEach(System.out::println);
    }

}
