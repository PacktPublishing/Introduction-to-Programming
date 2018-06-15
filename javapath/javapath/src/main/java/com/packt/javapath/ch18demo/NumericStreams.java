package com.packt.javapath.ch18demo;

import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


public class NumericStreams {
    public static void main(String... args) {
        range();
        boxed();
        mapToObj();
        mapTo();
        flatMapTo();
        sumAndAverage();
    }

    private static void sumAndAverage() {

        System.out.println();
        int sum = IntStream.empty().sum();
        System.out.println(sum);  //prints: 0
        sum = IntStream.range(1, 3).sum();
        System.out.println(sum);  //prints: 3
        double av = IntStream.empty().average().orElse(0);
        System.out.println(av);   //prints: 0.0
        av = IntStream.range(1, 3).average().orElse(0);
        System.out.println(av);   //prints: 1.5
        long suml = LongStream.range(1, 3).sum();
        System.out.println(suml);  //prints: 3
        double avl = LongStream.range(1, 3).average().orElse(0);
        System.out.println(avl);   //prints: 1.5
        double sumd = DoubleStream.of(1, 2).sum();
        System.out.println(sumd);  //prints: 3.0
        double avd = DoubleStream.of(1, 2).average().orElse(0);
        System.out.println(avd);   //prints: 1.5

    }
    private static void flatMapTo() {
        List<Integer> list = List.of(1, 2, 3);

        System.out.println();
        list.stream().flatMapToInt(i->IntStream.rangeClosed(1, i)).forEach(System.out::print);  //prints: 112123
        System.out.println();
        list.stream().flatMapToLong(i->LongStream.rangeClosed(1, i)).forEach(System.out::print);  //prints: 112123
        System.out.println();
        list.stream().flatMapToDouble(DoubleStream::of).forEach(d -> System.out.print(d + " "));  //prints: 1.0 2.0 3.0

        List<String> str = List.of("one", "two", "three");

        System.out.println();
        str.stream().flatMapToInt(s->IntStream.rangeClosed(1, s.length())).forEach(System.out::print);  //prints: 12312312345

    }

    private static void mapTo() {
        List<String> list = List.of("one", "two", "three");

        System.out.println();
        list.stream().mapToInt(String::length).forEach(System.out::print);                       //prints: 335
        System.out.println();
        list.stream().mapToLong(String::length).forEach(System.out::print);                      //prints: 335
        System.out.println();
        list.stream().mapToDouble(String::length).forEach(d -> System.out.print(d + " "));  //prints: 3.0 3.0 5.0
        System.out.println();
        //list.stream().mapToInt(String::length).map(Integer::shortValue).forEach(System.out::print);  //compile error
        list.stream().map(String::length).map(Integer::shortValue).forEach(System.out::print);  //prints: 335
    }

    private static void mapToObj() {

        System.out.println();
        IntStream.range(1, 3).mapToObj(Integer::valueOf).map(Integer::shortValue).forEach(System.out::print);  //prints: 12
        System.out.println();
        IntStream.range(42, 43).mapToObj(i -> new Person(i, "John")).forEach(System.out::print);  //prints: Person{name:John,age:42}
        System.out.println();
        LongStream.range(1, 3).mapToObj(Long::valueOf).map(Long::shortValue).forEach(System.out::print);  //prints: 12
        System.out.println();
        DoubleStream.of(1).mapToObj(Double::valueOf).map(Double::shortValue).forEach(System.out::print);  //prints: 1
        System.out.println();
    }

    private static void boxed() {

        System.out.println();
        //IntStream.range(1, 3).map(Integer::shortValue).forEach(System.out::print);  //compile error
        IntStream.range(1, 3).boxed().map(Integer::shortValue).forEach(System.out::print);  //prints: 12
        System.out.println();
        //LongStream.range(1, 3).map(Long::shortValue).forEach(System.out::print);  //compile error
        LongStream.range(1, 3).boxed().map(Long::shortValue).forEach(System.out::print);  //prints: 12
        System.out.println();
        //DoubleStream.of(1).map(Double::shortValue).forEach(System.out::print);  //compile error
        DoubleStream.of(1).boxed().map(Double::shortValue).forEach(System.out::print);  //prints: 1
        System.out.println();
    }

    private static void range() {

        System.out.println();
        IntStream.range(1, 3).forEach(System.out::print);  //prints: 12
        System.out.println();
        LongStream.range(1, 3).forEach(System.out::print);  //prints: 12
        System.out.println();
        IntStream.rangeClosed(1, 3).forEach(System.out::print);  //prints: 123
        System.out.println();
        LongStream.rangeClosed(1, 3).forEach(System.out::print);  //prints: 123
        System.out.println();
    }

    private static class Person {
        private String name;
        private int age;

        public Person(int age, String name) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return this.name; }

        public int getAge() {
            return this.age;
        }

        @Override
        public String toString() {
            return "Person{name:" + this.name + ",age:" + age + "}";
        }
    }

}
