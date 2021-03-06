import java.lang.reflect.Field;

public class HackingStrings {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String str = "Hello world!";
        String k = new String(str);

        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);
        field.set(str, "Goodbye world!".toCharArray());

        System.out.println(str);
        System.out.println("Hello world!");
        System.out.println(k);
    }

}
