package com.Interview.questions.multithreading;

import com.Interview.questions.multithreading.Data;

public class WriteTask implements Runnable {

    private final Data data;

    public WriteTask(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Current Thread: " + Thread.currentThread().getName());
                System.out.println("Added new Value! " + i);
                data.writeData("Data-" + i);
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
