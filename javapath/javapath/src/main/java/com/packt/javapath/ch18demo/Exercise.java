package com.packt.javapath.ch18demo;

import java.util.List;

public class Exercise {
    public static void main(String... args) {
        List<Integer> list = List.of(2, 3, 4);
        int r = list.stream().reduce(1, (x, y) -> x * y);
        System.out.println(r);     //prints: 24
    }
}
