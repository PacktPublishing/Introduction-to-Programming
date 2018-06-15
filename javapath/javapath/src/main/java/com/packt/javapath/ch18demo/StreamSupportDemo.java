package com.packt.javapath.ch18demo;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.DoubleStream;
import java.util.stream.StreamSupport;

public class StreamSupportDemo {
    public static void main(String... args) {
        createStream();
        parallelStream();

    }

    private static void parallelStream(){
        LocalTime lt1 = LocalTime.now();
        double a = DoubleStream.iterate(1., d -> d <= 100000000, d -> d + 1)
                .map(Math::sqrt).average().getAsDouble();
        System.out.println(a + " in " + Duration.between(LocalTime.now(), lt1).getNano());

        lt1 = LocalTime.now();
        a = DoubleStream.iterate(1., d -> d <= 100000000, d -> d + 1).parallel()
                .map(Math::sqrt).average().getAsDouble();
        System.out.println(a + " in  " + Duration.between(LocalTime.now(), lt1).getNano());
    }

    private static void createStream(){
        Iterable<String> iterable = List.of("1", "2", "3", "4", "5");
        StreamSupport.stream(iterable.spliterator(), false)
                .forEach(System.out::print);    //prints: 12345

        System.out.println();

        int[] arr = {1, 2, 3, 4, 5};
        StreamSupport.intStream(()-> Spliterators.spliterator(arr, 0), Spliterator.IMMUTABLE, false)
                .forEach(System.out::print);    //prints: 12345

    }
}
