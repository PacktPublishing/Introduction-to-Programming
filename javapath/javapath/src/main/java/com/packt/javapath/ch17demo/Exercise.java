package com.packt.javapath.ch17demo;

import java.util.function.Supplier;

public class Exercise {

    public static void main(String... args) {
        Supplier<A> supplier1 = () -> new A();
        Supplier<A> supplier2 = A::new;
    }

    private static class A{}

}
