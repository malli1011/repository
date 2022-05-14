package com.Interview.questions.multithreading;

import com.Interview.questions.multithreading.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SynchronizedData implements Data {
    //List<String> strings = new ArrayList<>();
    Map<Integer,String> dict = new HashMap<>();
    ReadWriteLock lock = new ReentrantReadWriteLock();

    public void writeData(String str) {
        try {
            lock.writeLock().lock();
            dict.put(1,str);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void readData() {
        try {
            lock.readLock().lock();
            System.out.println(dict.get(1));
        } finally {
            lock.readLock().unlock();
        }
    }

}
