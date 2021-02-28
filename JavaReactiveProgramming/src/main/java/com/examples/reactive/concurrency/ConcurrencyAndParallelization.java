package com.examples.reactive.concurrency;

import io.reactivex.rxjava3.core.Observable;

public class ConcurrencyAndParallelization {
    /*
     * The Observable Contract : The emissions must be passed sequentially and one at a time.
     * If its sequential then how can we run it parallel?
     * */
    public static void main(String[] args) {

        //Sequentially
        System.out.println("Sequential outcome :");
        Observable<String> source = Observable.create(
                e -> {
                    e.onNext("Hello");
                    e.onNext("RxJava");
                });
        source.subscribe(e -> System.out.println("Observer1 " + e + " Thread is :" + Thread.currentThread().getName()));
        source.subscribe(e -> System.out.println("Observer2 " + e + " Thread is :" + Thread.currentThread().getName()));

        //Parallel
        //For each subscriber a new thread will be created and emits the data.
        System.out.println("Parallel outcome :");
        Observable<String> source2 = Observable.create(
                e -> {
                    new Thread(() -> {
                        e.onNext("Hello");
                        e.onNext("RxJava");
                    }).start();

                });

        source2.subscribe(e -> System.out.println("Observer1 " + e + " Thread is :" + Thread.currentThread().getName()));
        source2.subscribe(e -> System.out.println("Observer2 " + e + " Thread is :" + Thread.currentThread().getName()));
    }


}
