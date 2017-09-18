public class BoxCars {

    public static void main(String[] args) {
        PairOfDice dice = new PairOfDice(6, 6);
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            int[] parts = dice.roll();
            if (parts[0] == parts[1] && parts[0] == 6) {
                sum++;
            }
        }

        System.out.println(sum);
    }

}
