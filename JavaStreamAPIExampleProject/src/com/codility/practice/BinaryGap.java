package com.codility.practice;

public class BinaryGap {

    public static void main(String ... args) {
        System.out.println(new BinaryGap().solution(124653));
    }

    public int solution(int n) {
        int first1Index = 0;
        for (int i = 0; i < 32; i++) {
            if (getNthBit(n, i) == 1) {
                first1Index = i;
                break;
            }
        }
        //From now every 1 is a begging of the end of Binary Gap.
        int currentGap = 0;
        int biggestGap = 0;
        for (int i = first1Index; i < 32; i++) {
            if (getNthBit(n, i) == 0) {
                currentGap++;
            } else {
                //If its a 1 then that is the end of the most recent binary gap.
                //We check if its biggest gap so far
                if (currentGap > biggestGap) {
                    biggestGap = currentGap;
                }
                currentGap = 0;
            }
        }
        return biggestGap;
    }


    private int getNthBit(int theNumber, int bitPosition) {
        int mask = 1;
        final int theNumberShifted = theNumber >> bitPosition;
        return theNumberShifted & mask;
    }
}
