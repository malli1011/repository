package com.examples.reactive;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class Disposing {
    public static void main(String[] args) throws Exception {
        Observable<Long> src = Observable.interval(1, TimeUnit.SECONDS);
        final Disposable disposable = src.subscribe(e -> System.out.println("Observer1 : " + e));

        src.subscribe(e -> System.out.println("Observer2 : " + e));
        Thread.sleep(5000);
        disposable.dispose();

        src.subscribe(new Observer<Long>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                this.disposable = d;
            }

            @Override
            public void onNext(@NonNull Long aLong) {
                System.out.println("Observer3 : " + aLong);
                if(aLong==10)
                    disposable.dispose();
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        Thread.sleep(20000);
    }
}
