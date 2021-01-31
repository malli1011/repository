package com.examples.reactive;

import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateObservableExamples {
    public static void main(String[] args) {
        //create
        Observable<Integer> observable1 = Observable.create(e -> {
            e.onNext(10);
            e.onNext(20);
            e.onComplete();
        });
        observable1.subscribe(System.out::println);

        //just
        Observable<Integer> observable2 = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        observable2.subscribe(System.out::println);

        //Iterable
        List<String> list = new ArrayList<>();
        list.add("Malli");
        list.add("Sakthi");
        Observable<String> observable3 = Observable.fromIterable(list);
        //Range
        Observable.range(1, 10).subscribe(System.out::println);

        //Interval, time based
        Observable.interval(1, TimeUnit.SECONDS);
     /*   //Future
        Observable.fromFuture(future);
        Observable.fromCallable(callable);
        Observable.empty();
        Observable.never();
        Observable.error(() -> throw new RuntimeException())

        */

        Observable<String> observable4 = Observable.defer(() -> Observable.fromIterable(list));
        observable4.subscribe(System.out::println);
        list.add("Paul");
        observable4.subscribe(System.out::println);

    }
}
