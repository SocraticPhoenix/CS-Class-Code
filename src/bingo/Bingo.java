package bingo;

import structures.set.SetImpl;

public class Bingo {

    public static void main(String[] args) {
        int NUM_BALLS = 75, NUM_PULLS = 10;

        SetImpl<BingoBall> set = new SetImpl<>();
        BingoBall ball;

        for (int i = 0; i < NUM_BALLS; i++) {
            ball = new BingoBall(i);
            set.add(ball);
        }

        System.out.println("Size: " + set.size());
        System.out.println();

        for (int i = 0; i < NUM_PULLS; i++) {
            ball = set.removeRandom();
            System.out.println(ball);
        }
    }

}
