package stream.api.task2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AggregationAndPoolingResults {

    public static void main(String[] args) {
        Map<String, Double> map = new HashMap<>();
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );
        System.out.println(students);

        for (Student s : students) {
            double number = s.getGrades().values().parallelStream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElseThrow(() -> new IllegalArgumentException("Map is empty"));
            map.put(s.getName(), number);
        }

        System.out.println("Средние оценки по всем предметам");
        map.entrySet().forEach(System.out::println);
    }
}
