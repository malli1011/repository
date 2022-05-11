package com.Interview.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EmployeeNode {

    private Long id;
    private String name;
    List<EmployeeNode> subOrdinates;

    public EmployeeNode(Long id, String name, List<EmployeeNode> subOrdinates) {
        this.id = id;
        this.name = name;
        this.subOrdinates = subOrdinates;
    }

    public Stream<EmployeeNode> flattened() {
        return Stream.concat(Stream.of(this),
                subOrdinates
                        .stream()
                        .flatMap(employeeNode -> employeeNode.getSubOrdinates() != null ? employeeNode.flattened() : Stream.of(employeeNode)));
    }

    public List<EmployeeNode> getAllSubOrdinates() {
        List<EmployeeNode> list = new ArrayList<>();
        list.add(this);
        if (this.subOrdinates != null) {
            for (EmployeeNode e : this.subOrdinates) {
                list.addAll(e.getAllSubOrdinates());
            }
        }
        return list;
    }

    public Stream<EmployeeNode> getAllSubOrdinatesStream() {
        if (subOrdinates != null) {
            return Stream.concat(Stream.of(this), this.subOrdinates.stream().flatMap(EmployeeNode::getAllSubOrdinatesStream));
        }
        return Stream.of(this);
    }

    public String getName() {
        return name;
    }

    public List<EmployeeNode> getSubOrdinates() {
        return subOrdinates;
    }
}
