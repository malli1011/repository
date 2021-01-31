package com.examples.reactive;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HotAndColdObservables {
    public static void main(String[] args) throws InterruptedException {
        //cold observables will get started when it is subscribed.  It will give same data to all the observers. new subscribers will get latest data.
        List<String> list = new ArrayList<>();
        list.add("Malli");
        list.add("Sakthi");
        Observable<String> observable3 = Observable.fromIterable(list);
        observable3.subscribe(System.out::println);

        list.add("Paul");
        observable3.subscribe(System.out::println);

        //Hot observable will get started when they are created. New observers will get the data that is available at that point of time.
        ConnectableObservable<Long> hotObservable = Observable.interval(1, TimeUnit.SECONDS).publish();
        hotObservable.connect();

        hotObservable.subscribe(System.out::println);
        Thread.sleep(10000);
        //second subscriber starts from 10 as it started after 10 seconds.
        hotObservable.subscribe(System.out::println);
        Thread.sleep(10000);
    }
}
