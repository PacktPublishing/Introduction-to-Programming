package com.packt.javapath.ch09demo;

public class Expressions {
    public static void main(String[] args) {

        sideEffect();
    }

    private static void sideEffect(){
        int x = 0, y;
        y = x++;                  //line 2
        System.out.println(y);    //prints: 0
        System.out.println(x);    //prints: 1
    }
}
