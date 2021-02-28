package com.examples.reactive.Operators;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id &&
                Double.compare(employee.rating, rating) == 0 &&
                Double.compare(employee.salary, salary) == 0 &&
                name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating, salary);
    }
}
