package com;

public class FibanaciTest {

    private static int i = 0;

    public static void main(String[] args) {
        // f(5) = f(4)+f(3)
        final long fibanaci = fibanaci2(1);
        System.out.println(fibanaci);
        System.out.println(i);
    }

    public static long fibanaci(int num) {
        i++;
        if (num == 0) {
            return 0;
        }
        if (num <= 1) {
            return 1;
        }
        return fibanaci(num - 1) + fibanaci(num - 2);

    }


    public static long fibanaci2(int num) {
        int sum = 0;
        if (num <= 0) {
            return num;
        }
        for (int i = num; i > 0; i--) {
            System.out.println("i : " + i);
            sum = sum + i;
        }
        return sum;
    }
}
