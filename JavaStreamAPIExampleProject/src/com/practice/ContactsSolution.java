package com.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContactsSolution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Node head = new Node();
        for (int i = 0; i < n; i++) {
            String lines = sc.nextLine();
            String[] commands = lines.split(" ");
            if (commands[0].equals("add")) {
                head.add(commands[1]);
            } else {
                int answer = head.getCount(commands[1]);
                System.out.println(answer);
            }
        }
    }

    static class Node {
        Map<Character, Node> map = new HashMap<>();
        int count = 0;

        public void add(String contact) {
            add(contact, 0);
        }

        private void add(String contact, int index) {
            count++;
            if (index == contact.length()) {
                return;
            }
            Node child = map.get(contact.charAt(index));
            if (child == null) {
                child = new Node();
                map.put(contact.charAt(index), child);
            }
            child.add(contact, index + 1);
        }

        public int getCount(String partial) {
            return getCount(partial, 0);
        }

        private int getCount(String partial, int index) {
            if (index == partial.length()) {
                return count;
            }
            Node child = map.get(partial.charAt(index));
            if (child == null) {
                return 0;
            }
            return child.getCount(partial, index + 1);
        }
    }
}