package com.packt.javapath.ch19demo;

import io.reactivex.Observable;
import java.util.stream.IntStream;

public class Reactive {

    public static void main(String... args) {
        Reactive r = new Reactive();
        r.stream();
        //r.observable1();
        r.observable2();
    }

    private void stream(){
        double a = IntStream.rangeClosed(1, 5)
                //.peek(System.out::print)
                .filter(i -> i % 2 == 0)
                //.peek(System.out::print)
                .mapToDouble(Double::valueOf)
                .map(Math::sqrt)
                .sum();
        System.out.println(a); //prints: 3.414213562373095
    }

    private void observable1(){
        Observable.range(1, 5)
                //.doOnNext(System.out::println)
                .filter(i -> i % 2 == 0)
                //.doOnNext(System.out::println)
                .map(Math::sqrt)
                .reduce((r, d) -> r + d)
                .subscribe(System.out::println); //prints: 3.414213562373095
    }


    private void observable2(){
        Observable<Double> observable = Observable.range(1,5)
                .filter(i -> i % 2 == 0)
                .doOnNext(System.out::println)
                .map(Math::sqrt);
                //.cache();

        observable
                .reduce((r, d) -> r + d)
                .subscribe(System.out::println); //prints: 3.414213562373095

        observable
                .reduce((r, d) -> r + d)
                .map(r -> r / 2)
                .subscribe(System.out::println);  //prints: 1.7071067811865475
    }

}
