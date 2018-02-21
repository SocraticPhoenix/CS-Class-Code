package grades;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Student {
    private String name;
    private int[] grades;
    private int avg;

    public Student(String name, int[] grades) {
        this.name = name;
        this.grades = grades;
        this.avg = (int) IntStream.of(grades).summaryStatistics().getAverage();
    }

    public String getName() {
        return name;
    }

    public int[] getGrades() {
        return grades;
    }

    public int getAvg() {
        return avg;
    }

    @Override
    public String toString() {
        return name + ": (Grades: " + Arrays.toString(grades) + "), (Average: " + this.avg + ")";
    }

}
