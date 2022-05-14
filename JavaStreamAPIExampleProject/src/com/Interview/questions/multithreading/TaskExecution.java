package com.Interview.questions.multithreading;

import java.util.concurrent.*;

public class TaskExecution {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        //Data data = new SynchronizedData();
        Data data = new NonSynchronizedData();

        WriteTask writeTask = new WriteTask(data);
        ReadTask readTask1 = new ReadTask(data);
        ReadTask readTask2 = new ReadTask(data);
        ReadTask readTask3 = new ReadTask(data);

        executorService.submit(writeTask);
        executorService.submit(readTask1);
        executorService.submit(readTask2);
        executorService.submit(readTask3);


        executorService.shutdown();
        System.out.println("Main Method completed!!");
    }
}
