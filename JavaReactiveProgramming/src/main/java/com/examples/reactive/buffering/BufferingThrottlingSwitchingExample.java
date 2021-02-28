package com.examples.reactive.buffering;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class BufferingThrottlingSwitchingExample {
    public static void main(String[] args) throws InterruptedException {
        //bufferingExamples();
        switchMapExample();
    }

    public static void bufferingExamples() {

        //Emits set of 5 elements at a time.
        Observable.range(1, 30).buffer(5).subscribe(System.out::println);

        //Emits set of elements at a specified interval
        Observable.range(1, 30).buffer(1, TimeUnit.SECONDS).subscribe(System.out::println);

        //Observable bound buffer
        final Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
        Observable.interval(300, TimeUnit.SECONDS).buffer(interval).subscribe(System.out::println);
    }

    //check when and how to use the switchMap
    public static void switchMapExample() throws InterruptedException {
        Observable<String> src = Observable.just("John", "Lilly", "Emma", "Reyan", "lDarshin")
                .concatMap(
                        s -> Observable.just(s).delay(
                                ThreadLocalRandom.current().nextInt(1000), TimeUnit.MILLISECONDS)
                );
        Observable.interval(2, TimeUnit.SECONDS)
                .switchMap(s -> src.doOnDispose(() -> System.out.println("Disposing and starting again")))
                .subscribe(System.out::println);
        Thread.sleep(10000);
    }

}
