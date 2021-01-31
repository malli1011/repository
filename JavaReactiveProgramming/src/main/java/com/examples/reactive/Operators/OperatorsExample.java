package com.examples.reactive.Operators;

import io.reactivex.rxjava3.core.Observable;

import java.util.Comparator;

public class OperatorsExample {
    public static void main(String[] args) {
        //Operator is a function, that takes an upstream Observable and then apply the function and then return downStream Observable.

        //Basic Operators
        Observable<Integer> observable2 = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final Observable<Integer> evenNumbersSource = observable2.filter(i -> i % 2 == 0);
        evenNumbersSource.subscribe(System.out::println);

        final Observable<Integer> reverseOrdered = observable2.sorted(Comparator.reverseOrder());
        reverseOrdered.subscribe(System.out::println);

        //Suppressing Operators
        //filter(),take(),skip(),distinct()

        //Transforming operators
        //map(),cast(),delay(),scan()
        observable2.map(Integer::doubleValue).subscribe(System.out::println);
        observable2.scan((x,y)->x+y).subscribe(System.out::println);

        //Reducing Operators: Takes a series of emission and reduce them into single emission. Works with finite observables.
        //count(),reduce(),contains(),all(),any()
        observable2.reduce((x,y)->x+y).subscribe(System.out::println);

        //collection Operators: combine all the emissions from upstream to some collection.
        //toList(),toSortedList(),toMap(),collect()

        //Error Recovery Operators : Used to handle the errors and to recover from them.
        //OnErrorReturnItem(),OnErrorReturn(),onErrorResumeNext(),retry()

        //Action Operators : Used to do debugging in the Observable chain.We can peek into observable chain at some particular event, to know whats happening.
        //doOnNext,doOnError(),doOnSubscribe(),doOnComplete()

    }
}
