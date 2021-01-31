package com.examples.reactive;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class ObservableVariants {
    public static void main(String[] args) {
        Observable<Integer> observable2 = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        observable2.first(1).subscribe(System.out::println);

        //single will have only one element
        Single<String> singleObservable = Single.just("Malli");

        //Maybe will have zero or one elements.
        final Maybe<Integer> integerMaybe = observable2.firstElement();
        integerMaybe.subscribe(System.out::println);
        final Maybe<Object> objectMaybe = Observable.empty().firstElement();
        objectMaybe.subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Completed"));

        //Completable will not emit elements, but it says if it is completed or not.
        Completable completable = Completable.complete();
        completable.subscribe(() -> System.out.println("Completed"));

        Completable
                .fromRunnable(() -> System.out.println("Some process executing"))
                .subscribe(() -> System.out.println("Process Executed Successfully"));


    }
}
