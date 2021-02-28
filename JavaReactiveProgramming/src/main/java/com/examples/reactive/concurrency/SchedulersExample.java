package com.examples.reactive.concurrency;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 1. Computations Scheduler : To perform computation heavy operations like executing algorithms . It uses all the available cores of a CPU.  So No.Threads <= No. of cores. Otherwise it will create performance issues.  Prefer this by default if you don't know which one to use.
 * No. Of Threads = Number of available Cores.
 * Runtime.getRunTime().availableProcessors();
 * <p>
 * <p>
 * 2. IO Scheduler : IO intensive tasks. For File, DB or HTTP calls.  Where we have more waiting time to get the response. So No.Threads can be  => No. of cores.
 * <p>
 * The above two creates a pool of threads and re-uses them.
 * <p>
 * 3. New Thread Scheduler : Create 1 Thread per Observer and then destroy the thread when done.
 * 4. Single Thread : Create only one Thread, So to run all the takes sequentially on that thread.
 * 5. Trampoline Scheduler :  This is mainly used for RxJava internal implementation. We may not use it often.
 * 6. Scheduler.from() : using our own executor service.
 */
public class SchedulersExample {
    public static void main(String[] args) throws InterruptedException {
       // SchedulerExamples();
        //observerOnExample();
        flatMapExample();
        Thread.sleep(5000);
    }

    public static void SchedulerExamples() throws InterruptedException {
        final Observable<String> src = Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein").subscribeOn(Schedulers.computation());
        IntStream.rangeClosed(1, 10).forEach(i -> src.subscribe(e -> compute()));

        final Observable<String> src2 = Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein").subscribeOn(Schedulers.io());
        IntStream.rangeClosed(1, 10).forEach(i -> src2.subscribe(e -> compute()));
        //Thread.sleep(5000);
        // Above two schedulers create daemon threads which gets terminated once the main method completes. so we put a thread.sleep() at the end.

        System.out.println("**********************************************************************");
        //Below custom scheduler creates non daemon threads so they will complete even after the main method completes.
        ExecutorService executor = Executors.newFixedThreadPool(20);
        final Scheduler scheduler = Schedulers.from(executor);
        final Observable<String> src3 = Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
                .subscribeOn(scheduler)  //in simple subscribeOn method is used to make async subscription
                .doFinally(executor::shutdown);// to shutdown the executor after all the executions complete

        System.out.println("**********************************************************************");
    }

    public static void compute() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Computation Done By: " + Thread.currentThread().getName());


    }

    public static void observerOnExample() throws InterruptedException{
        //Using subscribeOn
        Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
                .subscribeOn(Schedulers.computation())
                .map(e ->e.toLowerCase())
                .doOnNext(e -> System.out.println(Thread.currentThread().getName()))
                .subscribeOn(Schedulers.io())
                .filter(e-> e.length()>5)
                .subscribe(System.out::println);

        //Using observeOn
        Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
                .subscribeOn(Schedulers.computation())
                .map(e ->e.toLowerCase())
                .doOnNext(e -> System.out.println(Thread.currentThread().getName()))
                .observeOn(Schedulers.io())
                .filter(e-> e.length()>5)
                .observeOn(Schedulers.newThread())
                .subscribe(System.out::println);
    }

    public static void flatMapExample() throws InterruptedException{
        Observable.just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
                .flatMap(e -> Observable.just(e).subscribeOn(Schedulers.computation()).map(str -> task(str))).subscribe(System.out::println);
    }

    private static String task(String element){
        return element +" : "+Thread.currentThread().getName();
    }
}

