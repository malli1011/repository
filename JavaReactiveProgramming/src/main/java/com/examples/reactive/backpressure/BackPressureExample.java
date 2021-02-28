package com.examples.reactive.backpressure;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BackPressureExample {
    public static void main(String[] args) {
        //backPressure();
        handleBackPressureWithFlowable();
    }


    public static void backPressure() {
        Observable.range(1, 1000000)
                .map(e -> {
                    System.out.println("produced item : " + e + " : " + Thread.currentThread().getName());
                    return e;
                })
                .observeOn(Schedulers.io())
                .subscribe(e -> {
                    sleep(100);
                    System.out.println("Consumed  item is : " + e);
                });
        sleep(110000);
    }

    public static void handleBackPressureWithFlowable() {
        //Flowable produces a set of elements and waits for the consumer to consume them and then produces the next set.
        Flowable.range(1, 1000000)
                .map(e -> {
                    System.out.println("produced item :" + e + " " + Thread.currentThread().getName());
                    return e;
                })
                .observeOn(Schedulers.io())
                .subscribe(e -> {
                    sleep(100);
                    System.out.println("Consumed  item is : " + e);
                });
        sleep(110000);
    }

    private static void sleep(long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
