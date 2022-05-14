package com.Interview.questions.multithreading;

import com.Interview.questions.multithreading.Data;

import java.util.HashMap;
import java.util.Map;

public class NonSynchronizedData implements Data {

    Map<Integer,String> dict = new HashMap<>();
    @Override
    public void writeData(String str) {
        dict.put(1,str);
    }

    @Override
    public void readData() {
        System.out.println(dict.get(1));
    }
}
