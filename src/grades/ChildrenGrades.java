package grades;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ChildrenGrades {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("grades.txt"));
        lines.remove(0);
        List<Student> students = lines.stream().map(s -> s.split(" ")).map(a -> {
            String name = a[0];
            int[] grades = new int[a.length - 1];
            for (int i = 1; i < a.length; i++) {
                grades[i - 1] = Integer.parseInt(a[i]);
            }
            return new Student(name, grades);
        }).collect(Collectors.toList());

        FileWriter writer = new FileWriter("averages.txt");
        for (Student student : students) {
            writer.write(student.toString() + System.lineSeparator());
        }
        writer.close();
    }

}
