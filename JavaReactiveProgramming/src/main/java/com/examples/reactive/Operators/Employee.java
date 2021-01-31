package com.examples.reactive.Operators;

public class Employee {
    int id;
    String name;
    double rating, salary;

    public Employee(int id, String name, double rating, double salary) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public double getSalary() {
        return salary;
    }
}
