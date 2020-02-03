package com.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamsExample {
	public static void main(String... strings) {
		StreamsExample s = new StreamsExample();
		List<Employee> employees = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			employees.add(s.new Employee("name" + i, i, i * 5000.0, i % 2 > 0 ? "Male" : "Female"));
		}

		// employees.stream().forEach(System.out::println);
		List<Employee> newList = employees.stream().filter(i -> i.sal > 25000d).collect(Collectors.toList());
		Map<String, Set<Employee>> groupmap = employees.stream()
				.collect(Collectors.groupingBy(e -> e.sex, Collectors.toSet()));

		List<Employee> list = groupmap.entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase("male"))
				.flatMap(e -> e.getValue().stream()).filter(e -> e.id > 5).collect(Collectors.toList());

		newList.forEach(System.out::println);
		System.out.print(list);
	}

	class Employee {
		String name;

		@Override
		public String toString() {
			return "Employee [name=" + name + ", id=" + id + ", sal=" + sal + ", sex=" + sex + "]";
		}

		Integer id;
		Double sal;
		String sex;

		public Employee(String name, Integer id, Double sal, String sex) {
			super();
			this.name = name;
			this.id = id;
			this.sal = sal;
			this.sex = sex;
		}

		public Employee() {

		}
	}
}
