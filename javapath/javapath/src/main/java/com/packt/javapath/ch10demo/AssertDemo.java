package com.packt.javapath.ch10demo;

public class AssertDemo {
    public static void main(String... args) {
        int x = 2;
        assert x > 1 : "x <= 1";
        assert x == 1 : "x != 1";
    }
}
