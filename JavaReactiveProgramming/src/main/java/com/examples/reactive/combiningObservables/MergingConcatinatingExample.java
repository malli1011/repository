package com.examples.reactive.combiningObservables;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class MergingConcatinatingExample {
    //Merging the emissions of multiple observables to have a single observable source.

    public static void main(String[] args) throws Exception {

        /*Observable<String> source1 = Observable.just("Malli", "Arjun", "Sakthi", "Goki");
        Observable<Integer> source2 = Observable.range(11, 10);*/

        Observable<String> src1 = Observable.interval(1, TimeUnit.SECONDS).map(e -> "Src1 : "+e);
        Observable<String> src2 = Observable.interval(1, TimeUnit.SECONDS).map(e -> "Src2 : "+e);

        //merge doesn't maintain the order
        System.out.println("Merge output :");
        Observable.merge(src1, src2).subscribe(System.out::println);

        //concat maintains the order.
        System.out.println("Concat output :");
        Observable.concat(src1, src2).subscribe(System.out::println);

        Thread.sleep(20000);
    }


}
