package com.packt.javapath.ch18demo;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

public class ParallelStreams {
    public static void main(String... args) {

        list();
        doubleStream();

    }

    private static void doubleStream(){
        LocalTime lt1 = LocalTime.now();
        double a = DoubleStream.iterate(1., d -> d <= 100000000, d -> d + 1)
                .map(Math::sqrt).average().getAsDouble();
        System.out.println(a + " in " + Duration.between(LocalTime.now(), lt1).getNano());

        lt1 = LocalTime.now();
        a = DoubleStream.iterate(1., d -> d <= 100000000, d -> d + 1).parallel()
                .map(Math::sqrt).average().getAsDouble();
        System.out.println(a + " in " + Duration.between(LocalTime.now(), lt1).getNano());
    }

    private static void list(){
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i < 1000000; i++){
            list.add(i);
        }
        LocalTime lt1 = LocalTime.now();
        double a = list.stream().mapToDouble(i->i).map(Math::sqrt).average().getAsDouble();
        System.out.println(a + " in " + Duration.between(LocalTime.now(), lt1).getNano());

        lt1 = LocalTime.now();
        a = list.parallelStream().mapToDouble(i->i).map(Math::sqrt).average().getAsDouble();
        System.out.println(a + " in " + Duration.between(LocalTime.now(), lt1).getNano());
    }

}
