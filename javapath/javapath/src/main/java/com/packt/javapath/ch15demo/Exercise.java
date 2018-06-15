package com.packt.javapath.ch15demo;
import java.util.Objects;
public class Exercise {
    private A a;
    private B b;
    public Exercise(){
        System.out.println(Objects.equals(a, b));
    }
    public static void main(String... args){
        new Exercise();
    }


    static class A{}
    static class B{}

}
