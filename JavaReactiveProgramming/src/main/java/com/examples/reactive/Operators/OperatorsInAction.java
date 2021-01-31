package com.examples.reactive.Operators;

import io.reactivex.rxjava3.core.Observable;

import java.util.Comparator;

public class OperatorsInAction {
    public static void main(String[] args) {
        Observable<Employee> employeeObservable = Observable.just(
                new Employee(1, "Malli", 10.0, 9500.00),
                new Employee(2, "Sakthi", 7.0, 2500.00),
                new Employee(3, "John", 5.0, 5000.00),
                new Employee(4, "Paul", 1.0, 5001.00),
                new Employee(5, "Don", 8.0, 5500.00),
                new Employee(6, "Lin", 9.0, 4500.00)
        );

        employeeObservable.filter(e -> e.getRating() > 5.0)
                .sorted(Comparator.comparing(Employee::getRating).reversed())
                .map(Employee::getName)
                .take(3)
                .subscribe(System.out::println);

        employeeObservable
                .map(Employee::getSalary)
                .reduce((x, y) -> x + y)
                .subscribe(System.out::println);

    }

}
