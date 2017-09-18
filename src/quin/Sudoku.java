package quin;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Sudoku {

    public static void main(String[] args) {
        int[][] board = new int[4][4];
        Scanner scanner = new Scanner(System.in);
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                board[row][col] = scanner.nextInt();
            }
        }

        boolean valid = true;

        for (int row = 0; row < 4; row++) {
            if (!validRun(board, row, 0, row, 3)) {
                System.out.println("Invalid row");
                valid = false;
                break;
            }
        }

        for (int col = 0; col < 4; col++) {
            if (!validRun(board, 0, col, 3, col)) {
                System.out.println("Invalid column");
                valid = false;
                break;
            }
        }

        search:
        for (int row = 0; row < 4; row += 2) {
            for (int col = 0; col < 4; col += 2) {
                if (!validRun(board, row, col, row + 1, col + 1)) {
                    System.out.println("Invalid subgrid");
                    valid = false;
                    break search;
                }
            }
        }

        if (valid) {
            System.out.println("Valid Solution");
        }

    }

    public static boolean validRun(int[][] arr, int r1, int c1, int r2, int c2) {
        Set<Integer> seen = new HashSet<>();
        for (int row = r1; row <= r2; row++) {
            for (int col = c1; col <= c2; col++) {
                int v = arr[row][col];
                if (seen.contains(v)) {
                    return false;
                }
                seen.add(v);
            }
        }

        return true;
    }

}
