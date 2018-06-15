package com.packt.javapath.ch04demo;

import com.packt.javapath.ch04demo.math.SimpleMath;

public class MyApplication {

    public static void main(String[] args) {
        int i = Integer.parseInt(args[0]);
        SimpleMath simpleMath = new SimpleMath();
        int result = simpleMath.multiplyByTwo(i);
        System.out.println(i + " * 2 = " + result);
    }
}


