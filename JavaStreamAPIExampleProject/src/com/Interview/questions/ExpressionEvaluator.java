package com.Interview.questions;

import java.util.*;
import java.util.stream.Collectors;

public class ExpressionEvaluator {
    Map<Character, Integer> map = Map.of('+', 1, '-', 1, '*', 2, '/', 2);

    public void evaluateExpression(String expression) {
        List<Character> expressionList = expression.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        Stack<Character> stack = new Stack<>();
        List<Character> postFixList = new ArrayList<>();
        for (char c : expressionList) {
            if (Character.isDigit(c)) {
                postFixList.add(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (stack.peek() != '(') {
                    postFixList.add(stack.pop());
                }
                stack.pop();
            } else if (map.containsKey(c)) {
                if (stack.empty() || stack.peek() == '(') {
                    stack.push(c);
                } else if (map.get(c) != null && map.get(c) <= map.get(stack.peek())) {
                    postFixList.add(stack.pop());
                    stack.push(c);
                } else {
                    stack.push(c);
                }
            }
        }
        while (!stack.isEmpty()) postFixList.add(stack.pop());

        String postFixExpression = postFixList.stream().map(String::valueOf).collect(Collectors.joining());
        System.out.println(postFixExpression);
        calculateExpression(postFixList);
    }

    private void calculateExpression(List<Character> expression) {
        Stack<Integer> stack = new Stack<>();
        for (char c : expression) {
            if (Character.isDigit(c)) {
                stack.push((Integer.parseInt(String.valueOf(c))));
            } else {
                int oper1 = stack.pop();
                int oper2 = stack.pop();
                int val = calc(oper1, oper2, c);
                stack.push(val);

            }
        }
        System.out.println(stack.peek());
    }

    private int calc(int i, int j, char c) {
        switch (c) {
            case '*':
                return i * j;
            case '+':
                return i + j;
            case '-':
                return j - i;
            case '/':
                return j / i;
            default:
                return 0;
        }
    }

    private boolean validateExpression(String expression) {
        Stack<Character> stack = new Stack<>();
        char[] chars = expression.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                stack.push(')');
            } else if (c == ')') {
                if (stack.isEmpty() || c != stack.peek()) {
                    return false;

                } else {
                    stack.pop();
                }
            }
        }
        if (stack.isEmpty())
            return true;
        else
            return false;

    }

    public static void main(String[] args) {
        new ExpressionEvaluator().evaluateExpression("3 + 8 * ((4 + 3) * 2 + 1) - 6 / (2 + 1)");
        System.out.println(new ExpressionEvaluator().validateExpression("(())(())"));
    }
}
