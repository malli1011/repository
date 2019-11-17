package com.demo1;

import java.util.Optional;
import java.util.stream.Collectors;

public class OptionalClass {
	public static void main(String[] args) {
		String[] str = new String[10];
		Optional<String> check = Optional.ofNullable(str[5]);
		if (check.isPresent()) {
			String lowercaseString = str[5].toLowerCase();
			System.out.print(lowercaseString);
		} else {
			System.out.println("Empty str[5]");
		}
		
		str[2] = "hi";
		check = Optional.of(str[2]);
		System.out.println(check.stream().collect(Collectors.toList()));
	}
}
