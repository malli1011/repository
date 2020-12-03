package java8streams.collectors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CollectorsInAction {
    public static void main(String[] args) {
        Path path = Paths.get("D:/Malli/EmployeeData.txt");
        try (final Stream<String> lines = Files.lines(path)) {
            final Stream<String> words = lines.flatMap(line -> Arrays.stream(line.split(",")));
            final Spliterator<String> baseSpliterator = words.spliterator();
            Spliterator<Employee> employeeSpliterator = new EmployeeSpliterator(baseSpliterator);

            final Stream<Employee> employeeStream = StreamSupport.stream(employeeSpliterator, false);
            final List<Employee> employeeList = employeeStream.collect(Collectors.toList());

            System.out.println("-------X------LIST-------X------");
            employeeList.stream().forEach(System.out::println);
            System.out.println("-------X------END-------X------");

            final List<String> employeeNames = employeeList.stream().map(Employee::getName).collect(Collectors.toUnmodifiableList());
            final TreeSet<Employee> sortedEmployees = employeeList.stream().collect(Collectors.toCollection(TreeSet::new));

            System.out.println("--X------Collection(TreeSet)-------X--");
            sortedEmployees.stream().forEach(System.out::println);
            System.out.println("-------X------END-------X------");

            employeeList.stream().collect(
                    Collectors.toMap(Employee::getId, Employee::getName, (e1, e2) -> e1)
            );
            System.out.println("-------X------Map-------X------");
            sortedEmployees.stream().forEach(System.out::println);
            System.out.println("-------X------END-------X------");

            //split the stream into two partitions based on condition.
            final Map<Boolean, List<Employee>> partitionedData = employeeList.stream().
                    collect(
                            Collectors.partitioningBy(e -> e.getGender() == 'M')
                    );

            final List<Employee> maleEmployees = partitionedData.get(true);
            final List<Employee> femaleEmployees = partitionedData.get(false);
            System.out.println("Male Employees: " + maleEmployees);
            System.out.println("Female Employees: " + femaleEmployees);

            //GroupBy
            final Map<String, List<Employee>> groupByData = employeeList.stream()
                    .collect(Collectors.groupingBy(Employee::getDesignation));




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
