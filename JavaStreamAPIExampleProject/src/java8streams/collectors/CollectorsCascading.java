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

public class CollectorsCascading {
    public static void main(String[] args) {

        Path path = Paths.get("D:/Malli/EmployeeData.txt");
        try (final Stream<String> lines = Files.lines(path)) {
            final Stream<String> words = lines.flatMap(line -> Arrays.stream(line.split(",")));
            final Spliterator<String> baseSpliterator = words.spliterator();
            Spliterator<Employee> employeeSpliterator = new EmployeeSpliterator(baseSpliterator);

            final Stream<Employee> employeeStream = StreamSupport.stream(employeeSpliterator, false);
            final List<Employee> employeeList = employeeStream.collect(Collectors.toList());
            //joining
            final String employeeNamesString = employeeList.stream().map(Employee::getName)
                    .collect(Collectors.joining(","));
            System.out.println(employeeNamesString);

            //cascading collectors
            final Map<String, Long> countByDesignation = employeeList.stream()
                    .collect(Collectors.groupingBy(Employee::getDesignation, Collectors.counting()));

            System.out.println(countByDesignation);

            final Map<String, Double> fundDistribution = employeeList
                    .stream()
                    .collect(
                            Collectors.groupingBy(Employee::getDesignation,
                                    Collectors.summingDouble(Employee::getSalary))
                    );
            System.out.println(fundDistribution);

            final Map<String, Optional<Employee>> maxSalaryEmployees = employeeList.stream()
                    .collect(
                            Collectors.groupingBy(
                                    Employee::getDesignation,
                                    Collectors.maxBy(Comparator.comparing(Employee::getSalary))
                            ));


            final Map<String, Optional<Double>> maxSalaries = employeeList.stream()
                    .collect(
                            Collectors.groupingBy(
                                    Employee::getDesignation,
                                    Collectors.mapping(Employee::getSalary,
                                            Collectors.maxBy(Comparator.comparing(Function.identity())))
                            ));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
