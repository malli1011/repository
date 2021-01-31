package com.practice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Contacts {

    private static final Scanner scanner = new Scanner(System.in);
    static List<String> contacts = new ArrayList<>();
    static List<Long> counter = new ArrayList<>();

    static int[] contacts(String[][] queries) {

        for (String[] line : queries) {
            if (line[0].equals("add")) {
                contacts.add(line[1]);
            } else if (line[0].equals("find")) {
                final long count = contacts.stream().filter(contact -> contact.startsWith(line[1])).count();
                counter.add(count);
            }
        }
        return counter.stream().mapToInt(Long::intValue).toArray();
    }

    public static void main(String[] args) throws IOException {


        int queriesRows = Integer.parseInt(scanner.nextLine().trim());

        String[][] queries = new String[queriesRows][2];

        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int[] result = contacts(queries);
        Arrays.stream(result).forEach(System.out::println);

    }
}

