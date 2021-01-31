package com.examples.reactive;

import io.reactivex.rxjava3.core.Observable;

public class CreateObserverExamples {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        observable.subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Completed"));

        System.out.println("**************************");
        observable.subscribe(System.out::println, Throwable::printStackTrace);

        System.out.println("**************************");
        observable.subscribe(System.out::println);
    }
}
