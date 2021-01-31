package com.examples.reactive;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableCreate;

public class HelloRxJava {
    public static void main(String[] args) {
        ObservableAndObserver();
    }

    public static void helloWorld() {
        Observable<String> source = Observable.create(
                e -> {
                    e.onNext("Hello");
                    e.onNext("RxJava");
                });
        source.subscribe(e -> System.out.println("Observer1 :" + e));
        source.subscribe(e -> System.out.println("Observer2 :" + e));
        source.subscribe(e -> System.out.println("Observer3 :" + e));
    }

    public static void ObservableAndObserver() {
        Observable<Integer> observable = new ObservableCreate<Integer>(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) {

                try {
                    emitter.onNext(1);
                    emitter.onNext(21);
                    emitter.onComplete();
                } catch (Throwable e) {
                    emitter.onError(new RuntimeException("Failed to send data"));
                }
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Subscribed");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("On Next: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        };

        observable.subscribe(observer);
    }


}
