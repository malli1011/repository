package com.practice;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class BalancedBrackets {


    // Complete the isBalanced function below.
    static String isBalanced(String s) {

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else {
                if (stack.isEmpty() || c != stack.peek()) {
                    return "NO";

                } else {
                    stack.pop();
                }
            }
        }
        if (stack.isEmpty())
            return "YES";
        else
            return "NO";

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


    }

}
