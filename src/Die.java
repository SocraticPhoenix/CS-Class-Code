import java.util.Random;

public class Die {
    private static Random rand = new Random();

    private int sides;

    public Die(int sides) {
        this.sides = sides;
    }

    public int roll() {
        return rand.nextInt(this.sides) + 1;
    }

}
