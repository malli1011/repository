package com.examples.reactive.replaying_caching;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import io.reactivex.rxjava3.subjects.Subject;

import java.util.concurrent.TimeUnit;

public class ReplayingCachingExample {
    public static void main(String[] args) throws InterruptedException {
        //replayExample();
        //subjectExample();
        emissionsUsingSubject();

    }

    public static void replayExample() throws InterruptedException {
        final Observable<@NonNull Long> src = Observable.interval(1, TimeUnit.SECONDS)
                .replay()  //replay(2) it will replay latest two elements when a new subscriber comes.

                .autoConnect();

        src.subscribe(e -> System.out.println("Observer 1: " + e));
        Thread.sleep(5000);

        src.subscribe(e -> System.out.println("Observer 2: " + e));
        Thread.sleep(3000);
    }


    public static void cacheExample() throws InterruptedException {
        final Observable<@NonNull Long> src = Observable.interval(1, TimeUnit.SECONDS)
                .cache();
        src.subscribe(e -> System.out.println("Observer 1: " + e));
        Thread.sleep(5000);

        src.subscribe(e -> System.out.println("Observer 2: " + e));
        Thread.sleep(3000);
    }

    public static void subjectExample() throws InterruptedException {
        final Observable<Integer> src1 = Observable.just(5, 10, 15, 20).subscribeOn(Schedulers.computation());
        final Observable<Integer> src2 = Observable.just(50, 100, 150, 200).subscribeOn(Schedulers.computation());

  /*      src1.subscribe( e-> System.out.println(e));
        src2.subscribe( e-> System.out.println(e));*/

        final PublishSubject<Object> subject = PublishSubject.create();
        subject.subscribe(e -> System.out.println(e));
        src1.subscribe(subject);
        src2.subscribe(subject);

        Thread.sleep(10000);
    }

    public static void emissionsUsingSubject() {
        final PublishSubject<Object> subject = PublishSubject.create();
        //final PublishSubject<Object> subject = ReplaySubject.create();


        // first we need to subscribe to start the pipeline
        subject.subscribe(System.out::println);
        subject.subscribe(e -> System.out.println("Observer 2 : " + e));

        subject.onNext("Hello");
        subject.onNext("Basic");
        subject.onNext("Strong");
        subject.onComplete();
        //The below subscription will not get any emission as it subscribed after completing the emission.
        // Subjects are hot Observables. Subjects are not Thread safe, so we can use toSerialized method to make it thread safe.
        subject.subscribe(System.out::println);

        final Subject<Object> serialized = subject.toSerialized();
        serialized.subscribe(System.out::println);
    }
}
