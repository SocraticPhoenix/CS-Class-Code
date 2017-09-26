package quinnipiac._2006;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Maze {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        int[][] maze = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            char[] row = scanner.nextLine().toCharArray();
            for (int j = 0; j < cols; j++) {
                maze[i][j] = Character.digit(row[j], 10);
            }
        }

        List<Move> res = solve(maze);
        if (res == null) {
            System.out.println("No solution");
        } else {
            char[][] solution = new char[rows][cols];
            for (char[] sol : solution) {
                Arrays.fill(sol, '.');
            }
            solution[rows - 1][cols - 1] = '*';

            for (Move m : res) {
                if (!m.to.equals(m.from)) {
                    char c = m.to.x - m.from.x < 0 ? '<' :
                                m.to.x - m.from.x > 0 ? '>' :
                                    m.to.y - m.from.y < 0 ? '^' : 'v';
                    solution[m.from.y][m.from.x] = c;
                }
            }

            for (char[] sol : solution) {
                System.out.println(new String(sol));
            }
        }

    }

    private static int[][] offsets = new int[][] {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public static List<Move> solve(int[][] maze) {
        Stack<Move> moves = new Stack<>();
        moves.push(new Move(new Point(0, 0), new Point(0, 0)));
        while (!moves.peek().isFinal(maze)) {
            Move working = moves.peek();
            int size = moves.size();
            for (int[] offset : offsets) {
                int r = offset[0];
                int c = offset[1];
                Move prospective = new Move(working.getTo(), new Point(working.getTo().x + c, working.getTo().y + r));
                if (prospective.isPossible(maze) && moves.stream().noneMatch(m -> m.from.equals(prospective.to))) {
                    if (prospective.isValid(maze)) {
                        moves.push(prospective);
                        break;
                    } else {
                        maze[prospective.getTo().y][prospective.getTo().x] = -1;
                        while (!moves.isEmpty() && !moves.peek().isValid(maze)) {
                            Move prev = moves.pop();
                            maze[prev.getTo().y][prev.getTo().x] = -1;
                        }
                    }
                }
            }

            if (size == moves.size()) {
                return null;
            }

        }

        List<Move> moveList = new ArrayList<>();
        while (!moves.isEmpty()) {
            moveList.add(0, moves.pop());
        }

        return moveList;
    }

    static class Move {
        public Point from;
        public Point to;

        public Move(Point from, Point to) {
            this.from = from;
            this.to = to;
        }

        public Point getFrom() {
            return this.from;
        }

        public Point getTo() {
            return this.to;
        }

        public int getFromVal(int[][] maze) {
            return maze[from.y][from.x];
        }

        public int getToVal(int[][] maze) {
            return maze[to.y][to.x];
        }

        public boolean isPossible(int[][] maze) {
            return to.y >= 0 && to.x >= 0 && maze.length > to.y && maze[to.y].length > to.x;
        }

        public boolean isValid(int[][] maze) {
            return isPossible(maze) && Math.abs(getToVal(maze) - getFromVal(maze)) <= 1 && getToVal(maze) >= 0;
        }

        public boolean isFinal(int[][] maze) {
            return maze.length - 1 == to.y && maze[to.y].length - 1 == to.x;
        }

        public String toString() {
            return "(" + from.x + ", " + from.y + ") -> " + "(" + to.x + ", " + to.y + ")";
        }

    }

}
