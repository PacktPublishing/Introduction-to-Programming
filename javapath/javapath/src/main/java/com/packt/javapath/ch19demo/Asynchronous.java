package com.packt.javapath.ch19demo;

public class Asynchronous {
    public static void main(String... args) {
        int n = Runtime.getRuntime().availableProcessors();
        System.out.println(n);     //prints: 24
    }
}
