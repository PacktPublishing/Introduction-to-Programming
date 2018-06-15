package com.packt.javapath.ch04demo.math;

public class SimpleMath {
    private int i;
    private String s;
    public SimpleMath() {
    }

    public SimpleMath(int i) {
        this.i = i;
    }

    public SimpleMath(String s) {
        this.s = s;
    }

    /*
           This method just multiplies any integer by 2
           and returns the result
    */
    public int multiplyByTwo(int i){

        // Consider checking if i is bigger than 1/2 Integer.MAX_VALUE

        return i * 2; // Here the magic happens
    }

    public int multiplyByTwo(String s){
        int i = Integer.parseInt(s);
        return multiplyByTwo(i);
    }

}