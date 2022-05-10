package com.Interview.questions;

import java.util.List;

public class BuyAndSellStocks {

    public static void main(String[] args) {

        printBuyAndSellIndex(List.of(5, 4, 4, 3, 2,9));
    }

    private static void printBuyAndSellIndex(List<Integer> values) {

        int maxProfit = 0;
        int buyDay = 0;
        int sellDay = 0;

        for (int i = 0; i < values.size()-1; i++) {
            for (int j = i + 1; j < values.size(); j++) {
                int diff = values.get(j) - values.get(i);
                if (diff > maxProfit) {
                    maxProfit = diff;
                    buyDay = i + 1;
                    sellDay = j + 1;

                }
            }
        }
        System.out.printf("Buy Date:%d & Sell Date:%d\n", buyDay, sellDay);
        System.out.println("Profit: "+maxProfit);
    }
}
