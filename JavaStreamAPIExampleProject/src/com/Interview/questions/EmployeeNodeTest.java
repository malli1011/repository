package com.Interview.questions;

import java.sql.SQLOutput;
import java.util.List;

public class EmployeeNodeTest {

    public static void main(String[] args) {

        EmployeeNode emp1 = new EmployeeNode(2l, "Name1", null);
        EmployeeNode emp3 = new EmployeeNode(4l, "Name3", null);
        EmployeeNode emp2 = new EmployeeNode(3l, "Name2", List.of(emp3));

        EmployeeNode empRoot = new EmployeeNode(1l, "Name", List.of(emp1, emp2));

        //empRoot.getAllSubOrdinates().stream().map(EmployeeNode::getName).forEach(System.out::println);

        //empRoot.getAllSubOrdinatesStream().stream().map(EmployeeNode::getName).forEach(System.out::println);

        //emp3.flattened().map(EmployeeNode::getName).forEach(System.out::println);
        empRoot.getAllSubOrdinatesStream().map(EmployeeNode::getName).forEach(System.out::println);
    }

}
