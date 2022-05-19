package com.Interview.questions;

import java.util.ArrayList;
import java.util.List;

public class FindLongestPalindrome {

    private boolean isPalindrome(String str) {
        int len = str.length();
        if (len == 1) {
            return false;
        }
        StringBuffer sb = new StringBuffer(str);
        if (sb.reverse().toString().equals(str)) {
            return true;
        }
        return false;
    }

    private List<String> getAllSubStrings(String str) {
        int len = str.length();
        List<String> subStrings = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                subStrings.add(str.substring(i, j));
            }
        }
        return subStrings;
    }

    private void printLongestString(List<String> list) {
        list.stream().filter(this::isPalindrome).reduce((a, b) -> a.length() >= b.length() ? a : b).ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        var findLongestPalindrome = new FindLongestPalindrome();
        findLongestPalindrome.printLongestString(findLongestPalindrome.getAllSubStrings("asdfpnapmalayalamasdfas"));
    }

}
