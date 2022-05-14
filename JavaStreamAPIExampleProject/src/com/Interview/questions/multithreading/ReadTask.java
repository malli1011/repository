package com.Interview.questions.multithreading;

import com.Interview.questions.multithreading.Data;

public class ReadTask implements Runnable{
    private final Data data;

    public ReadTask(Data data) {
        this.data = data;
    }

    @Override
    public void run(){
        for(int i=0;i<10;i++){
            try {
                System.out.println("Current Thread: " + Thread.currentThread().getName());
                data.readData();
                Thread.sleep(100L);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
