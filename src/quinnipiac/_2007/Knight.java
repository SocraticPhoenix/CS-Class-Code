package quinnipiac._2007;

public class Knight {

    public static void main(String[] args) {
        for (int l = 0; l < 8; l++) {
            for (int n = 0; n < 8; n++) {
                int[][] pts = new int[][] {
                        {-2, -1},
                        {-2, 1},
                        {-1, -2},
                        {-1, 2},
                        {1, -2},
                        {1, 2},
                        {2, -1},
                        {2, 1}
                };

                System.out.print((char) (l + 'a') + "" + (n + 1) + ": ");

                for (int[] pt : pts) {
                    printMove(l, n, pt[0], pt[1]);
                }

                System.out.println();
            }
        }
    }

    public static void printMove(int x1, int y1, int x2, int y2) {
        int xv = x1 + x2;
        int yv = y1 + y2;
        if (xv >= 0 && xv <= 7 && yv >= 0 && yv <= 6) {
            System.out.print((char) (xv + 'a') + "" + (yv + 1) + " ");
        }
    }

}
