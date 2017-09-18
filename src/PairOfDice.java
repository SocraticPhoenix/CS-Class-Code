public class PairOfDice {
    private Die a;
    private Die b;

    public PairOfDice(int sideA, int sideB) {
        this.a = new Die(sideA);
        this.b = new Die(sideB);
    }

    public int[] roll() {
        return new int[] {this.a.roll(), this.b.roll()};
    }

}
