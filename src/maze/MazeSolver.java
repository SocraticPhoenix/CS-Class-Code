package maze;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class MazeSolver {

    public static void main(String[] args) throws Throwable {
        File mazeFile = new File("maze.txt");
        List<String> lines = Files.readAllLines(mazeFile.toPath());

        int rows = Integer.parseInt(lines.get(0));
        int cols = Integer.parseInt(lines.get(1));
        char[][] maze = new char[cols][rows];

        for (int i = 2; i < lines.size(); i++) {
            char[] row = lines.get(i).toCharArray();
            for (int j = 0; j < row.length; j++) {
                maze[i-2][j] = row[j];
            }
        }



    }

}
