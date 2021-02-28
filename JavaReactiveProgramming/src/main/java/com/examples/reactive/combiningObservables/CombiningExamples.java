package com.examples.reactive.combiningObservables;


import com.examples.reactive.Operators.Employee;
import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CombiningExamples {


    public static void main(String[] args) throws Exception {
        // flatMapConcatMap();
        //ambExample();
        //zipCombineLatest();
        groupBy();

    }

    public static void flatMapConcatMap() {
        List<String> list = List.of("Malli", "Arjun", "Sakthi", "Goki");
        //Used for non-concurrent observables.
        Observable.fromIterable(list)
                .flatMap(e -> Observable.fromArray(e.split("")))
                .subscribe(System.out::println);

        //Used for Concurrency and parallel Observables
        Observable.fromIterable(list)
                .concatMap(e -> Observable.fromArray(e.split("")))
                .subscribe(System.out::println);
    }

    public static void ambExample() throws Exception {
        Observable<String> src1 = Observable.interval(1, TimeUnit.SECONDS).take(10)
                .map(e -> "Ob 1:" + e);
        Observable<String> src2 = Observable.interval(2, TimeUnit.SECONDS).take(10)
                .map(e -> "Ob 2:" + e);

        //amb operator ignores duplicates and takes the elements from faster source.
        //in the above case src1 produces data every second,where as src2 produces every 2 seconds.
        //So amb will take data from src1
        Observable.amb(Arrays.asList(src1, src2)).subscribe(System.out::println);
        Thread.sleep(11000);
    }

    public static void zipCombineLatest() throws Exception {
        Observable<String> src1 = Observable.interval(200, TimeUnit.MILLISECONDS).take(10)
                .map(e -> "Ob 1:" + e);
        Observable<String> src2 = Observable.interval(1, TimeUnit.SECONDS).take(10)
                .map(e -> "Ob 2:" + e);
        // It will map element from src1 and src2. it will wait for both the sources to emit the elements.
        Observable.zip(src1, src2, (e1, e2) -> "Src1 : " + e1 + " Src2 : " + e2).subscribe(System.out::println);

        //This will map the latest element from src1 or src2 to the other one, it will not wait both the sources to generate the elements.
        // in the above sources src1 emits every 200 ms an elements, src2 emits for every sec. so src1 will emmit 5 elements before src2 emit one elements.
        //Now it will map from 5th element int src1 with the first element in src2. it continues this mapping until src2 emits 2nd element.
        Observable.combineLatest(src1, src2, (e1, e2) -> "Src1 : " + e1 + " Src2 : " + e2).subscribe(System.out::println);


        Thread.sleep(20000);
    }

    public static void groupBy(){
            Observable<Employee> employeeObservable = Observable.just(
                    new Employee(1, "Malli", 10.0, 9500.00),
                    new Employee(1, "Goki", 10.0, 9500.00),
                    new Employee(2, "Sakthi", 7.0, 2500.00),
                    new Employee(3, "John", 5.0, 5000.00),
                    new Employee(4, "Paul", 1.0, 5001.00),
                    new Employee(5, "Don", 8.0, 5500.00),
                    new Employee(6, "Lin", 9.0, 4500.00)
            );

            employeeObservable.groupBy(Employee::getRating)
                    .flatMapSingle(e -> e.toMultimap(key -> e.getKey(),emp ->emp.getName()))
                    .subscribe(System.out::println);
    }
}
