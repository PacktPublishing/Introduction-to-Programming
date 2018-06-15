package com.packt.javapath.ch19demo;

import io.reactivex.Observable;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Exercise {
    public static void main(String... args) {

        Observable.just("Hi!").subscribe(System.out::println); //prints: Hi!

        Observable.fromIterable(List.of("1","2","3")).subscribe(System.out::print); //prints: 123
        System.out.println();

        String[] arr = {"1","2","3"};
        Observable.fromArray(arr).subscribe(System.out::print); //prints: 123

        System.out.println();
        Observable.fromCallable(()->123).subscribe(System.out::println); //prints: 123

        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future<String> future = pool
                .submit(() -> {
                    Thread.sleep(100);
                    return "Hi!";
                });
        Observable.fromFuture(future).subscribe(System.out::println); //prints: Hi!
        pool.shutdown();

        Observable.interval(100, TimeUnit.MILLISECONDS).subscribe(v->System.out.println("100 ms is over")); //prints twice "100 ms is over"

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
